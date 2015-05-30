var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

/* GET posts page */
router.get('/posts', function(req, res) {
  var db = req.db;
  var collection = db.get('postcollection');
  collection.find({},{},function(e,docs){
    res.render('posts', {
      "postlist" : docs
    });
  });
});

/* GET Add Post page. */
router.get('/addpost', function(req, res) {
  res.render('addpost', { title: 'Add New Post' });
});

/* POST to Add Post */
router.post('/addpost', function(req, res) {
  // Set our internal DB variable
  var db = req.db;
  // Get our form values. These rely on the "name" attributes
  var postTitle = req.body.posttitle;
  var postContent = req.body.postcontent;
  // Set our collection
  var collection = db.get('postcollection');
  // Submit to the DB
  collection.insert({
    "title" : postTitle,
    "content" : postContent
  }, function (err, doc) {
    if (err) {
      // If it failed, return error
      res.send("There was a problem adding the information to the database.");
    }
    else {
      // If it worked, set the header so the address bar doesn't still say /adduser
      res.location("posts");
      // And forward to success page
      res.redirect("posts");
    }
  });
});

// Tutorial-based user stuff.

/* GET Userlist page. */
router.get('/userlist', function(req, res) {
  var db = req.db;
  var collection = db.get('usercollection');
  collection.find({},{},function(e,docs){
    res.render('userlist', {
      "userlist" : docs
    });
  });
});

/* GET New User page. */
router.get('/newuser', function(req, res) {
  res.render('newuser', { title: 'Add New User' });
});

/* POST to Add User Service */
router.post('/adduser', function(req, res) {
  // Set our internal DB variable
  var db = req.db;
  // Get our form values. These rely on the "name" attributes
  var userName = req.body.username;
  var userEmail = req.body.useremail;
  // Set our collection
  var collection = db.get('usercollection');
  // Submit to the DB
  collection.insert({
    "username" : userName,
    "email" : userEmail
  }, function (err, doc) {
    if (err) {
      // If it failed, return error
      res.send("There was a problem adding the information to the database.");
    }
    else {
      // If it worked, set the header so the address bar doesn't still say /adduser
      res.location("userlist");
      // And forward to success page
      res.redirect("userlist");
    }
  });
});

module.exports = router;
