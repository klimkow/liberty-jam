<div style="height: 500px;" class="container ">
    <div class="row wizard-container text-center">
        <div class="wizard-item-active-first"><h2>1.${translator.getString("step1")}</h2></div>
        <div class="wizard-item">2.${translator.getString("step2")}</div>
        <div class="wizard-item">3.${translator.getString("step3")}</div>
    </div>
    <div style="" class="dlv-block">
        <h2>От кого</h2>
        <div class="dlv-block-cn form-group">
            <label for="from-name">Имя</label>
            <input type="text" class="form-control" id="from-name">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">Email</label>
            <input type="email" class="form-control" id="from-email">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">Номер телефона</label>
            <input type="number" class="form-control" id="from-email">
        </div>
    </div>
    <div style="" class="dlv-block">
        <h2>Кому</h2>
        <div class="dlv-block-cn form-group">
            <label for="to-name">Имя</label>
            <input type="text" class="form-control" id="to-name">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="to-phone">Номер телефона</label>
            <input type="number" class="form-control" id="to-phone">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="comment">Послание</label>
            <textarea class="form-control" rows="5" id="comment"></textarea>
        </div>
    </div>
    <div style="border-right: none" class="dlv-block">

        <h2>Подробности доставки</h2>
        <div style="overflow:hidden;">
            <div class="dlv-block-cn form-group">
                <label for="comment">Дата</label>
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
                    Бесплатная доставка
                </label>
                <label>
                    <input type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                    Доставка в указанное время
                </label>
                <select style="padding-left: 15px" class="form-control">
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

        <div style="float: right; margin-top: 90px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15" onclick="show_step2()"/></div>
        <div style="float:right; margin-top: 90px; margin-bottom: 10px; margin-right: 5px;" id="go-step2" class="btn btn-default textleft">
            <img style="margin-right: 10px" src="img/prev_arrow.png" width="15" height="15" onclick="show_step2()"/>
        ${translator.getString("prev")}
        </div>



</div>