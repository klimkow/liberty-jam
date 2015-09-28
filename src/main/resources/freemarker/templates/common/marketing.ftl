<script>
    $(document).ready(function(){

      $("#gallery-container").delegate('#add-to-cart','click', function(e) {
          $(this).css("width", $(this).outerWidth());
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

        $('#bqt-found').fadeOut(200, function(){
            $('#bqt-found').html('${translator.getString("bqt_found")}: ${itemsSize}').fadeIn().delay(2000);

        });

    });
</script>

<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. -->
<div href="#" class="gallery__controls-prev" onclick="goPrev()">
    <img src="img/ar-left.png" alt="" width="50" height="110" />
</div>
<div href="#" class="gallery__controls-next" onclick="goNext()">
    <img src="img/ar-right.png" alt="" width="50" height="110"/>
</div>
<div class="container marketing margintop10">

    <!-- Three columns of text below the carousel -->
    <div class="row">
        <div id="gallery-container" class="gallery-wrap">
                <div style="width: ${galleryWidth}px" class="gallery clearfix">

                    <#list items as item>
                        <div style="cursor: pointer; width: ${itemWidth}px" class="gallery__item <#if x == 0>gallery__item--active</#if> text-center" >
                            <form name="myform">
                            <div id="item-desc-container" class="marketing-item-container">
                            <input id="item-id" type="hidden" name="itemId" value="${item.getId()}">
                            <img class="img-circle" src="${item.getLogo()}" height="200" width="200">
                            <h2>${item.getName()}</h2>
                            <div class="item-price-small">${item.getPrice()?string?replace(",",".")}${translator.getString("currency")}</div>
                            </div>
                            <div id="add-to-cart" class="btn btn-default <#if order??><#if order.isSelected(item)>btn-item-in-cart</#if></#if>" >
                                <#if order??&&order.isSelected(item)><img style="margin-right: 2px" src="img/ok_symb2.png" width="13" height="13"/>${translator.getString("item_int_the_cart")}
                                <#else>${translator.getString("add_to_cart")}
                                </#if></div>
                            </form>
                        </div>
                        <#assign x=1>
                    </#list>

            </div>


        </div>


    </div><!-- /.row -->
</div>

