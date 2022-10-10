const Product = require("../models/product");
const { cloudinary } = require("../cloudinary");


module.exports.index = async (req, res, next) => {
    const category=req.query.category;
    const filter=req.query.filter;
    if(filter){
        const products=await Product.find({"avgrating": {$gt : 4.5}})
        res.render("shop/products", {products})
    }
    if(category){
        const products= await Product.find({category})
        res.render("shop/products", { products })
    }
    const products = await Product.find({});
    res.render("shop/products", { products })
}

module.exports.renderNewForm = (req, res) => {
    res.render("shop/new")
}

module.exports.createProduct = async (req, res, next) => {
    const product = new Product(req.body.product);
    console.log(req.body);
    product.images = req.body.images.map(f => ({ url: f.path, filename: f.filename }))
    product.author = req.user._id;
    await product.save();
    req.flash("success", "successfully created a new campground")
    res.redirect(`/products/${product._id}`)
}
const parentCategories=["home", "kitchen", "office", "kids", "fashion", "ArtAndCraft"]
const subCategories=["men", "women"]
module.exports.showProduct = async (req, res) => {
    const id = req.params.id;
    const product = await Product.findById(id).populate(
        {
            path: "reviews",
            populate: { path: "author" }
        }
    ).populate("author")
    if (!product) {
        req.flash("error", "cannot find the product")
        return res.redirect("/products")
    }
    for(let c of product.category){
        if(subCategories.includes(c)){
            if(c=="men") {const similarproducts=await Product.find({category: c})
            res.render("shop/product", { product, similarproducts })}
            else {const similarproducts=await Product.find({category: c})
            res.render("shop/product", { product, similarproducts })}
        }
    }
    const productcategory=product.category[1];
    console.log(productcategory)
    const similarproducts=await Product.find({category: productcategory})
    
    res.render("shop/product", { product, similarproducts })
}

module.exports.renderEditForm = async (req, res) => {
    const id = req.params.id;
    const product = await Product.findById(id)
    console.log(product)
    if (!product) {
        req.flash("error", "cannot find the campground")
        return res.redirect("/products")
    }
    res.render("shop/edit", { product })
}

module.exports.updateProduct = async (req, res) => {
    const { id } = req.params;
    const product = await Product.findByIdAndUpdate(id, { ...req.body.product });
    const imgs = req.files.map(f => ({ url: f.path, filename: f.filename }));
    product.images.push(...imgs);
    await product.save();
    if (req.body.deleteImages) {
        for (let filename of req.body.deleteImages) {
            await cloudinary.uploader.destroy(filename);
        }
        await product.updateOne({ $pull: { images: { filename: { $in: req.body.deleteImages } } } })
    }
    req.flash('success', 'Successfully updated campground!');
    res.redirect(`/products/${product._id}`)
}

module.exports.deleteProduct = async (req, res) => {
    const id = req.params.id;
    await Product.findByIdAndDelete(id)
    req.flash("success", "successfully deleted product")
    res.redirect("/products")
}