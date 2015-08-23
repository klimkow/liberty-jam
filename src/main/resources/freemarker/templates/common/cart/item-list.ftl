<div style="height: 500px;" class="container ">
    <div class="row wizard-container text-center">
        <div class="wizard-item wizard-item-active"><h2>Букеты</h2></div>
        <div class="wizard-item">Подробности доставки</div>
        <div class="wizard-item">Оплата</div>
    </div>
    <div class="row" style="height: 350px; width: 60%; margin-top: 20px; margin-left: 20%; overflow: auto">
    <table class="table table-hover">
        <thead>
        <tr>
            <th></th>
            <th>Количество</th>
            <th>Стоимость</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list cartItems as item>
        <tr>
            <td class="textleft"><img class="img-circle textleft" src="${item.getLogo()}" height="100" width="100"> <p class="item-name-cart">${item.getName()}</p></td>
            <td style="padding-top: 30px" class="td-right">1</td>
            <td style="padding-top: 30px" class="td-right">${item.getPrice()?string?replace(",",".")}.000</td>
            <td class="td-right"><img style="margin-top: 30px;" src="img/delete.png" height="15" width="15"></td>
        </tr>
        </#list>
        </tbody>
    </table>
    </div>
    <div style="margin-top: 10px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">Далее<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15"/></div>
</div>