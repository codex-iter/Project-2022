const JWT = require('jsonwebtoken');
const path = require('path');
const User = require('../models/User');
require('dotenv').config();

const requireAuth = (req, res, next) => {
    const token = req.cookies.auth;

    // check json web token exists and is verified
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, (err, decodedToken) => {
            if (err) {
                console.log(err.message);
                res.redirect('/login');
            }
            else {
                console.log(decodedToken);
                next();
            }
        })
    }
    else {
        res.redirect('/login');
    }
}

// check current user
const checkUser = (req, res, next) => {
    const token = req.cookies.auth;
    // console.log(token);
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, async (err, decodedToken) => {
            if (err) {
                console.log(err.message);
                res.locals.user = null;
                next();
            }
            else {
                console.log(decodedToken);
                let user = await User.findById(decodedToken.id);
                res.locals.user = user;
                next();
            }
        })
    }
    else {
        res.locals.user = null;
        next();
    }

}
const preventAuth= (req, res, next) => {
    const token = req.cookies.auth;

    // check json web token exists and is verified
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, (err, decodedToken) => {
            if (err) {
                console.log(err.message);
                next()
            }
            else {
                console.log(decodedToken);
                res.redirect('/dashboard');
            }
        })
    }
    else {
        next();
    }
}


module.exports = { requireAuth, checkUser, preventAuth };