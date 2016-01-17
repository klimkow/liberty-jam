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
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
    <link rel="icon" href="img/favicon.ico" type="image/x-icon">
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

// RENDER MAP IN MODAL DIALOG
<div id="mapModal" class="modal fade bs-modal-lg" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-lg">
        <div id="map-hidden" class="dv-zone modal-content">
        </div>
    </div>
</div>
</body>
</html>