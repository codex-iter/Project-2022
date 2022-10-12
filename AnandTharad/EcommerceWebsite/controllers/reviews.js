const Product = require("../models/product");
const Review=require("../models/review")

module.exports.createReview= async(req, res)=>{
    const product=await Product.findById(req.params.id);
    const review=new Review(req.body.review);
    review.author= req.user._id;
    
    product.reviews.push(review);
    await review.save();
    if(!product.reviews){
        product.avgrating=0;
    }
    else{
        let c=0, sum=0;
        for(let p of product.reviews) {
            let r=await Review.findById(p);
            sum+=r.rating;
            c++;
        }
        console.log(sum, c);
        product.avgrating=sum/c;
    }

    console.log(product)
    await product.save();
    req.flash("success", "successfully created a new review")
    res.redirect(`/products/${product._id}`);
}

module.exports.deleteReview= async(req, res)=>{
    const {id, reviewId}= req.params
    await Product.findByIdAndUpdate(id, {$pull: {reviews: reviewId}})
    await Review.findByIdAndDelete(reviewId)
    req.flash("success", "successfully deleted review")
    res.redirect(`/products/${id}`)
   
}