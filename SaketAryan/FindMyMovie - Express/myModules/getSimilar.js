const axios = require('axios');
exports.getSimilar = getSimilar;

async function getSimilar(url){
  let similarMoviesArray = [];
  var v = axios.get(url).then(simResponse=>{
    const similarMovies = simResponse.data;
    for(let j=0;j<20;j++){
    let id = similarMovies.results[j].id;
    if(typeof id == 'undefined') break;
    let title = similarMovies.results[j].title;
    let name = similarMovies.results[j].name;
    let posterURL = 'https://image.tmdb.org/t/p/w500'+ similarMovies.results[j].poster_path;

    // checking for undefined cases
    if (typeof title == 'undefined') title = name;

    let similarMoviesObject={
      id: id,
      title: title,
      posterURL: posterURL
    }
    similarMoviesArray.push(similarMoviesObject);
    }
  }).then(()=>{
    return similarMoviesArray;
  })
  return v;
}