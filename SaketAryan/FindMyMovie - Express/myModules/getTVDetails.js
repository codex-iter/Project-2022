

const axios = require('axios');

exports.getTVDetails = getTVDetails;
async function getTVDetails(url,seasons, similarMoviesArray, cast){
    let myTV = [];
    var v = axios.get(url).then(res => {
        const tv = res.data;
        
        let id = tv.id;
        let in_production = tv.in_production;
        let status = tv.status;
        let title = tv.title;
        let name = tv.name;
        let ratings = tv.vote_average;
        let episodes = tv.number_of_episodes;
        let seasonCount = tv.number_of_seasons;
        let overview = tv.overview;
        let posterURL = 'https://image.tmdb.org/t/p/w500'+ tv.poster_path;
        let release_date = tv.first_air_date;

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
           temp = tv.genres[i].name;
           } catch(e){
               break;
           }
           genre.push(temp);
        }
        
        
        // getting production compinies
        let compinies = [];
        for(let i=0;i<5;i++){
           try{
           temp = tv.production_companies[i].name;
           } catch(e){
               break;
           }
           compinies.push(temp);
        }
        
        //languages spoken
        let languages = [];
        for(let i=0;i<5;i++){
           try{
           temp = tv.spoken_languages[i].english_name;
           } catch(e){
               break;
           }
           languages.push(temp);
        }

         // checking for undefined cases
         if (typeof title == 'undefined') title = name; 

         // rounding off ratings to 2 decimals
         ratings = parseFloat(ratings).toFixed(1);

         myTV = {
            id: id,
            in_production: in_production,
            status: status,
            title: title,
            ratings: ratings,
            episodes: episodes,
            seasonCount: seasonCount,
            overview: overview,
            posterURL: posterURL,
            country: country,
            release_date: release_date,

            genre: genre,
            compinies: compinies,
            languages: languages,
            seasons: seasons,
            similarMoviesArray: similarMoviesArray,
            cast: cast
         }

    })
    .catch(err => {
      console.log('Error: ', err.message);
    }).then(()=>{
        return myTV;
    })
    return v;
}


exports.getSeasons = getSeasons;

async function getSeasons(url){
    let seasons = [];

    var v = axios.get(url).then(res=>{
        const season = res.data;
        let i = 0;  // counter
        while(true){
            let id = season.seasons[i].id;
            if(typeof id == 'undefined') break;

            if(season.seasons[i].season_number== ''){
                i++;
                continue;
            }

            let overview = season.seasons[i].overview;

            let name = season.seasons[i].name.toUpperCase();
            posterURL = 'https://image.tmdb.org/t/p/w500'+ season.seasons[i].poster_path;
            
            let episodes = season.seasons[i].episode_count;
            let release_date = season.seasons[i].air_date

            let seasonObject = {
                id: id,
                name: name,
                overview: overview,
                posterURL: posterURL,
                episodes: episodes,
                release_date: release_date
            }
            seasons.push(seasonObject);
            i++;
        }
    }

    ).catch(e =>{
        console.log('Error: ', e.message);
    }).then(()=>{
        return seasons;
    })
    return v;
}