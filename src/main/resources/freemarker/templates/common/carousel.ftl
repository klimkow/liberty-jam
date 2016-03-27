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
            <div class="car-menu-line">
                <div id="car-menu-item" class="container">
                    <span class="item1" onclick="openPage('4')">${translator.getString("car_menuitem1")} <small>${translator.getString("car_menuitem1_small1")}</small></span>
                    <span class="item2">${translator.getString("car_menuitem2")} <small>${translator.getString("car_menuitem1_small2")}</small></span>
                    <span class="item3" onclick="openPage('3')">${translator.getString("car_menuitem3")}<small></small>${translator.getString("car_menuitem1_small3")}</span>
                    <span class="item4">${translator.getString("car_menuitem4")}<small></small>${translator.getString("car_menuitem1_small4")}</span>
                </div>
            </div>
            <div class="container">
                <div style="position: absolute;right: 15%; left: 5%; z-index: 10;
                padding-top: 50px;    padding-bottom: 20px;color: #fff;text-align:
                 left;text-shadow: 0 1px 2px rgba(0,0,0,.6);cursor: default;
                 font-family: OsansLight, Arial, Helvetica, sans-serif; font-weight: bold;">
                    <h1 style="font-size: 34pt;padding-bottom: 50px; font-weight: bold;">${translator.getString("car_main1")}</h1>
                    <p style="font-size: 21pt; max-width: 600px;">${translator.getString("car_main2")}</p>
                    <p style="font-size: 21pt; max-width: 600px;">${translator.getString("car_main3")}</p>
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