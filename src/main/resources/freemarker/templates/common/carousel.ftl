<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide">
    <!-- Indicators -->
    <#--<ol class="carousel-indicators">-->
        <#--<li data-target="#myCarousel" data-slide-to="0" class="active"></li>-->
        <#--<li data-target="#myCarousel" data-slide-to="1"></li>-->
        <#--<li data-target="#myCarousel" data-slide-to="2"></li>-->
    <#--</ol>-->
    <div class="carousel-inner">
        <#--<div class="item ">-->
            <#--<img src="img/car1.jpg" style="width:100%" class="img-responsive">-->
            <#--<div class="container">-->
                <#--<div class="carousel-caption">-->
                    <#--<h1>${translator.getString("car_item1_header")}</h1>-->
                    <#--<p>${translator.getString("car_item1_text")}</p>-->
                    <#--</p>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="item">-->
            <#--<img src="img/car2.jpg" style="width:100%" class="img-responsive">-->
            <#--<div class="container">-->
                <#--<div class="carousel-caption">-->
                    <#--<h1>${translator.getString("car_item2_header")}</h1>-->
                    <#--<p>${translator.getString("car_item2_text")}</p>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
        <div class="item active">
            <img src="img/car3.jpg" style="width:100%" class="img-responsive">
            <div class="container">
                <div style="position: absolute;right: 15%; left: 15%; z-index: 10;
                padding-top: 170px;    padding-bottom: 20px;color: #fff;text-align:
                 center;text-shadow: 0 1px 2px rgba(0,0,0,.6);
                 font-family: OsansLight, Arial, Helvetica, sans-serif;">
                    <h1 style="font-size: 34pt">${translator.getString("test_mode_mes1")}</h1>
                    <p style="font-size: 21pt;">${translator.getString("test_mode_mes2")}</p>
                </div>
                <#--<div class="carousel-caption">-->
                    <#--<h1>${translator.getString("car_item3_header")}</h1>-->
                    <#--<p>${translator.getString("car_item3_text")}</p>-->
                <#--</div>-->
            </div>
        </div>
    </div>
    <!-- Controls -->
    <#--<a class="left carousel-control" href="#myCarousel" data-slide="prev">-->
        <#--<span class="icon-prev"></span>-->
    <#--</a>-->
    <#--<a class="right carousel-control" href="#myCarousel" data-slide="next">-->
        <#--<span class="icon-next"></span>-->
    <#--</a>-->
</div>
<!-- /.carousel -->