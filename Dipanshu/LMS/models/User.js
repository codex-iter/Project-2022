const mongoose = require('mongoose');
const bcrypt = require('bcrypt');

const { isEmail } = require('validator')
const userSchema = new mongoose.Schema({
    // img: {
    //     type: String,
    //     required: true,
    // },
    fullname: {
        type: String,
    },
    username: {
        type: String,
        required: [true, 'Enter the username'],
        unique: true,
        lowercase: true
    },
    email: {
        type: String,
        required: [true, 'Enter the email'],
        unique: true,
        lowercase: true,
        validate: [isEmail, 'Please enter a valid email']
    },
    password: {
        type: String,
        required: [true, 'Enter the password'],
        minlength: [6, 'Minimum length of password is 6']
    },
    image: {
        type: String,
    },
    completed: {
        type: Array,
    },

});

//fire a function before a user is saved to db
userSchema.pre(['save', 'updateOne', 'update'], async function (next) {
    const salt = await bcrypt.genSalt();
    // console.log("salt: ", salt);
    // console.log("pass: ", this.password);
    this.password = await bcrypt.hash(this.password, salt);
    console.log('password:' ,this.password);
    next();
})
// 


// userSchema.pre('updateOne', async function (next) {
//     // console.log(this.password);
//     console.log("preupdate");
//     const salt = await bcrypt.genSalt();
//     // console.log(salt);
//     this.password = await bcrypt.hash(this.password, salt);
//     console.log("postsave\n\n\n");
//     next();
// })

//static method to login user
userSchema.statics.login = async function (email, password) {
    const user = await this.findOne({ email });
    if (user) {
        const auth = await bcrypt.compare(password, user.password);
        if (auth) {
            return user;
        }
        throw Error('Incorrect password');
    }
    throw Error('Incorrect email');
}

const User = mongoose.model('user', userSchema);
// console.log(User);
module.exports = User;