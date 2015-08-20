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

function getItem(id)
{

        var form = $(this);
        var post_url = 'getItem';
        var post_data = id;
        $('#active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
        //$('#top-layer').height($('#active-zone')[0].offsetHeight);
        //var topOffset = $('#active-zone')[0].offsetTop;
        //$('#top-layer').offset({top: topOffset});
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

}

function addToCart(id, element)
{
    var form = $(this);
    var post_url = 'addToCart';
    var post_data = id;
    $.ajax({
        type: 'POST',
        url: post_url,
        data: post_data,
        success: function(msg) {
            var resp = JSON.parse(msg);
            $('#cart-notif').fadeOut(300, function(){
                $('#cart-notif').html('У ВАС N БУКЕТОВ НА СУММУ ' + resp.amount + ' РУБ.').fadeIn().delay(800);
            });
            element.html('Товар в корзине').fadeIn().delay(800);
        }
    });
}


function getAllItems()
{

    var form = $(this);
    var post_url = 'getAllItems';
    var post_data = {'itemWidth' : $(".container")[0].offsetWidth/3};
    $.ajax({
        type: 'POST',
        url: post_url,
        data: post_data,
        success: function(msg) {
            $('#marketing-active-zone').fadeOut(800, function(){
                $('#marketing-active-zone').html(msg).fadeIn().delay(2000);

            });
            initGallery()
        }
    });

}
var totalWidth = 0;
function initGallery()
{
    // Set general variables
    // ====================================================================


//    var containerWidth = $(".container")[0].offsetWidth;
//    $(".gallery__item").width(containerWidth/3);
//
//    // Total width is calculated by looping through each gallery item and
//    // adding up each width and storing that in `totalWidth`
//    $(".gallery__item").each(function(){
//        totalWidth = totalWidth + $(this).outerWidth(true);
//    });
//
//    // The maxScrollPosition is the furthest point the items should
//    // ever scroll to. We always want the viewport to be full of images.
//    var maxScrollPosition = totalWidth - $(".gallery-wrap").outerWidth();
//
//
//
//    // Basic HTML manipulation
//    // ====================================================================
//    // Set the gallery width to the totalWidth. This allows all items to
//    // be on one line.
//    $(".gallery").width(totalWidth);
}


    $(document).ready(function(){

        $("button").click(function(e){
                e.preventDefault();
                var form = $(this);
                var post_url = 'getItem';
                var post_data = form.serialize();
                $('#active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
                //$('#top-layer').height($('#active-zone')[0].offsetHeight);
                //var topOffset = $('#active-zone')[0].offsetTop;
                //$('#top-layer').offset({top: topOffset});
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

// ---------------------------- MARKETING GALLERY--------------------------

function goPrev(){
    // Set target item to the item before the active item
    var $targetItem = $(".gallery__item--active").prev();
    toGalleryItem($targetItem);
};

// When the next button is clicked
// ====================================================================
function goNext(){
    // Set target item to the item after the active item
    var $targetItem = $(".gallery__item--active").next();
    toGalleryItem($targetItem);
};

var maxScrollPosition = 0;

// This is the core function that animates to the target item
// ====================================================================
function toGalleryItem($targetItem){

    if (maxScrollPosition == 0){
    $(".gallery__item").each(function(){
        maxScrollPosition = maxScrollPosition + $(this).outerWidth(true);
    });
        maxScrollPosition = maxScrollPosition - $(".gallery-wrap").outerWidth();
    }
    // Make sure the target item exists, otherwise do nothing
    if($targetItem.length){

        // The new position is just to the left of the targetItem
        var newPosition = $targetItem.position().left;

        // If the new position isn't greater than the maximum width
        if(newPosition <= maxScrollPosition){

            // Add active class to the target item
            $targetItem.addClass("gallery__item--active");

            // Remove the Active class from all other items
            $targetItem.siblings().removeClass("gallery__item--active");

            // Animate .gallery element to the correct left position.
            $(".gallery").animate({
                left : - newPosition
            });
        } else {
            // Animate .gallery element to the correct left position.
            $(".gallery").animate({
                left : - maxScrollPosition
            });
        };
    };
};

// Only run everything once the page has completely loaded
$(window).load(function(){

    // Add active class to the first gallery item
//    $(".gallery__item:first").addClass("gallery__item--active");

    // When the prev button is clicked
    // ====================================================================


    // download Items
    getAllItems();
});
