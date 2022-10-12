const mongoose = require('mongoose');

const { title, image } = require('./seedHelpers');
const Campground = require('../models/product');

mongoose.connect('mongodb+srv://admin:QdCZNIIKghOPFw6v@cluster0.aiq2f0x.mongodb.net/Ecommerce?retryWrites=true&w=majority');

const db = mongoose.connection;

db.on("error", console.error.bind(console, "connection error:"));
db.once("open", () => {
    console.log("Database connected");
});

const sample = array => array[Math.floor(Math.random() * array.length)];


const seedDB = async () => {
    // await Campground.deleteMany({});
        for(let i=0; i<title.length;i++)
        Campground.updateOne({$set: {avgrating: Math.random()*4+1}}).save();

        
    }

seedDB().then(() => {
    mongoose.connection.close();
})