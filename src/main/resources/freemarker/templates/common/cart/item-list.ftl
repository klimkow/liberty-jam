<script>
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
//            alert(form);
            var formData = form.serialize();
            show_step2(formData);
        });

        $("#items_form").delegate('#full-minus','click', function(e) {
            var value = $(this).next('input').val();
            var item_price = $(this).closest('#item-price-id').html();
            alert(item_price);
            if(value > 1) {
                $(this).next('input').val(--value);
            }
        });

        $("#items_form").delegate('#full-plus','click', function(e) {
            var value = $(this).prev('input').val();
            $(this).prev('input').val(++value);
        });



    });
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
            <tr id="item-list-tr">
                <td class="textleft"><img class="img-circle textleft" src="${item.getLogo()}" height="100" width="100"> <p class="item-name-cart">${item.getName()}</p></td>
                <td style="padding-top: 30px" class="td-right">
                    <div id="quant_box" style="margin-top: 0px;" class="quant_box">
                        <span id="full-minus" style="margin-top: 2px;" class="quant_btn_left">-</span>
                        <input type="text" name="${item.getId()}" id="quant_input" value="${order.getAmountOfItem(item)}" >
                        <span id="full-plus" style="margin-top: 2px;" class="quant_btn_right">+</span>
                    </div>
                </td>
                <td style="padding-top: 30px" class="td-right"><span id="item-price-id">${item.getPrice()?string?replace(",",".")}</span>.000</td>
                <td class="td-right"><img style="margin-top: 30px; cursor: pointer" onclick="removeItemFromCart(${item.getId()})" src="img/delete.png" height="15" width="15"></td>
            </tr>
            </#list>
        </tbody>
    </table>
    </form>
    </div>
    <div class="row sum_amount">
        Итого к оплате: <span style="color: #3FB8AF;" id="amount-to-pay">${sumAmount}</span><span style="color: #3FB8AF;">.000</span> РУБ.
    </div>

    <div id="go-step2" class="gallery__controls-next" >
        <img style="float: right" src="img/ar-right2.png" alt="" width="25" height="40"/>
        <p>${translator.getString("go_next_step")}</p>
    </div>
    <#--<div style="margin-top: 10px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15""/></div>-->
</div>

