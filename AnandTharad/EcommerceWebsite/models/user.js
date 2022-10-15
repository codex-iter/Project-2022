const mongoose= require("mongoose")
const passportLocalMongoose= require("passport-local-mongoose")

const Schema= mongoose.Schema;

const userSchema= new Schema({
    email: {
        type: String,
        required: true,
        unique: true
    }
});

userSchema.plugin(passportLocalMongoose) //it adds username(validates unique) password to the schema without actually mentioning them


module.exports= mongoose.model("User", userSchema)