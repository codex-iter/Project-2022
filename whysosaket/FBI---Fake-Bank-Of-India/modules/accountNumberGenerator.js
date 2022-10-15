
exports.accountNumber = accountNumber;

// Function to generate random number 
function accountNumber() { 
    return Math.floor(Math.random() * (999999999 - 100000000)) + 100000000;
}