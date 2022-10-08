const axios = require('axios');
exports.getCast = getCast;

async function getCast(url){
    let array = []; 
    var v = axios.get(url).then(res => {
        const cast = res.data;
            let id, department, name, popularity, profile_pic, characters, gender;
            for(let i=0; i<1000; i++){
                id = cast.cast[i].id;
                if(typeof id == 'undefined') break;
                department = cast.cast[i].known_for_department;
                name = cast.cast[i].name;
                popularity = cast.cast[i].popularity;
                profile_pic = 'https://image.tmdb.org/t/p/w500'+cast.cast[i].profile_path;
                characters = cast.cast[i].character;
                gender = cast.cast[i].gender;

                //creating object of the cast
                let castObject = {
                    id: id,
                    department: department,
                    name: name,
                    popularity: popularity,
                    profile_pic: profile_pic,
                    characters: characters,
                    gender: gender
                }
                array.push(castObject);
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