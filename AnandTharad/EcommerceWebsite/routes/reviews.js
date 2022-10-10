const express = require("express")
const router = express.Router({ mergeParams: true })

const catchAsync = require("../Utils/catchAsync");
const ExpressError = require("../Utils/ExpressError")

//middleware
const { validateReview, isLoggedIn, isReviewAuthor } = require("../middleware")

//controllers
const reviews = require("../controllers/reviews")

//Creating a review
router.post("/", isLoggedIn, validateReview, catchAsync(reviews.createReview))

//deleting a review
router.delete("/:reviewId", isLoggedIn, isReviewAuthor, catchAsync(reviews.deleteReview))

module.exports = router;