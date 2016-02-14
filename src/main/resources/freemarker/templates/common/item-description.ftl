<script>

    function getItemPriceFromDiapasons(count) {
        <#if diapasons??>
            <#list diapasons as diapason>
                if (count >= ${diapason.getCountFrom()} && count <= ${diapason.getCountTo()}) {
                    return ${diapason.getPrice()} * 1000;
                }
            </#list>
        </#if>
        return ${selectedItem.getPrice()} * 1000;
    }


    $(document).ready(function(){

        var itemMinAmount = ${minAmount};
        var isClassic = false;
        <#if isClassic>
                isClassic = true;
        </#if>
//        var value = document.getElementById('quant_input').value;

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


        $( "#quant_input" ).change(function() {
            var value = document.getElementById('quant_input').value;
            if (value < itemMinAmount) {
                document.getElementById('quant_input').value = itemMinAmount;
            } else {
                if(isClassic) {
                    updateResultAmount(value);
                }
            }
        });


        $( "#c1" ).change(function() {
            var value = document.getElementById('quant_input').value;
            updateResultAmount(value);
        });


        $( "#c2" ).change(function() {
            var value = document.getElementById('quant_input').value;
            updateResultAmount(value);
        });


        $("#full-minus").click(function(e) {
            var value = document.getElementById('quant_input').value;
            if(value > itemMinAmount) {
                document.getElementById('quant_input').value = --value;
                if(isClassic) {
                    updateResultAmount(value)
                }
            }
        });

        $("#full-plus").click(function(e) {
            var value = document.getElementById('quant_input').value;
            document.getElementById('quant_input').value = ++value;
            if(isClassic) {
                updateResultAmount(value)
            }
        });
    });

    function updateResultAmount(value)
    {
        var pforone = getItemPriceFromDiapasons(value);
        paper_price = $('#c1').is(':checked') ? 25000 : 0;
        vase_price = $('#c2').is(':checked') ? 200000 : 0;
        var result = calculateClassicBouquetPrice(paper_price, vase_price, value, pforone);
        $('#flex-price').html(getStringPriceForOneFlower(pforone));
        $('#result-amount').html(numberWithDots(result));
    }

    function getStringPriceForOneFlower(value)
    {
        return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }

    function getIntPriceForOneFlower(value)
    {
        if (value < 21) {
            return 35000;
        }
        if (value > 20 && value < 31) {
            return 34000;
        } else {
            return 30000;
        }
    }


</script>

<div class="container ">
    <div class="item-description-container">
    <div style="position: relative;" id="item-photo" class="item-photo">
        <img id="img-logo" src="${selectedItem.getLogo()}" height="450" width="450">
    </div>
    <div id="item-additional-photo" class="item-additional-photo">
        <#--<#if photos??>-->
        <#--<#list photos as photo>-->
            <div id="add-img-div" class="item-additional-photo-selected">
                <img id="img-add" style="margin-left: 15px; cursor:pointer;" class="img-circle" src="${selectedItem.getLogo()}" height="110" width="110">
            </div>
            <div id="add-img-div">
                <img id="img-add" style="margin-left: 15px; cursor:pointer;" class="img-circle" src="${selectedItem.getSecondImageUrl()}" height="110" width="110">
            </div>
            <div id="add-img-div">
                <img id="img-add" style="margin-left: 15px; cursor:pointer;" class="img-circle" src="${selectedItem.getThirdImageUrl()}" height="110" width="110">
            </div>
        <#--</#list>-->
        <#--</#if>-->
    </div>
    <div class="item-description">
        <div class="description-text">
            <h2>${selectedItem.getName()}</h2>
            <p>${selectedItem.getDescription()}</p>
        </div>
        <div class="item-price">
            <form name="myform">
                <div class="item-add-options">
                    <#if isClassic>
                        <input type="checkbox" id="c1" name="option_paper" <#if itemQuantity??><#if itemQuantity.isWithPaper()>checked</#if></#if>/>
                        <label style="display: block; margin-left: 10px" for="c1"><span></span>${translator.getString("item_paper_option")}</label>
                        <input type="checkbox" id="c2" name="option_vase" <#if itemQuantity??><#if itemQuantity.isWithVase()>checked</#if></#if>/>
                        <label style="display: block; margin-left: 10px" for="c2"><span></span>${translator.getString("item_vase_option")}</label>
                    </#if>
                    <div style="margin-left: 10px;" class="quant_box">
                        <span id="full-minus" class="quant_btn_left noselect">-</span>
                        <input id="quant_input" type="text" name="item_quantity" value="${count}">
                        <span id="full-plus" class="quant_btn_right noselect">+</span>
                    </div>
                    <#if isClassic>
                        <span style="padding-left: 9px;vertical-align: super;">
                            ${translator.getString("item_for")}
                            <span id="flex-price" style="padding-left: 5px;font-size: 14pt;font-weight: bold;">
                                ${selectedItem.getPrice()?string?replace(",",".")}.000
                            </span>
                            <span style="font-size: 14pt;font-weight: bold;">
                                ${translator.getString("rub")}.
                            </span>
                        </span></#if>
                </div>
                <div style="font-family: Attentica4F; font-size: 25pt; font-weight:bold; margin-left: 10px; margin-top:15px; padding-right: 15px" class="textleft">
                    <p id="result-amount" class="textleft">${(selectedItem.getPrice() * selectedItem.getMinAmount())?string?replace(",",".")}.000</p>
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