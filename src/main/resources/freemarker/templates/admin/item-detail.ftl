<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Kompliment Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="../../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../../css/sb-admin.css" rel="stylesheet">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

       <#include "/admin/navbar.ftl">

        <div style="height: 100vh;" id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 style="font-size: 25px;" class="page-header">
                            Букет ${item.getId()} - Подробности
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/administrator">Управление</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> <a href="/administrator/items">Цветы</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Подробности
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div style="width: 100%; height: 100%">
                        <div style="float: left; width: 40%">
                            <div style="border:2px solid #f3f3f3; background-color:#F3F3F3; <#if item??>background:
                                    url(../../${item.getLogo()}) no-repeat;background-size: 300px 300px;</#if> width: 300px; height: 300px">

                            </div>
                        </div>
                        <div style="float: left; width: 60%">
                            <form name="item_form" action="/administrator/items/save_item" method="post">
                            <input type="hidden" name="id" value="${item.getId()}">
                            <div class="dlv-block-cn form-group">
                                <label style="float: left; padding-right: 20px; padding-top: 7px;"
                                       for="b_name">Название</label>
                                <input style="width: 50%" type="text" name="b_name" class="form-control" id="b_name"
                                       <#if item??>value="${item.getName()}"</#if>>
                            </div>
                            <div class="dlv-block-cn form-group">
                                <label style="float: left; padding-right: 20px;"
                                       for="b_cat">Категория</label>
                                <select name="b_cat">
                                    <option value="classic" <#if item??>
                                        <#if item.getCategoryName() == 'classic'>selected</#if></#if>>
                                            Классический</option>
                                    <option value="box" <#if item??>
                                            <#if item.getCategoryName() == 'box'>selected</#if></#if>>В коробке</option>
                                    <option value="vase" <#if item??>
                                            <#if item.getCategoryName() == 'vase'>selected</#if></#if>>В вазе</option>
                                </select>
                            </div>
                            <div class="dlv-block-cn form-group">
                                <label style="float: left; padding-right: 52px; padding-top: 7px;"
                                       for="b_price">Цена</label>
                                <input style="float: left; width: 25%;" type="number" name="b_price" class="form-control" id="b_price"
                                       <#if item??>value="${item.getPrice()?string?replace(",","")}"</#if>>
                                <p style="padding-top: 7px;">.000 BYR</p>
                            </div>
                            <div style="padding-top: 7px;" class="dlv-block-cn form-group">
                                <label style="float: left; padding-right: 20px; padding-top: 7px;"
                                       for="b_desc">Описание</label>
                                <textarea style="width: 50%; height: 300px" type="text"
                                          name="b_desc" class="form-control" id="b_desc"><#if item??>${item.getDescription()}</#if>
                                </textarea>
                            </div>
                                <a class="btn btn-default" style="float: right; margin-top: 10px;margin-left: 10px;" href="/administrator/items">Отмена</a>
                                <button class="btn btn-default" style="float: right; margin-top: 10px;" type="submit">Сохранить</button>
                            </form>
                        </div>

                    </div>

                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="../../js/bootstrap.min.js"></script>
    <script src="../../js/liberty-admin.js"></script>

</body>

</html>
