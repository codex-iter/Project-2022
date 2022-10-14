
exports.getMovieDetails = getMovieDetails;
const axios = require('axios');

async function getMovieDetails(url, similarMoviesArray, cast){
    let myMovie = [];

   var v = axios.get(url).then(res => {
        const movie = res.data;

         let id = movie.id;
         let title = movie.title;
         let name = movie.name;
         let overview = movie.overview;
         let ratings = movie.vote_average;
         let posterURL = 'https://image.tmdb.org/t/p/w500'+ movie.poster_path;
         let originalLanguage = movie.original_language;

         let runtime = movie.runtime;
         let release_date = movie.release_date;
         let revenue = movie.revenue;
         let status = movie.status;

        let country;
        try{
            country = tv.production_countries[0].name;
        }catch(e){
            country = "Null"
        }

         // getting genre
         let genre = [];
         let temp = "";
         for(let i=0;i<5;i++){
            try{
            temp = movie.genres[i].name;
            } catch(e){
                break;
            }
            genre.push(temp);
         }
         
         // getting production compinies
         let compinies = [];
         for(let i=0;i<5;i++){
            try{
            temp = movie.production_companies[i].name;
            } catch(e){
                break;
            }
            compinies.push(temp);
         }
         
         //languages spoken
         let languages = [];
         for(let i=0;i<5;i++){
            try{
            temp = movie.spoken_languages[i].english_name;
            } catch(e){
                break;
            }
            languages.push(temp);
         }
          
         // checking for undefined cases
         if (typeof title == 'undefined') title = name;   
    
         // rounding off ratings to 2 decimals
         ratings = parseFloat(ratings).toFixed(1);

         myMovie = {
            id: id,
            title: title,
            overview: overview,
            ratings: ratings,
            posterURL: posterURL,
            runtime: runtime,
            release_date: release_date,
            revenue: revenue,
            status: status,
            country: country,
            compinies: compinies,
            genre: genre,
            languages: languages,
            similarMoviesArray: similarMoviesArray,
            cast: cast
         }

    })
    .catch(err => {
      console.log('Error: ', err.message);
    }).then(()=>{
        return myMovie;
    })
  return v;
}