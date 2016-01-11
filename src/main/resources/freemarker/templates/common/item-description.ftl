<script>
    $(document).ready(function(){

        $("#add-to-cart-desc").click(function(e) {
            $(this).css("width", $(this).outerWidth());
            $(this).html('<img src="../img/button-loading.gif"/>');
            var form = $(this).closest('form');
            var formData = form.serialize();
            addToCart(formData, $(this));
        });

        $("#item-additional-photo").delegate('#img-add','click', function(e) {
//            $('#item-photo').append('<img style="position: absolute; top: 50%;left: 50%;" src="img/loading.gif"  height="43" width="43"  />');
            $("#img-logo").attr('src', $(this).attr('src'));
            // highlight left border

            $("div[id^= 'add-img-div']").removeClass("item-additional-photo-selected");
            $(this).parent().addClass("item-additional-photo-selected");
        });

        $("#full-minus").click(function(e) {
            var value = document.getElementById('quant_input').value;
            if(value > 1) {
                document.getElementById('quant_input').value = --value;
            }
        });

        $("#full-plus").click(function(e) {
            document.getElementById('quant_input').value = ++document.getElementById('quant_input').value;
        });
    });
</script>

<div class="container ">
    <div class="item-description-container">
    <div style="position: relative;" id="item-photo" class="item-photo">
        <img id="img-logo" src="${selectedItem.getLogo()}" height="400" width="400">
    </div>
    <div id="item-additional-photo" class="item-additional-photo">
        <#if photos??>
        <#list photos as photo>
            <div id="add-img-div" style="text-align:left; display: block">
                <img id="img-add" style="margin-left: 15px; cursor:pointer;" class="img-circle" src="${photo.getImageUrl()}" height="110" width="110">
            </div>
        </#list>
        </#if>
    </div>
    <div class="item-description">
        <div class="description-text">
            <h2>${selectedItem.getName()}</h2>
            <p>${selectedItem.getDescription()}</p>
        </div>
        <div class="item-price">
            <form name="myform">
                <div class="item-add-options">
                    <input type="checkbox" id="c1" name="option_paper" <#if itemQuantity??><#if itemQuantity.isWithPaper()>checked</#if></#if>/>
                    <label style="display: block; margin-left: 10px" for="c1"><span></span>Крафт-бумага (+25.000 РУБ.)</label>
                    <input type="checkbox" id="c2" name="option_vase" <#if itemQuantity??><#if itemQuantity.isWithVase()>checked</#if></#if>/>
                    <label style="display: block; margin-left: 10px" for="c2"><span></span>Добавить вазу (+70.000 РУБ.)</label>
                    <div style="margin-left: 10px;" class="quant_box">
                        <span id="full-minus" class="quant_btn_left">-</span>
                        <input id="quant_input" type="text" name="item_quantity" value="${count}">
                        <span id="full-plus" class="quant_btn_right">+</span>
                    </div>
                </div>
                <div style="font-family: Attentica4F; font-size: 25pt; font-weight:bold; margin-left: 10px; margin-top:15px; padding-right: 15px" class="textleft">
                    <p class="textleft">${selectedItem.getPrice()?string?replace(",",".")}.000</p>
                </div>
                <div style="margin-top:20px; padding-right: 10px" class="textleft">
                        <input id="item-id" type="hidden" name="itemId" value="${itemId}">
                        <div id="add-to-cart-desc" class="btn btn-default <#if order??><#if order.isSelected(selectedItem)>btn-item-in-cart</#if></#if>" >
                    <#if order??&&order.isSelected(selectedItem)>
                        <img style="margin-right: 2px" src="img/ok_symb2.png" width="13" height="13"/>${translator.getString("item_int_the_cart")}
                    <#else>${translator.getString("add_to_cart")}
                    </#if></div>
                </div>
            </form>
        </div>
    </div>
    </div>
</div>