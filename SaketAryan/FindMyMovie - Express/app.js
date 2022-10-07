const express = require('express');
const bodyParser = require('body-parser');
const ejs = require('ejs');
const https = require('https');
const axios = require('axios');
const { get, request } = require('http');
const { response } = require('express');

// My custom modules
const similar = require(__dirname+"/myModules/getSimilar.js");
const getItems = require(__dirname+"/myModules/getItems.js");
const getMovieDetails = require(__dirname+"/myModules/getMovieDetails.js");
const getCast = require(__dirname+'/myModules/getCast.js');
const getTVDetails = require(__dirname+'/myModules/getTVDetails.js');

const API_KEY = '350dfd4ff79fab6f95562ce70ac31dc9';

const app = express();
app.set('view engine', 'ejs');

app.use(bodyParser.urlencoded({extended: true}));
app.use(express.static('public'));

//Setting up the home route
app.get('/', (request, response)=>{

  const top_rated_movies = 'https://api.themoviedb.org/3/movie/top_rated?api_key='+API_KEY;
  const popular_movies = 'https://api.themoviedb.org/3/movie/popular?api_key='+API_KEY;

  const top_rated_tv = 'https://api.themoviedb.org/3/tv/top_rated?api_key='+API_KEY;
  const popular_tv = 'https://api.themoviedb.org/3/tv/popular?api_key='+API_KEY;

  const movie_latest = 'https://api.themoviedb.org/3/movie/upcoming?api_key='+API_KEY;
  const movie_now = 'https://api.themoviedb.org/3/movie/now_playing?api_key='+API_KEY;
  const tv_latest = 'https://api.themoviedb.org/3/tv/airing_today?api_key='+API_KEY;

  let array = [];
  let nameArray = ['Top Rated Movies', 'Popular Movies', 'Top Rated TV Shows', 'Popular TV Shows', '','',''];
  let type = ['movie', 'movie', 'tv', 'tv', 'movie', 'movie', 'tv'];

  let top_rated_movies_array = [];
  let popular_movies_array = [];
  let top_rated_tv_array = [];
  let popular_tv_array = [];

  let movie_latest_array = [];
  let movie_now_array = [];
  let tv_latest_array = [];
  
  (async()=>{

    top_rated_movies_array = await similar.getSimilar(top_rated_movies).then(data=> {return data});
    array.push(top_rated_movies_array);

    popular_movies_array = await similar.getSimilar(popular_movies).then(data=> {return data});
    array.push(popular_movies_array);

    top_rated_tv_array = await similar.getSimilar(top_rated_tv).then(data=> {return data});
    array.push(top_rated_tv_array);

    popular_tv_array = await similar.getSimilar(popular_tv).then(data=> {return data});
    array.push(popular_tv_array);

    // for carousel
    movie_latest_array = await similar.getSimilar(movie_latest).then(data=> {return data});
    array.push(movie_latest_array);

    movie_now_array = await similar.getSimilar(movie_now).then(data=> {return data});
    array.push(movie_now_array);

    tv_latest_array = await similar.getSimilar(tv_latest).then(data=> {return data});
    array.push(tv_latest_array);

    response.render('index',{array: array, nameArray: nameArray, type: type} );
  })();
})

// Get extra pages
app.get('/:type/:list', (request, response)=>{
    let l = request.params.list;
    let t = request.params.type;
    let url;
    let array = [];
    let title = '';

    switch(l){
      case 'top-movies': {
        url = 'https://api.themoviedb.org/3/trending/movie/day?api_key='+API_KEY;
        title = 'Trending Movies';
        break;
      }

      case 'top-tv-show':{
        url = 'https://api.themoviedb.org/3/trending/tv/day?api_key='+API_KEY;
        title = 'Trending TV Shows';
        break;
      }

      case 'upcoming-movies':{
        url = 'https://api.themoviedb.org/3/movie/upcoming?api_key='+API_KEY;
        title = 'Upcoming Movies';
        break;
      }

      case 'upcoming-tv-shows':{
        url = ""
        break;
      }

      default:{
        response.render('listResults', {trending: array, title: title});
      }
    }
    (async()=>{
      array = await getItems.getItems(url, t).then(data=> {return data});
      response.render('listResults',{trending: array, title: title} );
    })();
})

app.get('/about', (request, response)=>{
    response.render('about');
})


// Searching Movies
app.post('/search', (request, response)=>{
    var query = request.body.search;
    const url = 'https://api.themoviedb.org/3/search/multi?api_key='+API_KEY+'&language=en-US&page=1&query='+query;
    let array = [];   
    (async()=>{
      array = await getItems.getItems(url).then(data=> {return data});
      response.render('search',{trending: array} );
    })();
})


// Individual Movie Page
app.get('/movie/details/:id', (request, response)=>{
    let id = request.params.id;
    const url = 'https://api.themoviedb.org/3/movie/'+id+'?api_key='+API_KEY+'&language=en-US';
    const similarMoviesURL = "https://api.themoviedb.org/3/movie/"+id+"/similar?api_key="+API_KEY+"&language=en-US&page=1";
    const castURL = 'https://api.themoviedb.org/3/movie/'+id+'/credits?api_key='+API_KEY+'&language=en-US';


    let myMovie;
    let similarMoviesArray= [];
    let cast = [];

    (async()=>{
      similarMoviesArray = await similar.getSimilar(similarMoviesURL).then(data=> {return data});
      cast = await getCast.getCast(castURL).then(data=> {return data});
      myMovie = await getMovieDetails.getMovieDetails(url, similarMoviesArray, cast).then(data=> {return data});
      response.render('movie',{myMovie: myMovie} );
    })();
    
})

app.get('/tv/details/:id', (request, response)=>{
    let id = request.params.id;
    const url = 'https://api.themoviedb.org/3/tv/'+id+'?api_key='+API_KEY+'&language=en-US';
    const similarTVURL = "https://api.themoviedb.org/3/tv/"+id+"/similar?api_key="+API_KEY+"&language=en-US&page=1";
    const castURL = 'https://api.themoviedb.org/3/tv/'+id+'/credits?api_key='+API_KEY+'&language=en-US';

    let myTV;
    let similarTVArray= [];
    let cast = [];
    let seasons = [];

    (async()=>{
      similarTVArray = await similar.getSimilar(similarTVURL).then(data=> {return data});
      cast = await getCast.getCast(castURL).then(data=> {return data});
      seasons = await getTVDetails.getSeasons(url).then(data=> {return data});
      myTV = await getTVDetails.getTVDetails(url,seasons, similarTVArray, cast).then(data=> {return data});
      response.render('tvshows',{myMovie: myTV} );
    })();
})

app.get('*', function(req, res){
  res.status(404).render('error');
});


//Setting Server Listening Port
app.listen(3000, ()=>{
    console.log("Server Started at PORT: 3000");
})
