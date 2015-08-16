function createSlider(){
    var slider = document.getElementById('slider');

    noUiSlider.create(slider, {
        start: [20, 80],
        connect: true,
        range: {
            'min': 0,
            'max': 100
        }
    });
}
function appear(elm, i, step, speed){
    var t_o;
    //initial opacity
    i = i || 0;
    //opacity increment
    step = step || 5;
    //time waited between two opacity increments in msec
    speed = speed || 50;

    t_o = setInterval(function(){
        //get opacity in decimals
        var opacity = i / 100;
        //set the next opacity step
        i = i + step;
        if(opacity > 1 || opacity < 0){
            clearInterval(t_o);
            //if 1-opaque or 0-transparent, stop
            $('#active-zone').html('<img src="img/loading.gif" height="150" width="150" /> ');
            return;
        }
        //modern browsers
        elm.style.opacity = opacity;
        //older IE
        elm.style.filter = 'alpha(opacity=' + opacity*100 + ')';
    }, speed);


}


    $(document).ready(function(){

        $("button").click(function(e){
                e.preventDefault();
                var form = $(this);
                var post_url = 'hello';
                var post_data = form.serialize();
                //$('#active-zone').append('<img style=" background-color:transparent; z-index: 100;  position: relative; top: 50%;transform: translateY(-180%);" src="img/loading.gif"  height="43" width="43"  /> ');
                $.ajax({
                    type: 'POST',
                    url: post_url,
                    data: post_data,
                    success: function(msg) {
                        $('#active-zone').fadeOut(800, function(){
                            $('#active-zone').html(msg).fadeIn().delay(2000);

                        });
                    }
                });
            });

    });
