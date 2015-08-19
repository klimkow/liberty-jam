<!DOCTYPE html>
<html lang="en">
<head>
    <title>Комплимент - Цветочный бутик</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/liberty.css" rel="stylesheet" media="screen">
    <link href="css/nouislider.min.css" rel="stylesheet">
</head>
<body onload="createSlider()">
    <#--TODO: download jquery to libs folder (current downloaded is too slow)-->
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/nouislider.min.js"></script>
    <script src="js/liberty.js"></script>

    <#include "/common/navbar.ftl">
    <div id="active-zone" class="row text-center">
        <#include "/common/carousel.ftl">
    </div>
    <#include "/common/filter.ftl">
    <div id="marketing-active-zone">
        <#--<#include "/common/marketing.ftl">-->
        <#include "/common/download-stub.ftl">
    </div>
    <#include "/common/footer.ftl">

</body>
</html>