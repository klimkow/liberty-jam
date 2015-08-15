<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap 101 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/liberty.css" rel="stylesheet" media="screen">
    <link href="css/nouislider.min.css" rel="stylesheet">
</head>
<body onload="createSlider()">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script>
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

    </script>

    <!-- NAVBAR -->

    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="textleft margintop3">
                <img src="img/cart.png" height="25" width="25">
                У ВАС 2 БУКЕТА НА СУММУ 3.150.000 РУБ.
            </div>
            <div class="textright">
                <img style="border-right: 1px solid #B8B8B8; border-left: 1px solid #B8B8B8" src="img/fb.png" alt="Facebook" height="42" width="42">
                <img style="border-right: 1px solid #B8B8B8;" src="img/twt.png" alt="Twitter" height="42" width="42">
                <img style="border-right: 1px solid #B8B8B8;" src="img/vk.png" alt="Vkontakte" height="42" width="42">
            </div>
        </div>
    </nav>

    <!-- NAVBAR END-->

    <!-- TOP MENU & LOGO -->
    <div class="row">
         <div class="container top-menu">
             <div class="text-center">
                 <div class="row">
                     <img src="img/main-logo.png" style="height:120px;"/>
                 </div>
                 <div class="row menu-item-container">
                     <div class="menu-col">ВЫБРАТЬ БУКЕТ</div>
                     <div class="menu-col">ПОВОД</div>
                     <div class="menu-col">КАК ЭТО РАБОТАЕТ</div>
                     <div class="menu-col">О НАС</div>
                     <div class="menu-col">КОНТАКТЫ</div>
                </div>
         </div>
    </div>


    </div>

    <!-- TOP MENU END -->

    <!-- Carousel
 ================================================== -->
    <div id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="/img/bg_suburb.jpg" style="width:100%" class="img-responsive">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Bootstrap 3 Carousel Layout</h1>
                        <p></p>
                        <p><a class="btn btn-lg btn-primary" href="http://getbootstrap.com">Learn More</a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img src="http://lorempixel.com/2000/600/abstract/1" class="img-responsive">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Changes to the Grid</h1>
                        <p>Bootstrap 3 still features a 12-column grid, but many of the CSS class names have completely changed.</p>
                        <p><a class="btn btn-large btn-primary" href="#">Learn more</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img src="http://placehold.it/1500X500" class="img-responsive">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>Percentage-based sizing</h1>
                        <p>With "mobile-first" there is now only one percentage-based grid.</p>
                        <p><a class="btn btn-large btn-primary" href="#">Browse gallery</a></p>
                    </div>
                </div>
            </div>
        </div>
        <!-- Controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
    </div>
    <!-- /.carousel -->

    <!-- FILTER MENU -->
    <div class="row">
    <div class="content-section-a">
        <div class="container attentica-font16 ">
            <p class="textleft margintop25">Выберите тип букета и стоимость </p>
            <div id="slider" class="slider"></div>
        </div>
    </div>
    </div>

    <!-- FILTER MENU END -->

    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing margintop10">

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-sm-6 col-md-4 text-center">
                <img class="img-circle" src="img/b1.jpg" height="200" width="200">
                <h2>Mobile-first</h2>
                <p>Tablets, phones, laptops. The new 3 promises to be mobile friendly from the start.</p>
                <p><a class="btn btn-default" href="#">View details »</a></p>
            </div>
            <div class="col-sm-6 col-md-4 text-center">
                <img class="img-circle" src="img/b2.jpg" height="200" width="200">
                <h2>One Fluid Grid</h2>
                <p>There is now just one percentage-based grid for Bootstrap 3. Customize for fixed widths.</p>
                <p><a class="btn btn-default" href="#">View details »</a></p>
            </div>
            <div class="col-sm-6 col-md-4 text-center">
                <img class="img-circle" src="img/b3.jpg" height="200" width="200">
                <h2>LESS is More</h2>
                <p>Improved support for mixins make the new Bootstrap 3 easier to customize.</p>
                <p><a class="btn btn-default" href="#">View details »</a></p>
            </div>
        </div><!-- /.row -->
        </div>

        <div class="row">
            <div class="content-section-a">
                <div class="container attentica-font30">
                    <p class="text-center margintop10">КАК ЭТО РАБОТАЕТ </p>
                </div>
            </div>
        </div>

    <!-- How does it work section
    ================================================== -->

    <div class="container marketing margintop10">

        <!-- Three columns of text below the carousel -->
        <div class="row">
            <div class="col-sm-6 col-md-4 text-center">
                <img src="img/hw-it-work1.png" height="200" width="200">
            </div>
            <div class="col-sm-6 col-md-4 text-center">
                <img src="img/hw-it-work2.png" height="200" width="200">
            </div>
            <div class="col-sm-6 col-md-4 text-center">
                <img src="img/hw-it-work3.png" height="200" width="200">
            </div>
        </div><!-- /.row -->
    </div>

    <div class="row">
        <div class="content-section-a">
            <div class="container text-center">
            <div class="row menu-item-container">
                <div class="menu-col">ВЫБРАТЬ БУКЕТ</div>
                <div class="menu-col">ПОВОД</div>
                <div class="menu-col">КАК ЭТО РАБОТАЕТ</div>
                <div class="menu-col">О НАС</div>
                <div class="menu-col">КОНТАКТЫ</div>
            </div>
                </div>
        </div>
    </div>


</body>
</html>