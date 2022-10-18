const express = require('express')
const { Router } = require('express')
const router = Router();
const authcontroller = require('../auth/authController');
const JWT = require('jsonwebtoken');
require('dotenv').config();

router.get('/signup', authcontroller.signup_get);
router.post('/signup', authcontroller.signup_post);

router.get('/login', authcontroller.login_get);
router.post('/login', authcontroller.login_post);
router.get('/logout', authcontroller.logout_get);

router.get('/signup/img', authcontroller.img_get);
router.post('/signup/img', authcontroller.img_post);

router.put('/dashboard', authcontroller.dashboard_put);
module.exports = router;