<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div style="margin-top: 9px" class="textleft margintop3">
            <img style="margin-right: 15px" src="img/cart.png" class="textleft" height="25" width="25">
            <div id="cart-notif" class="textleft">
            <#if order??>
                ${translator.getString("you_have")} ${itemCount}
                    <#if itemCount == 1>
                    ${translator.getString("bouquet1")}
                    </#if>
                    <#if itemCount gt 1>
                        <#if itemCount < 5>
                        ${translator.getString("bouquet24")}
                        <#else>
                        ${translator.getString("bouquet5")}
                    </#if>
            </#if>
                ${translator.getString("total_amount")} ${order.getAmount()?string?replace(",",".")}${translator.getString("currency")}
            <#else>${translator.getString("cart_is_empty")}
            </#if>
            </div>
        </div>
        <div class="textright">
            <img style="border-right: 1px solid #E2E2E2; border-left: 1px solid #E2E2E2" src="img/fb-tr.png" alt="Facebook" height="48" width="48">
            <img style="border-right: 1px solid #E2E2E2;" src="img/twt-tr.gif" alt="Twitter" height="48" width="48">
            <img style="border-right: 1px solid #E2E2E2;" src="img/vk-tr.png" alt="Vkontakte" height="48" width="48">
            <img style="border-right: 1px solid #E2E2E2;" src="img/ru_trs.png" alt="Language" height="48" width="48">
        </div>
    </div>
</nav>

<!-- NAVBAR END-->

<!-- TOP MENU & LOGO -->
<div class="row row-bottom-border">
    <div class="container top-menu">
        <div class="text-center">
            <div class="row">
                <img src="img/main-logo.png" style="height:120px;"/>
            </div>
            <div class="row menu-item-container">
                <div class="menu-col"><button class="btn-main-menu">${translator.getString("choose_bouquet_title")}</button></div>
                <div class="menu-col"><button class="btn-main-menu">${translator.getString("reason_title")}</button></div>
                <div class="menu-col"><button class="btn-main-menu">${translator.getString("how_does_it_work_title")}</button></div>
                <div class="menu-col"><button class="btn-main-menu">${translator.getString("about_title")}</button></div>
                <div class="menu-col"><button class="btn-main-menu">${translator.getString("contacts_title")}</button></div>
            </div>
        </div>
    </div>


</div>

<!-- TOP MENU END -->