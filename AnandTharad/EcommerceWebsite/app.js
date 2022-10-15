if(process.env.NODE_ENV !== "production"){
    require("dotenv").config();
}


const express = require("express");
const path = require("path");
const mongoose = require("mongoose");
const methodOverride = require("method-override");
const ejsMate = require("ejs-mate")
const ExpressError = require("./Utils/ExpressError")
const productRoutes = require("./routes/product")
const reviewsRoutes = require("./routes/reviews")
const session = require("express-session")
const flash = require("connect-flash")
const passport = require("passport")
const LocalStrategy = require("passport-local")
const User= require("./models/user")
const userRoutes= require("./routes/users")
const fileUpload = require('express-fileupload');

const app=express()



mongoose.connect("mongodb+srv://admin:QdCZNIIKghOPFw6v@cluster0.aiq2f0x.mongodb.net/Ecommerce?retryWrites=true&w=majority");

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error"));
db.once("open", () => {
    console.log("Database connected");
})

app.engine("ejs", ejsMate);
app.set("view engine", "ejs");
app.set("views", path.join(__dirname, "views"))

app.use(express.urlencoded({ extended: true }))
app.use(methodOverride("_method"))
app.use(express.static(path.join(__dirname, "public")))

const sessionConfig = {
    secret: "thisshouldbeabettersecret",
    resave: false,
    saveUninitialized: true,
    cookie: {
        httpOnly: true,
        expires: Date.now() + (1000 * 60 * 60 * 24 * 7), //expires after 1 week. Date.now() returns in miliseconds
        maxAge: 1000 * 60 * 60 * 24 * 7

    }
}
app.use(session(sessionConfig))
app.use(flash())

app.use(passport.initialize())
app.use(passport.session())
passport.use(new LocalStrategy(User.authenticate()))

passport.serializeUser(User.serializeUser())
passport.deserializeUser(User.deserializeUser())

app.use((req, res, next) => {
    
    res.locals.currentUser= req.user;
    res.locals.success = req.flash("success")
    res.locals.error = req.flash("error")
    next()
})
app.use(fileUpload());

app.use("/", userRoutes)
app.use("/products", productRoutes)
app.use("/products/:id/reviews", reviewsRoutes)

//Home page




app.all("*", (req, res, next) => {
    next(new ExpressError("page not found", 404))
})

app.use((err, req, res, next) => {
    const { statusCode = 500 } = err
    if (!err.message) err.message = "Something went wrong"
    res.status(statusCode).render("error", { err })
})

app.listen(3000, () => {
    console.log("listening to port 3000");
})