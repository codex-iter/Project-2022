const express = require("express")
const router = express.Router()
const catchAsync = require("../Utils/catchAsync");
//Middleware
const multer  = require('multer')
const {storage}= require("../cloudinary");
const upload = multer({ storage })
const { isLoggedIn, isAuthor, validateProduct } = require("../middleware");

//controllers
const products=require("../controllers/products")

//Display of all Campgrounds
router.get("/", catchAsync(products.index)) 

//Form To create a new Campground
router.get("/new", isLoggedIn, products.renderNewForm)
//Sending form data for a new Campground
router.post("/" ,isLoggedIn,upload.array("images[]"), validateProduct ,catchAsync(products.createProduct))



//searching campground by id
router.get("/:id", catchAsync(products.showProduct))

//Editing info of a campground
router.get("/:id/edit", isLoggedIn, isAuthor, catchAsync(products.renderEditForm))
router.put("/:id", isLoggedIn, isAuthor, upload.array("images[]") ,validateProduct, catchAsync(products.updateProduct))

//Delete Campground
router.delete("/:id", isLoggedIn, isAuthor, catchAsync(products.deleteProduct))

module.exports = router;