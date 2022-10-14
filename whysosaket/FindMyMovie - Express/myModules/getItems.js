

const axios = require('axios');
exports.getItems = getItems;

async function getItems(url, type){
    let array = []; 
    var v = axios.get(url).then(res => {
        const movies = res.data;
         // here resetting the array so no duplicate items

         for(let i=0; i<20; i++){
         let id = movies.results[i].id;
         if(typeof movies.results[i].id == 'undefined') break;    //end of results case
         let title = movies.results[i].title;
         let name = movies.results[i].name;
         let overview = movies.results[i].overview;
         let ratings = movies.results[i].vote_average;
         let posterURL = 'https://image.tmdb.org/t/p/w500'+ movies.results[i].poster_path;
         let mediaType = movies.results[i].media_type;

         if(typeof mediaType == 'undefined') mediaType = type;
    
         // checking for undefined cases
         if (typeof title == 'undefined') title = name;
    
         // rounding off ratings to 2 decimals
         ratings = parseFloat(ratings).toFixed(1);
    
         let trendingObject = {
             id: id,
             title: title,
             overview: overview,
             ratings: ratings,
             posterURL: posterURL,
             mediaType: mediaType
         }
    
         array.push(trendingObject);
        }
        }
      )
      .catch(err => {
        console.log('Error: ', err.message);
      }).then(()=>{
        return array;
      })
      return v;
    // end
}