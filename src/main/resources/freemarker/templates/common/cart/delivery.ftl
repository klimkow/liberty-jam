<div style="height: 120px; background-color: #f3f3f3;" class="row">
    <div class="row container wizard-container text-center">
        <div class="wizard-item-active-first"><h2>1.${translator.getString("step1")}</h2></div>
        <div class="wizard-item">2.${translator.getString("step2")}</div>
        <div class="wizard-item">3.${translator.getString("step3")}</div>
    </div>
</div>
<div style="height: 500px;" class="container ">
    <div class="row dlv-container-block">
    <div class="row">
    <div style="float:left">
        <img style="margin-left: 220px; margin-top:50px;" src="img/lorry.svg.hi.png" width="120" height="75"/>
        <div style="margin-left: 220px; width: 200px;font-family: BadScript;
            font-size: 15pt; font-weight: bold; color: #929090">
            Выберите доступные дату и время доставки, а так же адрес
        </div>
    </div>
    <div style="" class="dlv-block textright">
        <h2>От кого</h2>
        <div class="dlv-block-cn form-group">
            <label for="from-name">Имя</label>
            <input type="text" class="form-control has-success" id="from-name"  placeholder="Ваше имя, к примеру Самуил Яковлевич">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">Email</label>
            <input type="email" class="form-control" id="from-email"  placeholder="Чтоб сообщать вам о статусе доставки">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="from-email">Номер телефона</label>
            <input style="padding: 0px 10px;" type="number" class="form-control" id="from-email"  placeholder="Код страны, код оператора и номер телефона">
        </div>
    </div>
    </div>
    </div>
    <div class="row dlv-container-block">
    <div id="dlv-bock-title" class="row text-center">

    </div>
    <div class="row">
    <div style="float:left">
        <img style="margin-left: 220px; margin-top:60px;" src="img/ci12.png" width="90" height="120"/>
        <div style="margin-left: 220px; width: 200px;font-family: BadScript;
             font-size: 15pt; font-weight: bold; color: #929090">
            Выберите доступные дату и время доставки, а так же адрес
        </div>
    </div>
    <div style="" class="dlv-block textright">
        <h2>Кому</h2>
        <div class="dlv-block-cn form-group">
            <label for="to-name">Имя</label>
            <input type="text" class="form-control" id="to-name"  placeholder="Имя счастливицы, к примеру Маргарита Павловна">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="to-phone">Номер телефона</label>
            <input style="padding: 0px 10px;" type="number" class="form-control" id="to-phone"  placeholder="+375 29 1234567">
        </div>
        <div class="dlv-block-cn form-group">
            <label for="comment">Послание</label>
            <textarea style="height: 70px" class="form-control" rows="5" id="comment"  placeholder="Оставьте пару приятных слов на память"></textarea>
        </div>
    </div>
    </div>
    </div>

    <div class="row dlv-container-block">
    <div id="dlv-bock-title" class="row text-center">

    </div>

    <div class="row">
    <div style="float:left">
    <img style="margin-left: 220px; margin-top:50px;" src="img/lorry.svg.hi.png" width="120" height="75"/>
    <div style="margin-left: 220px; width: 200px;font-family: BadScript;
    font-size: 15pt; font-weight: bold; color: #929090">
      Выберите доступные дату и время доставки, а так же адрес
    </div>
    </div>

    <div style="margin-bottom: 20px" class="dlv-block textright">
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



</div>