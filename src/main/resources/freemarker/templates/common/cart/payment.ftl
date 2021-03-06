<script>


    var isSelected = false;
    var selectedId = '';

    function makeNotActive(id){
        if (!isSelected) {
            $('#' + id).addClass("payment-not-acitve");
        }
    }

    function makeActive(id){
        if (!isSelected) {
            $('#' + id).removeClass("payment-not-acitve");
        }
    }

    function selectPayment(id) {
        isSelected = true;
        selectedId = id;
        $('#lastStepButton').removeClass("hidden-control");
        if (id == 'pay_cash') {
            $('#pay_cash').removeClass("payment-not-acitve").
                    addClass("select-payment");
            $('#pay_online').removeClass("select-payment")
                    .addClass("payment-not-acitve");
            $('#lastStepButton').fadeOut(200, function(){
                $('#lastStepButton').
                        html("<img style='float: right' src='img/ar-right2.png' width='25' height='40'/><p>${translator.getString("done")}</p>").fadeIn().delay(100);

            });
        } else {
            $('#pay_online').removeClass("payment-not-acitve").
                    addClass("select-payment");
            $('#pay_cash').removeClass("select-payment")
                    .addClass("payment-not-acitve");
            $('#lastStepButton').fadeOut(200, function(){
                $('#lastStepButton').html(
                        "<img style='float: right' src='img/ar-right2.png' width='25' height='40'/><p>${translator.getString("go_to_payment")}</p>").fadeIn().delay(100);

            });
        }
    }

</script>

<div style="height: 100px; background-color: #f3f3f3;" class="row">
    <div class="container">
    <div style="width: 60%; margin-left: 210px;">
            <img style="margin-left: 590px; margin-top: 7px; margin-bottom: 5px" src="img/bi3.png" width="30" height="30"/>
            <div class="progress">
                <div class="progress-bar" role="progressbar"
                     aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                    <span class="sr-only">99% Complete</span>
                </div>
            </div>
            <div class="width33 progress-step1" onclick="goToCart()">
                <img style="float:left; margin-right: 6px; margin-top: 10px" src="img/ok_symb2.png" width="18" height="18"/>
                <p>1.${translator.getString("step1")}</p></div>
            <div class="width33 progress-step2" onclick="show_step2()">
                <img style="float:left;margin-left: 20px; margin-right: 6px; margin-top: 10px" src="img/ok_symb2.png" width="18" height="18"/>
                <p style="float: left">2.${translator.getString("step2")}</p></div>
            <div  class="width33 progress-step3"><p>3.${translator.getString("step3")}</p></div>
        </div>
    </div>
</div>

<div style="min-height: 400px;" class="container ">
    <div id="payment-type-row" class="row">

    <div id="pay_online" style="margin-left: 100px; margin-right: 30px"
         class="payment-block" onmouseover="makeNotActive('pay_cash')"
         onmouseout="makeActive('pay_cash')" onclick="selectPayment('pay_online')">
        <p>${translator.getString("payment_online")}</p>
        <p style="font-family: BadScript; font-size: 14pt; margin-top: 10px;">
            ${translator.getString("payment_by_card_online")}
        </p>
        <div style="margin-top: 40px" class="row">
            <img src="img/assistlogo.png" height="50px">
        </div>
        <#--<div style="margin-top: 30px;" class="btn btn-default">-->
        <#--${translator.getString("go_to_payment")}-->
        <#--</div>-->
    </div>
    <div id="pay_cash" style="" class="payment-block"
         onmouseover="makeNotActive('pay_online')"
         onmouseout="makeActive('pay_online')" onclick="selectPayment('pay_cash')">
        <p>${translator.getString("pay_to_delivery_boy")}</p>
        <p style="font-size: 14pt; font-family: BadScript; margin-top: 10px;">
            ${translator.getString("pay_to_d_boy_by_card_or_cash")}
        </p>
    </div>

    </div>
    <div style="font-size: 28pt;" class="row sum_amount">
    ${translator.getString("sum_amount_to_pay")}: <span style="color: #3FB8AF;" id="amount-to-pay">${sumAmount?string?replace(",",".")}</span><span style="color: #3FB8AF;">.000</span> ${translator.getString("rub")}.
    </div>

    <div style="margin-top: 30px;" href="#" class="gallery__controls-prev" onclick="show_step2()">
        <img style="float: left" src="img/ar-left2.png" alt="" width="25" height="40" />
        <p>${translator.getString("go_prev_step")}</p>
    </div>
    <div id="lastStepButton" style="margin-top: 30px; cursor: pointer" class="hidden-control gallery__controls-next " onclick="finishOrder(selectedId)">
    </div>
    <form name="pay_online" method="post" action="https://pay139.paysec.by/pay/order.cfm">
        <input type="hidden" name="Merchant_ID" value="477696">
        <input type="hidden" name="FirstName"">
        <input type="hidden" name="LastName">
        <input type="hidden" name="Email">
        <input type="hidden" name="MobilePhone">
        <input type="hidden" name="OrderNumber">
        <input type="hidden" name="OrderAmount">
    </form>
</div>