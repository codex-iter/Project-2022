// Using enviroment variables to save data from being published online
require('dotenv').config();
const express = require("express");
const bodyParser = require("body-parser");
const ejs = require("ejs");
const { default: mongoose } = require("mongoose");
const { v4: uuidv4 } = require("uuid");

//importing custom modules
const accountGenerator = require(__dirname +
  "/modules/accountNumberGenerator.js");
const branches = require(__dirname + "/modules/branches.js");
const getIFSC = require(__dirname + "/modules/getIFSC.js");

const app = express();
app.set("view engine", "ejs");

app.use(bodyParser.urlencoded({ extended: true }));
app.use(express.static("public"));

//The below code is the status code and is responsible for rendering content to the status page
var statusImage = "fail";
var statusText = "Some Error Occured!";

// Code to connect to mongo db atlas
mongoose.connect(
  process.env.MONGO
);

//Creating schema for Bank Account Details
const accountSchema = {
  name: String,
  account_no: Number,
  balance: Number,
  branch: String,
  ifsc: String,
  pin: String,
  transactions: [String],
};

//Creating Account Model
const Account = mongoose.model("Account", accountSchema);

//Creating transaction Schema
const transactionSchema = {
  id: String,
  amount: Number,
  date: String,
  sender: Number,
  recipient: Number,
};

//Creating transaction Model
const Transaction = mongoose.model("Transaction", transactionSchema);

var date = new Date();

//Below is sample Account
/*
const saket = new Account({
    name: "Saket Aryan",
    account_no: 29497898279,
    balance: 1000000,
    branch: "BBS",
    ifsc: "FBIN0123456",
    pin: "214100"
})

//saket.save();

*/

app.get("/", (req, res) => {
  res.render("index");
});

app
  .route("/transfer")
  .get((req, res) => {
    res.render("transfer", { branches: branches.branches(), account: "" });
  })
  .post((req, res) => {
    (async () => {
      let data = req.body;

      //Saving the data
      let sender = data.myAccount;
      let recipient = data.resAccount;
      let pin = data.pin;
      let amount = data.amount;
      let senderBranch = data.myBranch;
      let recipientBranch = data.resBranch;
      let senderIFSC =
        "FBIN0" + getIFSC.getIFSC(branches.branches(), senderBranch);
      let recipientIFSC =
        "FBIN0" + getIFSC.getIFSC(branches.branches(), recipientBranch);

      // Validating users Exists
      let senderCheck = await Account.findOne(
        { account_no: sender },
        "name account_no ifsc balance pin transactions"
      );
      let recipientCheck = await Account.findOne(
        { account_no: recipient },
        "name account_no ifsc balance transactions"
      );

      if (senderCheck == null) {
        fail("Invalid Sender!");
        res.redirect("/status");
        return null;
      } else if (recipientCheck == null) {
        fail("Invalid Recipient!");
        res.redirect("/status");
        return null;
      }
      //console.log(senderCheck);
      //console.log(recipientCheck);

      //Validating Send is not the recipient
      if (sender == recipient) {
        fail("Sender cannont be recipient!");
        res.redirect("/status");
        return null;
      }

      //Validating IFSC Code
      if (
        senderIFSC != senderCheck.ifsc ||
        recipientIFSC != recipientCheck.ifsc
      ) {
        fail("Invalid Branch Code!");
        res.redirect("/status");
        return null;
      }

      // Validating Amount (if sender has enought amount in his bank account);
      if (amount > senderCheck.balance) {
        fail("Insufficient Balance!");
        res.redirect("/status");
        return null;
      }

      //Validating pin
      if (pin != senderCheck.pin) {
        fail("Invalid Pin!");
        res.redirect("/status");
        return null;
      }

      // Actually Updating Balance
      let send = await Account.updateOne(
        { account_no: sender },
        { balance: senderCheck.balance - amount }
      );
      let recieve = await Account.updateOne(
        { account_no: recipient },
        { balance: +recipientCheck.balance + +amount }
      );

      //console.log(send);
      //console.log(recieve);
      //validating successful transaction

      if (send.acknowledged == true && send.acknowledged == true) {
        //Creating Transaction Object
        let transactionID;

        while (true) {
          let no = uuidv4();
          let check = await Transaction.findOne({ id: no }).exec();
          transactionID = no;
          if (check == null) break;
        }

        let addSenderTransaction = await Account.updateOne(
          { account_no: sender },
          { transactions: [...senderCheck.transactions, transactionID] }
        );
        let addRecipientTransaction = await Account.updateOne(
          { account_no: recipient },
          { transactions: [...recipientCheck.transactions, transactionID] }
        );

        let transaction = new Transaction({
          id: transactionID,
          amount: amount,
          date: date.toLocaleDateString(),
          sender: sender,
          recipient: recipient,
        });
        transaction.save();
        statusImage = "success";
        statusText = "Money Sent!";
      } else {
        fail("Transaction Failed!");
        res.redirect("/status");
      }
      res.redirect("/status");
    })();
  });

app
  .route("/create")
  .get((req, res) => {
    res.render("create", { branches: branches.branches() });
  })
  .post((req, res) => {
    (async () => {
      let data = req.body;
      let name = data.name;
      let pin = data.pin;
      if (typeof name == "undefined") {
        fail("Something went wrong!");
        res.redirect("/status");
        return null;
      }
      let account_no;
      let branch = data.branch;
      let ifsc = "FBIN0" + getIFSC.getIFSC(branches.branches(), branch);

      //check so that no duplicate account number gets created
      while (true) {
        let no = accountGenerator.accountNumber();
        let check = await Account.findOne({ account_no: account_no }).exec();
        account_no = no;
        if (check == null) break;
      }

      let account = new Account({
        name: name,
        pin: pin,
        balance: 10000,
        account_no: account_no,
        branch: branch,
        ifsc: ifsc,
      });

      account.save();
      success("Account Created Successfully with ACCOUNT NO: " + account_no);
      res.redirect("/status");
    })();
  });

app.route("/customers").get((req, res) => {
  (async () => {
    let accounts = await Account.find({}, "name account_no ifsc branch balance");
    res.render("customers", { accounts: accounts });
  })();
});

app.route("/account/:number").get((req, res) => {
  let account_no = req.params.number;

  (async () => {
    let account = await Account.findOne({ account_no: account_no }).exec();

    let transactions = [];
    let transactionIDs = await Transaction.find({});

    //loop to check actual transactions
    for (let i = 0; i < transactionIDs.length; i++) {
      if (account.transactions.includes(transactionIDs[i].id)) {
        let transactionsObject = {
          id: transactionIDs[i].id,
          amount: transactionIDs[i].amount,
          date: transactionIDs[i].date,
          account:
            transactionIDs[i].sender == account_no
              ? transactionIDs[i].recipient
              : transactionIDs[i].sender,
            type: transactionIDs[i].sender == account_no ? "table-danger" : "table-success"
          // type: transactionIDs[i].sender == account_no ? "debit" : "credit"
        };
        transactions.push(transactionsObject);
      }
    }

    res.render("account", { account: account, transactions: transactions });
  })();
});

app.get("/updatepassword/:account", (req, res) => {
  res.render("updatePassword", { account: req.params.account });
});

app
  .route("/updatepassword")
  .get((req, res) => {
    res.render("updatePassword", {account: ""});
  })
  .post((req, res) => {
    (async () => {
        // Saving Data in Variables
        let data = req.body;

        let account_no = data.account;
        let oldpin = data.oldpin;
        let newpin = data.newpin;
        let confirmpin = data.confirmpin;

        //Retrieving Account Information
        let account = await Account.findOne(
            { account_no: account_no },
            "account_no pin"
          );


        //Validating if account exits
        if(account==null){
            fail("Account doesn't exist!");
            res.redirect('/status');
            return null;
        }

        //validatind newPin and confirmPin Match
        if( newpin != confirmpin){
            fail("New pin and confirm pin didn't match!");
            res.redirect('/status');
            return null;
        }

        //validating old password
        if(account.pin != oldpin){
            fail("Bad Credetials");
            res.redirect('/status');
            return null;
        }

        //updating the password
        let updatePin = await Account.updateOne({account_no: account_no}, {pin: confirmpin});
        if(updatePin.acknowledged == true) {
            success("Password Updated Successfully!");
        }else{
            fail("Something went wrong!");
        }

        res.redirect('status');
        
    })();
  });

  app.get('/transfer/:account', (req, res)=>{
    let account = req.params.account;
    res.render("transfer", { branches: branches.branches(), account: account });
  });

app.get("/status", (req, res) => {
  res.render("status", { statusText: statusText, statusImage: statusImage });
});

app.route('/delete/:account')
.get((req,res)=>{
  res.render('delete', {account: req.params.account});
})

app.post('/delete',(req, res)=>{
  (async ()=>{
    let data = req.body;

    let accountNumber = data.accountNumber;
    let pin = data.pin;

    //Retrieving Account Information
    let account = await Account.findOne(
      { account_no: accountNumber },
      "account_no pin"
    );

    // Account not found case
    if(account == null){
      fail("Sorry, Account doesn't exist!");
      res.redirect('/status');
      return null;
    }

    //Validating pin
    if (pin != account.pin) {
      fail("Invalid Pin!");
      res.redirect("/status");
      return null;
    }

    // Performing delete

    let del = await Account.deleteOne({account_no: accountNumber});

    if(del.acknowledged == true){
      status('sad', 'Sorry, to see you go!');
    }else{
      fail("Something Went Wrong!");
    }

    res.redirect('/status');


  })();
})


//The below codes are customFunctions

//Below code is for status page

function fail(text){
    statusImage = "fail";
    statusText = text;
}

function success(text){
    statusImage = "success";
    statusText = text;
}

function status(emotion, text){
  statusImage = emotion;
  statusText = text;
}




//Setting Server Listening Port
let port = process.env.PORT;
if(port == null || ""){
    port = 3000;
}

app.listen(port, ()=> console.log("Server started!!"));

