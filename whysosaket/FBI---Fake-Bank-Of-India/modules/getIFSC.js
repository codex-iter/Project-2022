
exports.getIFSC = getIFSC;

function getIFSC(array, target){
    for(let i =0; i<array.length;i++){
        if(target == array[i].branch){
            return array[i].ifsc;
        }
    }
    return 123456;
}