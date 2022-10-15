const express = require("express")
const router = express.Router();

const catchAsync = require("../Utils/catchAsync")
const passport = require("passport")

const users = require("../controllers/user")

router.route("/landingpage")
.get(catchAsync(users.landing))

router.route("/register")
    .get(users.renderNewUserForm)
    .post(catchAsync(users.registerNewUser))

router.route("/login")
    .get(users.renderLoginForm)
    .post(passport.authenticate("local", { failureFlash: true, failureRedirect: "/login", keepSessionInfo: true }), catchAsync(users.loginUser))

router.get('/logout', users.logoutUser)

module.exports = router;