<div style="height: 100px; background-color: #f3f3f3;" class="row">


    <div class="container">

    <div style="margin-top: 30px;" href="#" class="gallery__controls-prev" onclick="goToCart()">
        <img style="float: left" src="img/ar-left.png" alt="" width="25" height="40" />
        <p style="text-decoration: underline; float: left; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_prev_step")}</p>
    </div>
    <div style="margin-top: 30px;" href="#" class="gallery__controls-next" onclick="show_step2()">
        <img style="float: right" src="img/ar-right.png" alt="" width="25" height="40"/>
        <p style="text-decoration: underline; float: right; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_next_step")}</p>
    </div>
        <div style="width: 60%; margin-left: 210px;">
            <img style="margin-top: 5px; margin-bottom: 5px" src="img/bi3.png" width="35" height="35"/>
            <div class="progress">
                <div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
                    <span class="sr-only">50% Complete</span>
                </div>
            </div>
                <div class="width33 progress-step1"><img style="float:left; margin-right: 6px; margin-top: 10px" src="img/ok_symb2.png" width="18" height="18"/><p>1.${translator.getString("step1")}</p></div>
                <div class="width33 progress-step2"><p style="font-size: 21pt;">2.${translator.getString("step2")}</p></div>
                <div style="color: #ABABAB;" class="width33 progress-step3"><p>3.${translator.getString("step3")}</p></div>
            </div>
        </div>
    <#--<div class="row container wizard-container text-center">-->
        <#--<div class="wizard-item-active-first"><h2>1.${translator.getString("step1")}</h2></div>-->
        <#--<div class="wizard-item">2.${translator.getString("step2")}</div>-->
        <#--<div class="wizard-item">3.${translator.getString("step3")}</div>-->
    <#--</div>-->
</div>
<div  class="container ">
    <form data-toggle="validator" role="form">
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
            <input type="text" pattern="^[_A-z]" maxlength="25" class="form-control has-success" id="from-name"  placeholder="${translator.getString("dlv_name_placeholder1")}">
            <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
            <span class="help-block with-errors">Hey look, this one has feedback icons!</span>
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">Email</label>
            <input type="email" class="form-control" id="from-email"  placeholder="${translator.getString("dlv_email_placeholder")}">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">${translator.getString("dlv_phone_number")}</label>
            <input style="padding: 0px 10px;" type="number" class="form-control" id="from-email"  placeholder="${translator.getString("dlv_phone_placeholder1")}">
        </div>
    </div>
    </div>
    </div>
    <div class="row dlv-container-block">
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
        <div class="dlv-block-cn form-group">
            <label for="to-name">${translator.getString("dlv_name")}</label>
            <input type="text" class="form-control" id="to-name"  placeholder="${translator.getString("dlv_name_placeholder2")}">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="to-phone">${translator.getString("dlv_phone_number")}</label>
            <input style="padding: 0px 10px;" type="number" class="form-control" id="to-phone"  placeholder="${translator.getString("dlv_phone_placeholder1")}">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="comment">${translator.getString("dlv_wish_comment")}</label>
            <textarea style="height: 70px" class="form-control" rows="5" id="comment"  placeholder="${translator.getString("dlv_wish_comment_placeholder")}"></textarea>
        </div>
    </div>
    </div>
    </div>

    <div class="row dlv-container-block">
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
        <div style="overflow:hidden;">
            <div class="dlv-block-cn form-group">
                <label for="comment">${translator.getString("dlv_deliver_date")}</label>
                <div class='input-group date' id='datetimepicker10'>
                <input type='text' class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar">
                    </span>
                </span>
                <script type="text/javascript">
                    $(function () {
                        $('#datetimepicker10').datepicker({
                            language: "ru"
                        });
                    });
                </script>
                </div>
            </div>
            <div class="dlv-block-cn form-group">
                <label>
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                ${translator.getString("dlv_delivery_free")}
                </label>
                <label>
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                ${translator.getString("dlv_delivery_accurate")}
                </label>
                <select style="padding: 0px 10px;" class="form-control">
                    <option>9.00 - 10.00</option>
                    <option>10.00 - 11.00</option>
                    <option>11.00 - 12.00</option>
                    <option>12.00 - 13.00</option>
                    <option>13.00 - 14.00</option>
                    <option>14.00 - 15.00</option>
                    <option>15.00 - 16.00</option>
                    <option>16.00 - 17.00</option>
                </select>
            </div>
    </div>
    </div>

    </div>
    </div>


        <#--<div style="float: right; margin-top: 250px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15"/></div>-->
        <#--<div style="float:right; margin-top: 250px; margin-bottom: 10px; margin-right: 5px;" id="go-step2" class="btn btn-default textleft">-->
            <#--<img style="margin-right: 10px" src="img/prev_arrow.png" width="15" height="15"/>-->
        <#--${translator.getString("prev")}-->
        <#--</div>-->


    </form>
</div>