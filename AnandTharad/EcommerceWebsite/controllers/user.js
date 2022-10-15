const User=require("../models/user")
const Product = require("../models/product");

module.exports.landing=async(req, res)=>{
    const product=await Product.find({"avgrating": {$gt : 4.5}})
    res.render("landingpage", {product})
}

module.exports.renderNewUserForm=(req, res)=>{
    res.render("users/register")
}
module.exports.registerNewUser=async(req, res)=>{
    try{
    const{email, username, password}= req.body;
    const user= new User({email, username})
    const registeredUser=await User.register(user, password);
    req.login(registeredUser, err=>{
        if(err) return next(err);
        req.flash("success",`Welcome to Website, ${user.username}`)
        res.redirect("/products")
    });
    } catch(e){
        req.flash("error", e.message)
        res.redirect("register")
    }
}
module.exports.renderLoginForm=(req, res)=>{
    res.render("users/login")
}
module.exports.loginUser=async(req, res)=>{
    req.flash("success", `Welcome back to Website, ${req.user.username}`)
    const redirectUrl= req.session.returnTo || '/products'
    delete req.session.returnTo
    res.redirect(redirectUrl)
}
module.exports.logoutUser=(req, res, next) => {
    req.logout(function(err){
        if(err){
            return next(err)
        }
        res.redirect('/products');
        req.flash('success', "Goodbye!");
    });
}