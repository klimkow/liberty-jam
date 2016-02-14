<!DOCTYPE html>
<html lang="en">
<head>
    <title>${translator.getString("index_title")}</title>
    <description>${translator.getString("index_description")}</description>
<#--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/liberty.css" rel="stylesheet" media="screen">
    <link href="css/nouislider.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap-datepicker.min.css" /><link rel="stylesheet" href="css/bootstrap-datepicker.min.css" />
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
<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-PH29GX"
                  height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
        new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
        j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
        '//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-PH29GX');</script>
<!-- End Google Tag Manager -->

<#--TODO: download jquery to libs folder (current downloaded is too slow)-->
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/validator.js"></script>
<script src="locales/bootstrap-datepicker.ru.min.js"></script>
<script src="js/nouislider.min.js"></script>
<script src="js/wNumb.min.js"></script>
<script src="js/liberty.js"></script>
<script type="text/javascript" charset="utf-8"
        src="https://api-maps.yandex.ru/services/constructor/1.0/js/?sid=MLV6Yv2Rxa7Tiv2JgPZiGNl4PNcjN9An&width=917&height=559&lang=ru_RU&sourceType=constructor&id=map-hidden"></script>

<#include "/common/navbar.ftl">
<div id="active-zone" class="row text-center">
<#if order??&&itemCount gt 0>
<#include "item-list.ftl">
<#else>
    <#include "empty-stub.ftl">
</#if>
</div>
<#include "/common/carousel-small.ftl">
<#include "/common/footer.ftl">

 <#--RENDER MAP IN MODAL DIALOG-->
<div id="mapModal" class="modal fade bs-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-lg">
        <div id="map-hidden" class="dv-zone modal-content">
        </div>
    </div>
</div>
</body>
</html>