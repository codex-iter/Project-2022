$(".searchBar").focusin(function(){
    $(this).animate({
        top: '25%',
        width: '40vw',
        right: '30vw',
        height: "10vh",
        fontSize: "30px"
    }, "slow");
    $(".searchIcon").animate({
        right: '31vw',
        top: '26.8%',
        width: "50px",
        height: "50px"
    }, "slow");
});

$(".searchBar").focusout(function(){
    $(this).animate({
        top: '0',
        width: '20vw',
        right: '40vw',
        height: "5vh",
        fontsize: "20px"
    }, "slow");
    $(".searchIcon").animate({
        right: '41vw',
        top: '.8%',
        width: "25px",
        height: "25px"
    }, "slow");
});

$(".searchBarForm").submit(function(event){
    event.preventDefault();

    $.ajax({
        type:'POST',
        url: '/ajax/get_track_name/',
        data:{
            trackName: $('.searchBar').val(),
            csrfmiddlewaretoken: $('input[name=csrfmiddlewaretoken]').val()
        },
        success:function(trackPath){
            var path = trackPath.trackPath;
            $("#audio").attr("src", `/static/${path}`);
        }
    });

});
