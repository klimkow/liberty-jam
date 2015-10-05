<script>
    $(document).ready(function(){

        $("#go-step2").click(function(e) {
            show_step2();
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
            <div style="color: #ABABAB;" class="width33 progress-step2" onclick="show_step2()"><p>2.${translator.getString("step2")}</p></div>
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
    <div class="row" style="height: 250px; width: 100%; margin-top: 20px; overflow: auto">
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
            <td style="padding-top: 30px" class="td-right">1</td>
            <td style="padding-top: 30px" class="td-right">${item.getPrice()?string?replace(",",".")}.000</td>
            <td class="td-right"><img style="margin-top: 30px;" src="img/delete.png" height="15" width="15"></td>
        </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <div style="margin-top: 90px;" href="#" class="gallery__controls-next" onclick="show_step2()">
        <img style="float: right" src="img/ar-right.png" alt="" width="25" height="40"/>
        <p style="text-decoration: underline; float: right; font-family: Attentica4F; font-weight: bold;font-size: 20pt;">${translator.getString("go_next_step")}</p>
    </div>
    <#--<div style="margin-top: 10px; margin-bottom: 10px;" id="go-step2" class="btn btn-default textright">${translator.getString("next")}<img style="margin-left: 10px" src="img/next_arrow.png" width="15" height="15""/></div>-->
</div>

<#include "/common/carousel-small.ftl">