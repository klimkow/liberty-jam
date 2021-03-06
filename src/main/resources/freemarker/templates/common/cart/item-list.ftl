<script>
    function getItemPriceFromDiapasons(id, count) {
        <#list cartItems as item>
            if(id == ${item.getId()}) {
                <#if item.getPriceDiapasonList()??>
                    <#list item.getPriceDiapasonList() as diapason>
                        if (count >= ${diapason.getCountFrom()} && count <= ${diapason.getCountTo()}) {
                            return ${diapason.getPrice()};
                        }
                    </#list>
                </#if>

                return ${item.getPrice()?c};
            }
        </#list>
    }

    function getAdditionalPrice(id) {
        var result = 0;
        <#list cartItems as item>
            if(id == ${item.getId()}) {
                <#if order.getIQWithItem(item).isWithPaper()>result += 25;</#if>
                <#if order.getIQWithItem(item).isWithVase()>result += 220;</#if>

                return result;
            }
        </#list>
    }

    function getItemMinAmount(id) {
        <#list cartItems as item>
            if(id == ${item.getId()}) {
                return ${item.getMinAmount()};
            }
        </#list>
        return 1;
    }

    $(document).ready(function(){


        $("#go-step2").click(function(e) {
            $('form[name="items_form"]').submit();
        });

        $("#step2_wizard").click(function(e) {
            $('form[name="items_form"]').submit();
        });

        $('#items_form').bind('submit', function (e) {
            e.preventDefault();
            var form = $('form[name="items_form"]');
            var formData = form.serialize();
            show_step2(formData);
        });

        $("#items_form").delegate('#full-minus','click', function(e) {
            var value = $(this).next('input').val();
            var id = $(this).next('input').attr('name');
            var minAmount = getItemMinAmount(id);
            if(value > minAmount) {
                $(this).next('input').val(--value);
                updateItemPrice(id, value);
                recalculateSumAmount();
            }
        });

        $("#items_form").delegate('#full-plus','click', function(e) {
            var value = $(this).prev('input').val();
            var id = $(this).prev('input').attr('name');

            $(this).prev('input').val(++value);
            updateItemPrice(id, value);
            recalculateSumAmount();
        });
    });

    function onCountChanged(id) {
        var count = $(("input[name='" + id + "']")).val();
        var minAmount = getItemMinAmount(id);
        if (count < minAmount) {
            $(("input[name='" + id + "']")).val(minAmount);
            count = minAmount;
        }
        updateItemPrice(id, count);
        recalculateSumAmount();
    }

    function updateItemPrice(id, count) {
        needUpdateNavBar = true;
        var price = getItemPriceFromDiapasons(id, count);
        $('#item-price-' + id).html(numberWithDots(price));
    }

    function recalculateSumAmount()
    {
        var price = 0;
        $(".item-list-tr").each(function() {
            var id = $(this).find('input').attr('name');
            var count = $(this).find('input').val();
            price += getItemPriceFromDiapasons(id, count) * count;
            price += getAdditionalPrice(id);
        });
        $('#amount-to-pay').html(numberWithDots(price));
    }

</script>


<div style="height: 100px; background-color: #f3f3f3;" class="row">
    <div class="container">
        <#--<div style="margin-top: 30px;" href="#" class="gallery__controls-next" onclick="show_step2()">-->
            <#--<img style="float: right" src="img/ar-right.png" alt="" width="25" height="40"/>-->
            <#--<p style="text-decoration: underline; float: right; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_next_step")}</p>-->
        <#--</div>-->
        <div style="width: 60%; margin-left: 210px;">
            <div style="height: 45px" class="row">
                <img style="float:left; margin-left: 80px; margin-top: 7px; margin-bottom: 5px" src="img/bi3.png" width="30" height="30"/>
            </div>
            <div class=" row progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="5" aria-valuemin="0" aria-valuemax="100" style="width: 5%;">
                    <span class="sr-only">5% Complete</span>
                </div>
            </div>
            <div class="width33 progress-step1"><p style="font-size: 21pt;">1.${translator.getString("step1")}</p></div>
            <div id="step2_wizard" style="color: #ABABAB;" class="width33 progress-step2"><p>2.${translator.getString("step2")}</p></div>
            <div style="color: #ABABAB;" class="width33 progress-step3"><p>3.${translator.getString("step3")}</p></div>
        </div>
    </div>
</div>

<div style="height: 400px;" class="container ">
    <#--<div class="row wizard-container text-center">-->
        <#--<div class="wizard-item-active-first"><h2>1.${translator.getString("step1")}</h2></div>-->
        <#--<div class="wizard-item">2.${translator.getString("step2")}</div>-->
        <#--<div class="wizard-item">3.${translator.getString("step3")}</div>-->
    <#--</div>-->
    <div class="row" style="height: 275px; width: 100%; margin-top: 20px; overflow: auto">
    <form name="items_form" id="items_form" method="post">
    <table class="table table-striped">
        <thead>
        <tr>
            <th></th>
            <th>${translator.getString("amount")}</th>
            <th>${translator.getString("price")}</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
            <#list cartItems as item>
            <tr id="item-list-tr" class="item-list-tr">
                <td class="textleft">
                    <img class="img-circle textleft" src="${item.getLogo()}" height="100" width="100">
                    <div class="item-name-cart-block">
                        <p class="item-name-cart">${item.getName()}</p>
                        <#if order.getIQWithItem(item).isWithPaper()><span>${translator.getString("item_paper_option")}</span></#if>
                        <#if order.getIQWithItem(item).isWithVase()><span>${translator.getString("item_vase_option_added")}</span></#if>
                    </div>
                </td>
                <td style="padding-top: 30px" class="td-right">
                    <div id="quant_box" style="margin-top: 0px;" class="quant_box">
                        <span id="full-minus" style="margin-top: 2px;" class="quant_btn_left noselect">-</span>
                        <#assign initCount = order.getAmountOfItem(item)>
                        <input type="text" onchange="onCountChanged(${item.getId()})" name="${item.getId()}" id="quant_input" value="${initCount}" >
                        <span id="full-plus" style="margin-top: 2px;" class="quant_btn_right noselect">+</span>
                    </div>
                </td>
                <td style="padding-top: 30px" class="td-right"><span id="item-price-${item.getId()}" class="itm-price">${item.getPriceIncludingDiapasons(initCount)?string?replace(",",".")}</span>.000</td>
                <td class="td-right"><img style="margin-top: 30px; cursor: pointer" onclick="removeItemFromCart(${item.getId()})" src="img/delete.png" height="15" width="15"></td>
            </tr>
            </#list>
        </tbody>
    </table>
    </form>
    </div>
    <div class="row sum_amount">
        ${translator.getString("sum_amount_to_pay")}: <span style="color: #3FB8AF;" id="amount-to-pay">${sumAmount?string?replace(",",".")}</span><span style="color: #3FB8AF;">.000</span> ${translator.getString("rub")}.
    </div>

    <div id="go-step2" class="gallery__controls-next" >
        <img style="float: right" src="img/ar-right2.png" alt="" width="25" height="40"/>
        <p>${translator.getString("go_next_step")}</p>
    </div>
    <#--<div style="margin-top: 10px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15""/></div>-->
</div>

