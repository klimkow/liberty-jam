<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div style="margin-top: 9px" class="textleft margintop3">
            <img style="margin-right: 15px" src="img/cart.png" class="textleft" height="25" width="25">
            <div id="cart-notif" class="textleft">
            <#if order?? && itemCount gt 0>
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
                <a id="go-to-cart" class="btn btn-default" href="/cart" onclick="turnOnLoadingGlass()">${translator.getString("go_to_cart")}</a>
            <#else>${translator.getString("cart_is_empty")}
            </#if>
            </div>
        </div>
        <div class="textright">
            <a href="https://www.facebook.com/komplimentby/" target="_blank"><img style="border-right: 1px solid #E2E2E2; border-left: 1px solid #E2E2E2" src="img/fb-tr.png" alt="Facebook" height="48" width="48"></a>
            <a href="https://www.instagram.com/kompliment.by/" target="_blank"><img style="border-right: 1px solid #E2E2E2;" src="img/insta.png" alt="Instagram" height="48" width="48"></a>
            <a href="http://vk.com/kompliment_by" target="_blank"><img style="border-right: 1px solid #E2E2E2;" src="img/vk-tr.png" alt="Vkontakte" height="48" width="48"></a>
            <div id="dLabel" class="language-dropdown dropdown">
                <p  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="font-family:Attentica4F; font-weight: bold;">${translator.getCurrentLocale()}
                <span class="caret"></span>
                </p>
                <ul class="dropdown-menu" aria-labelledby="dLabel">
                    <#list translator.getAllowedLanguages() as lang>
                    <li onclick="changeLang('${lang}')"><a >${lang}</a> </li>
                    </#list>
                </ul>
            </div>
            <#--<div class="dropdown"><img style="border-right: 1px solid #E2E2E2;" src="img/ru_trs.png" alt="Language" height="48" width="48"></div>-->
        </div>

    </div>
    <div id="alert-zone"></div>
</nav>

<!-- NAVBAR END-->

<!-- TOP MENU & LOGO -->
<div id="top-menu" class="row row-bottom-border">
    <div class="container top-menu">
        <div class="text-center">
            <div class="row">
                <a href="/"><img src="img/main-logo.png" style="height:120px;"/></a>
            </div>
            <div class="row menu-item-container">
                <div class="menu-col" onclick="openPage('1')"><button class="btn-main-menu">${translator.getString("menu_item1")}</button></div>
                <div class="menu-col" onclick="chooseBoquete()"><button class="btn-main-menu">${translator.getString("menu_item2")}</button></div>
                <div class="menu-col" onclick="openPage('3')"><button class="btn-main-menu">${translator.getString("menu_item3")}</button></div>
                <div class="menu-col" onclick="openPage('4')"><button class="btn-main-menu">${translator.getString("menu_item4")}</button></div>
                <div class="menu-col" onclick="openPage('5')"><button class="btn-main-menu">${translator.getString("menu_item5")}</button></div>
            </div>
        </div>
    </div>


</div>

<!-- TOP MENU END -->