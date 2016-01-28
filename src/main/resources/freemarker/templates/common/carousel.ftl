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
            <img src="img/car1.jpg" style="width:100%" class="img-responsive">
            <div class="container">
                <div class="carousel-caption">
                    <h1>${translator.getString("car_item1_header")}</h1>
                    <p>${translator.getString("car_item1_text")}</p>
                    </p>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="img/car2.jpg" style="width:100%" class="img-responsive">
            <div class="container">
                <div class="carousel-caption">
                    <h1>${translator.getString("car_item2_header")}</h1>
                    <p>${translator.getString("car_item2_text")}</p>
                </div>
            </div>
        </div>
        <div class="item">
            <img src="img/car3.jpg" style="width:100%" class="img-responsive">
            <div class="container">
                <div class="carousel-caption">
                    <h1>${translator.getString("car_item3_header")}</h1>
                    <p>${translator.getString("car_item3_text")}</p>
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