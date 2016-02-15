<script>

    function updateDisabledTime(dateParam)
    {
        turnOnLoadingGlassWithParam(true);
        $.ajax({
            type: 'POST',
            url: "getTimesForDate",
            data: {date : dateParam},
            success: function(msg) {
                var ar = msg.split("&");
                $('.dlv-time-item').each(function(i, obj) {
                    var id = $(this).attr('id').replace('timeOption', '');
                    var isDisabled = ar.indexOf(id) != '-1';
                    if (isDisabled) {
                        $(this).addClass("dlv-time-item-disabled");
                        $(this).removeClass("dlv-time-available");
                    } else {
                        $(this).removeClass("dlv-time-item-disabled");
                        if (!$(this).hasClass("dlv-time-available")) {
                            $(this).addClass("dlv-time-available");
                        }
                    }
                });
                for (i = 0; i < ar.length; i++) {
                    $('#timeOption' + ar[i]).addClass("dlv-time-item-disabled");
                }
                turnOnLoadingGlassWithParam(false);
            }
        });
    }

    $(document).ready(function() {

        // SELECT TIME IF CHOSE

        <#if delivery??>
            <#if delivery.getTimeRange()??>
                var time = ${delivery.getTimeRange()};
                $("#timeOption" + time).addClass("dlv-time-item-selected");
                document.getElementById('time').value = time;
            </#if>

            // SELECT FREE PRICE OPTION BY DEFAULT
            $("#zone${delivery.getDeliveryPrice()}").attr('checked', 'checked');

            // SHOW COLLAPSED INPUTS IF THEY WAS SELECTED
            <#if isAnotherPerson == true>
                $("#inlineRadio1").attr('checked', 'checked');
                $('.collapse').collapse('show');
            </#if>
        </#if>

        $(("input[name='dlv_zone_radio']")).change(function() {
            needUpdateNavBar = true;
        });

        updateDisabledTime(new Date());

        // INITIALIZE VALIDATOR
        $('#dlv-form').validator();

        // DELEGATE CLICK EVENT OF TIME CONTAINER
        $("#timeOptnsContainer").delegate('div[id^=timeOption]','click', function(e) {
            var isDisabled = $(this).hasClass("dlv-time-item-disabled");
            if (!isDisabled) {
                $("div[id^= 'timeOption']").removeClass("dlv-time-item-selected");
                $(this).addClass("dlv-time-item-selected");
                var id = $(this).attr('id');
                document.getElementById('time').value = id.replace('timeOption', '');
            }
        });
    });

    // ON FORM SUBMIT
    $('#dlv-form').validator().on('submit', function (e) {

        if (!e.isDefaultPrevented()) {
            e.preventDefault();
            if (document.getElementById('date').value == null ||
                    document.getElementById('date').value == '') {
                showValidationWarning('${translator.getString("alert_fill_date_and_time")}');
                return;
            }
            if (document.getElementById('time').value == null ||
                    document.getElementById('time').value == '') {
                showValidationWarning('${translator.getString("alert_fill_date_and_time")}');
                return;
            }

            $('#alert-zone').html('');
            var form = $(this).closest('form');
            var formData = form.serialize();
            show_step3(formData);
        } else {
            showValidationWarning('${translator.getString("alert_fill_all_fields")}');
        }
    });

    // ON GO TO THE NEXT STEP
    $('#go_step3').click(function(e) {
        document.getElementById('date').value = $('#datetimepicker10 div').datepicker('getFormattedDate');
        $('#date').val($('#datetimepicker10 div').datepicker('getDate'));
        var form = $(this).closest('form');

        var formData = form.serialize();
//        show_step3(formData);
        form.submit();
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
        <img style="margin-left: 220px; margin-top:90px;" src="img/icon_sender.png" width="120" height="120"/>
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
        <div class="dlv-block-cn form-group has-feedback">
            <label for="from-surname">${translator.getString("dlv_surname")}</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="text" name="surname-from" pattern="${translator.getString('name_reg_exp')}{1,}$"
                   maxlength="55" class="form-control has-success" id="from-surname" <#if user??>value="${user.getSurname()}" </#if> required>
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
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="text" name="phone-from" pattern="[+\d\s]{1,}$" maxlength="25" class="form-control"
                   id="from-tel"
                   <#if user??>value="${user.getPhone()}"</#if>
                   <#--data-toggle="popover" data-content="Мы воспользуемся этим номером телефона, если у нас возникнут трудности с доставкой"-->
                   required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="from-email">Email</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="email" name="email-from" class="form-control" id="from-email"
                   <#if user??>value="${user.getEmail()}"</#if> required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="dlv-block-cn form-group">
            <label for="comment">${translator.getString("dlv_wish_comment")}</label>
            <textarea style="height: 70px" name="message" class="form-control" rows="5" id="comment" placeholder="${translator.getString("dlv_wish_comment_placeholder")}"></textarea>
            <span class="dlv-description-small">${translator.getString("punctuation_warning")}</span>
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label>
                ${translator.getString("dlv_who_is_reciever")}
            </label>
            <label style="padding-left: 30px;" class="radio-inline">
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
        <img style="margin-left: 220px; margin-top:20px;" src="img/icon_recipient.png" width="120" height="120"/>
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
            <input type="text" name="name-to" pattern="${translator.getString('name_reg_exp')}{1,}$"
                   <#if delivery??>value="${delivery.getName()}"</#if> maxlength="55" class="form-control" id="to-name" required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>
        <div class="dlv-block-cn form-group has-feedback">
            <label for="to-phone">${translator.getString("dlv_phone_reciever")}</label>
            <span class="glyphicon glyphicon-asterisk"></span>
            <input type="text" name="phone-to" pattern="[+\d\s]{1,}$" maxlength="25"
                   <#if delivery??>value="${delivery.getPhone()}"</#if> class="form-control" id="to-phone" required>
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
        </div>

    </div>
    </div>
    </div>

</div>
</div>
    <#--=============================== COLLAPSABLE MENU END ===============================-->

    <div class="row dlv-container-block">
    <div id="dlv-bock-title" class="row text-center">

    </div>

    <div class="row">
    <div style="float:left">
    <img style="margin-left: 220px; margin-top:130px;" src="img/icon_delivery.png" width="120" height="120"/>
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
                <input type="text" name="address"
                       maxlength="55" class="form-control" id="to-name"
                       <#if delivery??>value="${delivery.getAddress()}"</#if> required>
                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            </div>
            <div class="delivery-zone">
                <span class="map-link" data-toggle="modal" data-target=".bs-modal-lg">${translator.getString("delivery_zone")}</span>
            </div>
            <div style="padding-bottom: 5px;" class="dlv-block-cn form-group">
                <div class="radio">
                    <label>
                        <input type="radio" id="zone1" name="dlv_zone_radio" value="1" checked>
                        ${translator.getString("delivery_zone1")}</label>
                </div>
                <div class="radio">
                    <label><input type="radio" id="zone2" name="dlv_zone_radio" value="2">${translator.getString("delivery_zone2")}</label>
                </div>
                <div class="radio">
                    <label><input type="radio" id="zone3" name="dlv_zone_radio" value="3">${translator.getString("delivery_zone3")}</label>
                </div>
            </div>
            <div style="float:left; width: 33%;" class="dlv-block-cn form-group has-feedback">
                <label for="address-house">${translator.getString("address_house")}</label>
                <span class="glyphicon glyphicon-asterisk"></span>
                <div style="width: 70%">
                    <input type="text" name="address-house" maxlength="10" class="form-control" id="to-phone"
                           <#if delivery??>value="${delivery.getAddressHouse()}"</#if> required>
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>
            </div>
            <div style="float:left; width: 33%;" class="dlv-block-cn form-group has-feedback">
                <label for="address-door">${translator.getString("address_apartments")}</label>
                <div style="width: 70%">
                    <input type="text" name="address-door" maxlength="10" class="form-control"
                           <#if delivery??>value="${delivery.getAddressDoor()}"</#if> id="address-door" required="">
                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                </div>
            </div>
            <div style="float:left; width: 33%;" class="dlv-block-cn form-group has-feedback">
                <label for="address-flor">${translator.getString("dlv_floor")}</label>
                <div style="width: 70%">
                    <input type="text" name="address-flor" maxlength="10" class="form-control"
                           <#if delivery??>value="${delivery.getAddressFloor()}"</#if> id="to-phone">
                </div>
            </div>
            <div class="dlv-block-cn form-group">
                <div style="float: left; width: 40%;font-family: BadScript;font-size: 13pt;">
                    <div style="border-bottom: 1px #B1AEAE solid;">
                        <label for="comment">${translator.getString("dlv_deliver_date")}</label>
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </div>
                    <div style="float: left;" class='input-group date'
                         id='datetimepicker10' >
                        <input id="date" type="hidden" name="date">
                        <div class="attentica-font16" <#if delivery??>data-date="${delivery.getDateView()}"</#if>></div>
                        <script type="text/javascript">
                            $(function () {
                                var startDate = new Date();
                                $('#datetimepicker10 div').datepicker({
                                    startDate: startDate,
                                    datesDisabled: ['09/06/2015', '09/21/2015'],
                                    language: "ru"
                                }).on('changeDate', function (ev) {
                                    $("div[id^= 'timeOption']").addClass("dlv-time-available");
                                    $("div[id^= 'timeOption']").removeClass("dlv-time-item-selected");
                                    updateDisabledTime($('#datetimepicker10 div').datepicker('getDate'));
                                }).datepicker("setDate", "0");
                            });
                        </script>
                    </div>

                </div>
                <div style="font-family: BadScript; font-size: 13pt;float: right;  width: 50%;">
                    <div style="margin-bottom:10px; width:99%;
                    border-bottom: 1px #B1AEAE solid;" class="row">
                        <label for="comment">${translator.getString("dlv_deliver_time")}</label>
                        <span class="glyphicon glyphicon-asterisk"></span>
                    </div>
                    <div id="timeOptnsContainer">
                        <div id="timeOption1" class="dlv-time-item">07.00 - 09.00</div>
                        <div id="timeOption2" class="dlv-time-item">09.00 - 11.00</div>
                        <div id="timeOption3" class="dlv-time-item">11.00 - 13.00</div>
                        <div id="timeOption4" class="dlv-time-item">13.00 - 15.00</div>
                        <div id="timeOption5" class="dlv-time-item">15.00 - 17.00</div>
                        <div id="timeOption6" class="dlv-time-item">17.00 - 19.00</div>
                        <input id="time" type="hidden" name="time">
                    </div>
                </div>

            </div>
    </div>
    </div>

    </div>
    </div>


    <div class="gallery__controls-prev" onclick="goToCart()">
        <img style="float: left" src="img/ar-left2.png" alt="" width="25" height="40" />
        <p>${translator.getString("go_prev_step")}</p>
    </div>
    <div id="go_step3" class="gallery__controls-next">
        <img style="float: right" src="img/ar-right2.png" alt="" width="25" height="40"/>
        <p>${translator.getString("go_next_step")}</p>
    </div>
    </form>
</div>

