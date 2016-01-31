<!DOCTYPE html>
<html lang="en">
<head>
    <title>${translator.getString("index_title")}</title>
<#--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/liberty.css" rel="stylesheet" media="screen">
    <link href="css/nouislider.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css" />
    <link rel="apple-touch-icon" sizes="57x57" href="img/fav/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="img/fav/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="img/fav/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="img/fav/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="img/fav/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="img/fav/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="img/fav/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="img/fav/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="img/fav/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="img/fav/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="img/fav/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="img/fav/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="img/fav/favicon-16x16.png">
    <link rel="manifest" href="/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
</head>
<body>
<#--TODO: download jquery to libs folder (current downloaded is too slow)-->
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/validator.js"></script>
<script src="locales/bootstrap-datepicker.ru.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/wNumb.min.js"></script>
<script src="js/liberty.js"></script>

<#include "/common/navbar.ftl">
<div id="active-zone" class="row text-center">
    <div style="height: 500px;" class="container ">
        <div class="item-description-container">
            <#if paymentFailed??>
                <p class="attentica-font30 pages-title">${translator.getString("pay_res_denied")}</p>
                <p>${translator.getString("pay_res_denied_detail")}</p>
            <#else>
                <p class="attentica-font30 pages-title">${translator.getString("done")}!</p>
                <span style="text-align: justify;font-family: OsansLight, Arial, Helvetica, sans-serif;font-size: 15pt;">
                <p><b>${translator.getString("pay_res_order_id")} ${id}</b></p>
                <p>${translator.getString("pay_res_succes_detail")}</p>
                </span>
            </#if>
        </div>
    </div>
</div>
<#include "/common/carousel-small.ftl">
<#include "/common/footer.ftl">

</body>
</html>