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
    for (let i = title-2; i < title.length; i++) {
        const price=Math.floor(Math.random()*20)+10;
        const camp = new Campground({
            author: "6336914c422e0799b317cbf8",
            title: `${title[i]}`,
            images: [
                {
                  url:  `${image[i].url}`,
                  filename: `${image[i].url.slice(image[i].url.indexOf("Website"))}`
                
                }
              ],
            description: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Nulla sunt illum nostrum, quod suscipit tempore fugiat? Iste, nam, omnis odio, necessitatibus reprehenderit a laudantium deleniti natus incidunt tempore nulla quia?",
            price,
            category,
        })
        await camp.save();
    }
}

seedDB().then(() => {
    mongoose.connection.close();
})