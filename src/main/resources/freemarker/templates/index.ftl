<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap 101 Template</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/liberty.css" rel="stylesheet" media="screen">
</head>
<body>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Project name</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <form class="navbar-form navbar-right">
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                </form>
            </div><!--/.navbar-collapse -->
        </div>
    </nav>
    <header>
         <div class="container" style="margin-top: 50px; font-family: Attentica4F; font-weight:  bold">
             <div class="span12" style="text-align: center">
                 <div class="row">
                     <img src="img/main-logo.png" style="height:120px;"/>
                 </div>
                 <div class="row" style="font-size: 12pt">
                     <div class="span2">${locale}</div>
                     <div class="span2">ПОВОД</div>
                     <div class="span2">КАК ЭТО РАБОТАЕТ</div>
                     <div class="span2">О НАС</div>
                     <div class="span2">КОНТАКТЫ</div>
                 </div>
             </div>
         </div>


    </header>
    <div class="container">
    <div id="myCarousel" class="carousel slide">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="active item">
                <img src="img/slide-1.jpg"/>
            </div>
            <div class="item">
                <img src="img/slide-2.jpg"/>
            </div>
            <div class="item">
                <img src="img/slide-3.jpg"/>
            </div>
        </div>
        <!-- Carousel nav -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
    </div>

    </div>

    <div class="content-section-a">


    </div>
</body>
</html>