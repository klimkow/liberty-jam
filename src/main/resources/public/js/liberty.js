var needUpdateNavBar = false;

function createSlider(){
    var slider = document.getElementById('slider');

    noUiSlider.create(slider, {
        start: [300000, 4000000],
        connect: true,
        step: 100000,
        range: {
            'min': 0,
            'max': 4000000
        },
        format: wNumb({
            decimals: 0,
            thousand: '.'
        })
    });

    var snapValues = [
        document.getElementById('sl_value_from'),
        document.getElementById('sl_value_to')
    ];

    slider.noUiSlider.on('update', function( values, handle ) {
        snapValues[handle].innerHTML = values[handle];
    });

    slider.noUiSlider.on('change', function(){
        $('#marketing-active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');

        filter('');
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

function show_step2(formData)
{
    var post_url = 'delivery_info';
    turnOnLoadingGlass();
    $('html, body').animate({
        scrollTop: $("#top-menu").offset().top
    }, 'slow');
    $.ajax({
        type: 'POST',
        url: post_url,
        data: formData,
        success: function(msg) {
            $('#active-zone').fadeOut(800, function(){
                $('#active-zone').html(msg).fadeIn().delay(2000);
                if (formData != null && formData != '') {
                    maybeUpdateNavBar();
                }
            });
        }
    });
}


function show_step3(formData)
{
    var post_url = 'payment_step';
    turnOnLoadingGlass();
    $('html, body').animate({
        scrollTop: $("#top-menu").offset().top
    }, 'slow');
    $.ajax({
        type: 'POST',
        url: post_url,
        data: formData,
        success: function(msg) {
            $('#active-zone').fadeOut(800, function(){
                $('#active-zone').html(msg).fadeIn().delay(2000);
                    maybeUpdateNavBar();
            });
        }
    });
}


function getCategoryItems(id)
{
    $('#filter_option' + id).addClass("button-selected");
    $('#marketing-active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
    var post_url = 'getCategoryItems';
    var post_data = {
        'categoryId' : id,
        'itemWidth' : $(".container")[0].offsetWidth/3};
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


function filter(option)
{
    maxScrollPosition = 0;
    var post_url = 'filter';

    var options = '';
    if (option != '') {
        options = option + "&";
    }
    options = options + "price_from=" + $("#sl_value_from").html() +
        "&price_to=" + $("#sl_value_to").html() + "&itemWidth=" + $(".container")[0].offsetWidth/3;

    $.ajax({
        type: 'POST',
        url: post_url,
        data: options,
        success: function(msg) {
            $('#marketing-active-zone').fadeOut(800, function(){
                $('#marketing-active-zone').html(msg).fadeIn().delay(2000);

            });
        }
    });

}


function changeLang(lang)
{
    var post_url = 'changeLang';
    $.ajax({
        type: 'POST',
        url: post_url,
        data: {'lang' : lang},
        success: function(msg) {
            location.reload();
        }
    });
}

function openPage(page)
{
    var post_url = 'openPage';
    $('html, body').animate({
        scrollTop: $("#active-zone").offset().top-50
    }, 'slow');
    turnOnLoadingGlass();
    $.ajax({
        type: 'POST',
        url: post_url,
        data: {'page' : page},
        success: function(msg) {
            $('#active-zone').fadeOut(800, function(){
                $('#active-zone').html(msg).fadeIn().delay(2000);
            });
        }
    });
}


function calculateClassicBouquetPrice(paper, vase, count, priceforone)
{
    return (count * priceforone) + paper + vase;
}

function chooseBoquete()
{
    if($("#marketing-filter-zone").get(0))
    {
        $('html, body').animate({
            scrollTop: $("#marketing-filter-zone").offset().top - 50
        }, 'slow');
    } else {
        window.location.href = "/";
        $("#marketing-filter-zone").load(function() {
            $('html, body').animate({
                scrollTop: $("#marketing-filter-zone").offset().top - 50
            }, 'slow');
        });
    }
}

function numberWithDots(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
}

function getItem(id)
{

        var form = $(this);
        var post_url = 'getItem';
        var post_data = id;
        // scroll slowly to active zone
        // 50 - the height of navbar
        $('html, body').animate({
            scrollTop: $("#active-zone").offset().top-50
        }, 'slow');
        turnOnLoadingGlass();
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
            var price = numberWithDots(resp.price);
            $('#cart-notif').fadeOut(300, function(){
                $('#cart-notif').html(resp.st1 + ' ' + resp.amount
                    + ' ' + resp.st2 + ' ' + resp.st3 + ' '
                    + price + resp.st4 +
                    '<a id="go-to-cart" class="btn btn-default" href="/cart">Оплатить</a>').fadeIn().delay(800);
            });
            element.css('border-color', '#3FB8AF');
            element.html('<img style="margin-right: 2px" src="img/ok_symb2.png" width="13" height="13"/>' + resp.st5
            ).fadeIn().delay(800);
        }
    });
}

function goToCart()
{
    var post_url = 'cart';
    $('html, body').animate({
        scrollTop: $("#top-menu").offset().top
    }, 'slow');
    turnOnLoadingGlass();
    $.ajax({
        type: 'POST',
        url: post_url,
        success: function(msg) {
            $('#marketing-filter-zone').fadeOut(800, function(){
                $('#marketing-filter-zone').html('').fadeIn().delay(2000);

            });
            $('#active-zone').fadeOut(800, function(){
                $('#active-zone').html(msg).fadeIn().delay(2000);

            });
        }
    });
}

function showValidationWarning(text)
{
    $('#alert-zone').html('<div style="text-align: center; color:black; font-family: OsansLight, Arial, Helvetica, sans-serif;font-size: 12pt;" class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' + text + '</div>');
}


function removeItemFromCart(id)
{
    needUpdateNavBar = true;
    var post_url = 'removeItem';
    turnOnLoadingGlass();
    $.ajax({
        type: 'POST',
        url: post_url,
        data: {"id" : id},
        success: function(msg) {
            $('#marketing-filter-zone').fadeOut(800, function(){
                $('#marketing-filter-zone').html('').fadeIn().delay(2000);

            });
            $('#active-zone').fadeOut(800, function(){
                $('#active-zone').html(msg).fadeIn().delay(2000);

            });
            maybeUpdateNavBar();
            recalculateSumAmount();
        }
    });
}

function maybeUpdateNavBar()
{
    if (!needUpdateNavBar)
        return;

    needUpdateNavBar = false;
    var post_url = 'updateNavbar';
    $.ajax({
        type: 'POST',
        url: post_url,
        success: function(msg) {
            var resp = JSON.parse(msg);
            if (resp.price != '') {
                var price = numberWithDots(resp.price);
                $('#cart-notif').fadeOut(300, function () {
                    $('#cart-notif').html(resp.st1 + ' ' + resp.amount
                        + ' ' + resp.st2 + ' ' + resp.st3 + ' '
                        + price + resp.st4 +
                        '<a id="go-to-cart" class="btn btn-default" href="/cart">Оплатить</a>').fadeIn().delay(800);
                });

            } else {
                $('#cart-notif').html("Корзина пуста");
            }
        }
    });
}

function turnOnLoadingGlass()
{
    $('#active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
}

function turnOnLoadingGlassWithParam(enable)
{
    if (enable) {
        turnOnLoadingGlass();
    } else {
        $('#top-layer').remove();
    }
}


function finishOrder(id)
{
    needUpdateNavBar = true;
    if (id == 'pay_cash') {
        var post_url = 'payCash';
        turnOnLoadingGlass();
        $.ajax({
            type: 'POST',
            url: post_url,
            success: function (msg)
            {
                $('#active-zone').fadeOut(800, function ()
                {
                    $('#active-zone').html(msg).fadeIn().delay(2000);
                    maybeUpdateNavBar();
                });
            }
        });
    } else {
        var post_url = 'payOnline';
        turnOnLoadingGlass();
        $.ajax({
            type: 'POST',
            url: post_url,
            success: function (msg)
            {
                var resp = JSON.parse(msg);
                $('input[name="FirstName"]').val(resp.name);
                $('input[name="LastName"]').val(resp.surname);
                $('input[name="Email"]').val(resp.email);
                $('input[name="MobilePhone"]').val(resp.phone);
                $('input[name="OrderNumber"]').val(resp.id);
                $('input[name="OrderAmount"]').val(resp.price);
                $('form[name="pay_online"]').serialize();
                $('form[name="pay_online"]').submit();
            }
        });
    }

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


    //$(document).ready(function(){
    //
    //    $("button").click(function(e){
    //            var post_url = 'getItem';
    //            var post_data = form.serialize();
    //            turnOnLoadingGlass();
    //            $.ajax({
    //                type: 'POST',
    //                url: post_url,
    //                data: post_data,
    //                success: function(msg) {
    //                    $('#active-zone').fadeOut(800, function(){
    //                        $('#active-zone').html(msg).fadeIn().delay(2000);
    //
    //                    });
    //                }
    //            });
    //        });
    //
    //});


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
    //getAllItems();
});
