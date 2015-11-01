<script>

    $(document).ready(function() {
        $('#dlv-form').validator();
        <#--<#if delivery??>$('#datetimepicker10 div').datepicker('setDate', ${delivery.getDateView()});    </#if>-->

    });

    $('#go_step3').click(function(e) {
        document.getElementById('date').value = $('#datetimepicker10 div').datepicker('getDate');
//        $('#date').val($('#datetimepicker10 div').datepicker('getDate'));
        var form = $(this).closest('form');
        var formData = form.serialize();
        show_step3(formData);
    });

    $('.btn-show').on('click', function(e) {
        $('.collapse').collapse('show');
    });

    $('.btn-hide').on('click', function(e) {
        $('.collapse').collapse('hide');
    });

    $(function () {
        $('[data-toggle="popover"]').popover()
    })
</script>

<div style="height: 100px; background-color: #f3f3f3;" class="row">


    <div class="container">

        <div style="width: 60%; margin-left: 210px;">
            <img style="margin-top: 7px; margin-bottom: 5px" src="img/bi3.png" width="30" height="30"/>
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                    <span class="sr-only">50% Complete</span>
                </div>
            </div>
                <div class="width33 progress-step1" onclick="goToCart()"><img style="float:left; margin-right: 6px; margin-top: 10px" src="img/ok_symb2.png" width="18" height="18"/><p>1.${translator.getString("step1")}</p></div>
                <div class="width33 progress-step2"><p style="font-size: 21pt;">2.${translator.getString("step2")}</p></div>
                <div style="color: #ABABAB;" class="width33 progress-step3"><p>3.${translator.getString("step3")}</p></div>
            </div>
        </div>
</div>
<div  class="container ">
    <form name="dlv-form-name" id="dlv-form" data-toggle="validator" role="form">
    <div class="row dlv-container-block">
    <div class="row">
    <div style="float:left">
        <img style="margin-left: 220px; margin-top:30px;" src="img/icon_sender.png" width="120" height="120"/>
        <div style="margin-left: 220px; width: 200px;font-family: BadScript;
            font-size: 15pt; font-weight: bold; color: #929090">
        ${translator.getString("dlv_assist_pic1")}
        </div>
    </div>
    <div style="" class="dlv-block textright">
        <h2>${translator.getString("dlv_from")}</h2>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="from-name">${translator.getString("dlv_name")}</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="text" name="name-from" pattern="${translator.getString('name_reg_exp')}{1,}$"
                   maxlength="55" class="form-control has-success" id="from-name" <#if user??>value="${user.getName()}" </#if> required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            <#--<span class="help-block with-errors"></span>-->
        </div>
        <#--<div class="dlv-block-cn form-group has-feedback">-->
            <#--<label for="from-surname">${translator.getString("dlv_surname")}</label>-->
            <#--<input type="text" name="surname-from" pattern="${translator.getString('name_reg_exp')}{1,}$" maxlength="55" class="form-control has-success" id="from-name">-->
        <#--&lt;#&ndash;<span class="help-block with-errors"></span>&ndash;&gt;-->
        <#--</div>-->
        <div class="dlv-block-cn form-group has-feedback">
            <label for="from-tel">${translator.getString("dlv_phone_number")}</label>
            <input type="text" name="phone-from" pattern="[+\d\s]{1,}$" maxlength="25" class="form-control"
                   id="from-tel"  placeholder="${translator.getString("dlv_phone_placeholder1")}"
                   <#if user??>value="${user.getPhone()}"</#if>
                   data-toggle="popover" data-content="Мы воспользуемся этим номером телефона, если у нас возникнут трудности с доставкой">
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="from-email">Email</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="email" name="email-from" class="form-control" id="from-email"
                   <#if user??>value="${user.getEmail()}"</#if> required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label>
                ${translator.getString("dlv_who_is_reciever")}
            </label>
            <label class="radio-inline">
                <input class="btn-hide" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" checked="checked">
                ${translator.getString("i_am")}
            </label>
            <label class="radio-inline">
                <input class="btn-show" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                ${translator.getString("another_person")}
            </label>
        </div>
    </div>
    </div>
    </div>

        <#--=============================== COLLAPSABLE MENU ===============================-->
<div class="row">
<div class="collapse-group">



    <div class="collapse row dlv-container-block">

    <div id="dlv-bock-title" class="row text-center">
    </div>

    <div class="row">
    <div style="float:left">
        <img style="margin-left: 220px; margin-top:60px;" src="img/icon_recipient.png" width="120" height="120"/>
        <div style="margin-left: 220px; width: 200px;font-family: BadScript;
             font-size: 15pt; font-weight: bold; color: #929090">
        ${translator.getString("dlv_assist_pic2")}
        </div>
    </div>
    <div style="" class="dlv-block textright">
        <h2>${translator.getString("dlv_to")}</h2>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="to-name">${translator.getString("dlv_name_to")}</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="text" name="name_to" pattern="${translator.getString('name_reg_exp')}{1,}$" maxlength="55" class="form-control" id="to-name" required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="to-phone">${translator.getString("dlv_phone_reciever")}</label>
            <input type="text" name="name-to" pattern="[+\d\s]{1,}$" maxlength="25" class="form-control" id="to-phone"  placeholder="${translator.getString("dlv_phone_placeholder1")}">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="comment">${translator.getString("dlv_wish_comment")}</label>
            <textarea style="height: 70px" name="message" class="form-control" rows="5" id="comment"  placeholder="${translator.getString("dlv_wish_comment_placeholder")}"></textarea>
        </div>
    </div>
    </div>
    </div>

</div>
</div>
    <#--=============================== COLLAPSABLE MENU END ===============================-->

    <div style="margin-bottom: 50px" class="row dlv-container-block">
    <div id="dlv-bock-title" class="row text-center">

    </div>

    <div class="row">
    <div style="float:left">
    <img style="margin-left: 220px; margin-top:10px;" src="img/icon_delivery.png" width="120" height="120"/>
    <div style="margin-left: 220px; width: 200px;font-family: BadScript;
    font-size: 15pt; font-weight: bold; color: #929090">
    ${translator.getString("dlv_assist_pic3")}
    </div>
    </div>

     <div style="margin-bottom: 20px" class="dlv-block textright">
        <h2>${translator.getString("dlv_delivery_title")}</h2>
        <div style="overflow: hidden;">
            <div class="dlv-block-cn form-group has-feedback">
                <label for="address">${translator.getString("address_street")}</label>
                <span class="glyphicon glyphicon-asterisk"></span>
                <input type="text" name="address" pattern="${translator.getString('name_reg_exp')}{1,}$"
                       maxlength="55" class="form-control" id="to-name"
                       <#if delivery??>value="${delivery.getAddressStreet()}"</#if> required>
                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            </div>
            <div style="float:left; width: 50%;" class="dlv-block-cn form-group has-feedback">
                <label for="address-house">${translator.getString("address_house")}</label>
                <span class="glyphicon glyphicon-asterisk"></span>
                <div style="width: 70%">
                    <input type="text" name="address-house" maxlength="10" class="form-control" id="to-phone"
                           <#if delivery??>value="${delivery.getAddressHouse()}"</#if> required>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>
            </div>
            <div style="float:right; width: 50%;" class="dlv-block-cn form-group has-feedback">
                <label for="address-flor">Офис/этаж</label>
                <div style="width: 70%">
                    <input type="text" name="address-flor" maxlength="10" class="form-control"
                           <#if delivery??>value="${delivery.getAddressFlor()}"</#if> id="to-phone">
                </div>
            </div>
            <div class="dlv-block-cn form-group">
                <div style="float: left; border-bottom: 1px #B1AEAE solid;width: 40%;font-family: BadScript;font-size: 13pt;">
                    <label for="comment">${translator.getString("dlv_deliver_date")}</label>
                    <span class="glyphicon glyphicon-asterisk"></span>
                </div>
                <div style="font-family: BadScript; font-size: 13pt;float: right;  width: 50%;">
                    <div style="margin-bottom:10px; width:99%;
                    border-bottom: 1px #B1AEAE solid;" class="row">
                    <label for="comment">${translator.getString("dlv_deliver_time")}</label>
                        <span class="glyphicon glyphicon-asterisk"></span>
                        </div>
                    <div class="dlv-time-item">07.00 - 09.00</div>
                    <div class="dlv-time-item">09.00 - 11.00</div>
                    <div class="dlv-time-item">11.00 - 13.00</div>
                    <div class="dlv-time-item">13.00 - 15.00</div>
                    <div class="dlv-time-item">15.00 - 17.00</div>
                    <div class="dlv-time-item">17.00 - 19.00</div>
                    <input type="hidden" name="time">
                </div>
                <div style="float: left;" class='input-group date' id='datetimepicker10'>
                    <input id="date" type="hidden" name="date">
                    <div class="attentica-font16"></div>
                <#--<input type='text' name="date" class="form-control" required/>-->
                    <script type="text/javascript">
                        $(function () {
                            $('#datetimepicker10 div').datepicker({
                                datesDisabled: ['09/06/2015', '09/21/2015'],
                                language: "ru"
                            });
                        });
                    </script>
                </div>
            </div>
            <#--<div class="dlv-block-cn form-group">-->
                <#--<label>-->
                    <#--<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" checked>-->
                <#--${translator.getString("dlv_delivery_free")}-->
                <#--</label>-->
                <#--<label>-->
                    <#--<input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">-->
                <#--${translator.getString("dlv_delivery_accurate")}-->
                <#--</label>-->
                <#--<select name="time" style="padding: 0px 10px;" class="form-control">-->
                    <#--<option>9.00 - 10.00</option>-->
                    <#--<option>10.00 - 11.00</option>-->
                    <#--<option>11.00 - 12.00</option>-->
                    <#--<option>12.00 - 13.00</option>-->
                    <#--<option>13.00 - 14.00</option>-->
                    <#--<option>14.00 - 15.00</option>-->
                    <#--<option>15.00 - 16.00</option>-->
                    <#--<option>16.00 - 17.00</option>-->
                <#--</select>-->
            <#--</div>-->
    </div>
    </div>

    </div>
    </div>


        <#--<div style="float: right; margin-top: 250px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15"/></div>-->
        <#--<div style="float:right; margin-top: 250px; margin-bottom: 10px; margin-right: 5px;" id="go-step2" class="btn btn-default textleft">-->
            <#--<img style="margin-right: 10px" src="img/prev_arrow.png" width="15" height="15"/>-->
        <#--${translator.getString("prev")}-->
        <#--</div>-->



    <div style="margin-top: 30px;" href="#" class="gallery__controls-prev" onclick="goToCart()">
        <img style="float: left" src="img/ar-left2.png" alt="" width="25" height="40" />
        <p style="text-decoration: underline; float: left; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_prev_step")}</p>
    </div>
    <div id="go_step3" style="margin-top: 30px;" href="#" class="gallery__controls-next">
        <img style="float: right" src="img/ar-right2.png" alt="" width="25" height="40"/>
        <p style="text-decoration: underline; float: right; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_next_step")}</p>
    </div>
    </form>
</div>

