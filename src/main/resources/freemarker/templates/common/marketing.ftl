<script>
    $(document).ready(function(){

      $("#gallery-container").delegate('#add-to-cart','click', function(e) {
          $(this).html('<img src="../img/button-loading.gif"/>');
          var form = $(this).closest('form');
          var formData = form.serialize();
          addToCart(formData, $(this));
      });

      $("#gallery-container").delegate('#item-desc-container','click', function(e) {
          var form = $(this).closest('form');
          var formData = form.serialize();
          getItem(formData);
      });

    });
</script>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<div href="#" class="gallery__controls-prev" onclick="goPrev()">
    <img src="img/ar-left.png" alt="" width="50" height="50" />
</div>
<div href="#" class="gallery__controls-next" onclick="goNext()">
    <img src="img/ar-right.png" alt="" width="50" height="50"/>
</div>
<div class="container marketing margintop10">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div id="gallery-container" class="gallery-wrap">
                <div style="width: ${galleryWidth}px" class="gallery clearfix">

                    <#list items as item>
                        <div style="cursor: pointer; width: ${itemWidth}px" class="gallery__item <#if x == 0>gallery__item--active</#if> text-center" >
                            <form name="myform">
                            <div id="item-desc-container">
                            <input id="item-id" type="hidden" name="itemId" value="${item.getId()}">
                            <img class="img-circle" src="${item.getLogo()}" height="200" width="200">
                            <h2>${item.getName()}</h2>
                            <p>${item.getDescription()?substring(0, 110)}...</p>
                            </div>
                            <p><div id="add-to-cart" class="btn btn-default" >Добавить в корзину</div></p>
                            </form>
                        </div>
                        <#assign x=1>
                    </#list>

            </div>


        </div>


    </div><!-- /.row -->
</div>

