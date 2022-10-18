const { Router } = require('express')
const router = Router();
const authcontroller = require('../auth/authController');
const { requireAuth } = require('../middleware/authMiddleware');
const JWT = require('jsonwebtoken');
require('dotenv').config();
const User = require('../models/User');
const path = require('path');




//create a token
const maxAge = 60 * 60 * 24 * 3;
const createToken = (id) => {
    // console.log("hi");
    return JWT.sign({ id }, process.env.HASHING_SECRET_CODE, {
        expiresIn: maxAge
    });
};




router.get('/1', requireAuth, (req, res) => {
    res.render('./tasks/page1.ejs');
})
router.put('/1', requireAuth, (req, res) => {
    // console.log(completed);
    const token = req.cookies.auth;
    // console.log(token);
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, (err, decodedToken) => {
            if (err) {
                // console.log(err.message);
                res.send(err)
                // res.redirect('/login');
            }
            else {
                const idname = decodedToken.id;
                User.find()
                    .then((result) => {
                        result.forEach((user) => {
                            // console.log(idname);
                            User.findByIdAndUpdate(
                                idname,
                                { "completed.0": true },
                                (err, docs) => {
                                    if (err)
                                        console.log("err" + err);
                                    console.log(docs);
                                })
                        });
                    })
                    .then(() => {
                        res.json({ success: true });
                    })
            }
        })
    }
    else {
        res.send('Bad request. Error');
    }

})
router.get('/2', requireAuth, (req, res) => {
    res.render('./tasks/page2.ejs');
})
router.put('/2', requireAuth, (req, res) => {
    // console.log(completed);
    const token = req.cookies.auth;
    // console.log(token);
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, (err, decodedToken) => {
            if (err) {
                // console.log(err.message);
                res.send(err)
                // res.redirect('/login');
            }
            else {
                const idname = decodedToken.id;
                User.find()
                    .then((result) => {
                        result.forEach((user) => {
                            // console.log(idname);
                            User.findByIdAndUpdate(
                                idname,
                                { "completed.1": true },
                                (err, docs) => {
                                    if (err)
                                        console.log("err" + err);
                                    console.log(docs);
                                })
                        });
                    })
                    .then(() => {
                        res.json({ success: true });
                    })
            }
        })
    }
    else {
        res.send('Bad request. Error');
    }

})
router.get('/3', requireAuth, (req, res) => {
    res.render('./tasks/page3.ejs');
})

router.put('/3', requireAuth, (req, res) => {
    // console.log(completed);
    const token = req.cookies.auth;
    // console.log(token);
    if (token) {
        JWT.verify(token, process.env.HASHING_SECRET_CODE, (err, decodedToken) => {
            if (err) {
                // console.log(err.message);
                res.send(err)
                // res.redirect('/login');
            }
            else {
                const idname = decodedToken.id;
                User.find()
                    .then((result) => {
                        result.forEach((user) => {
                            // console.log(idname);
                            User.findByIdAndUpdate(
                                idname,
                                { "completed.2": true },
                                (err, docs) => {
                                    if (err)
                                        console.log("err" + err);
                                    console.log(docs);
                                })
                        });
                    })
                    .then(() => {
                        res.json({ success: true });
                    })
            }
        })
    }
    else {
        res.send('Bad request. Error');
    }

})

module.exports = router;