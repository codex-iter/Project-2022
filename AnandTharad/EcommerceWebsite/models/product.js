const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const Review = require("./review")

const ImageSchema= new Schema( {
    url: String,
    filename: String
})
ImageSchema.virtual("thumbnail").get(function(){
    return this.url.replace("/upload", "/upload/w_200,h_200")
})
const ProductSchema = new Schema({
    title: String,
    price: Number,
    description: String,
    category: [{
        type: String
    }],
    images: [ImageSchema],
    author: {
        type: Schema.Types.ObjectId,
        ref: "User"
    },
    reviews: [{
        type: Schema.Types.ObjectId,
        ref: "Review"
    }],
    avgrating: {
        type: Number,
        default: 0
    }
})
//deleting all related reviews of a campground when it is deleted
ProductSchema.post("findOneAndDelete", async function (doc) {
    if (doc) {
        await Review.deleteMany({
            _id: {
                $in: doc.reviews
            }
        })
    }
})

module.exports = mongoose.model("Product", ProductSchema)