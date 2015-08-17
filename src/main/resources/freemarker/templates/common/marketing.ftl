<!-- FILTER MENU -->
<div class="row">
    <div class="content-section-a">
        <div id="filter" class="container">
            <p style="padding-right: 30px;" class=" textleft margintop25">Выберите тип букета и стоимость </p>
            <div style="margin-top:22px; padding-right: 10px" class="textleft">
                <p><a class=" btn btn-default" href="">Классический</a></p>
            </div>
            <div style="margin-top:22px; padding-right: 10px" class="textleft">
            <p><a class=" btn btn-default" href="#">Букет в шляпной коробке</a></p>
            </div>

            <div id="slider" class="slider"></div>
        </div>
    </div>
</div>

<!-- FILTER MENU END -->

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<div href="#" class="gallery__controls-prev">
    <img src="img/ar-left.png" alt="" width="50" height="50" />
</div>
<div href="#" class="gallery__controls-next">
    <img src="img/ar-right.png" alt="" width="50" height="50"/>
</div>
<div class="container marketing margintop10">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div class="gallery-wrap">
                <div class="gallery clearfix">

                    <#list items as item>
                        <div style="cursor: pointer" class="gallery__item  text-center" onclick="getItem(${item.getId()})">
                            <input id="item-id" type="hidden" name="itemId" value="${item.getId()}">
                            <img class="img-circle" src="${item.getLogo()}" height="200" width="200">
                            <h2>${item.getName()}</h2>
                            <p>${item.getDescription()?substring(0, 110)}...</p>
                            <p><a class="btn btn-default" href="#">Добавить в корзину</a></p>
                        </div>
                    </#list>

            </div>


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