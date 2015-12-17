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
                            Заказ ${order.getId()} - Подробности
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/administrator">Управление</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> <a href="/administrator/orders">Заказы</a>
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
                        <p>Name: ${user.getName()}</p>
                        <p>Email: ${user.getEmail()}</p>
                        <p>Phone: ${user.getPhone()}</p>
                        <#--<p>Address: <#if user.getAddress != null>${user.getAddress()}</#if></p>-->

                        <div class="row" style="height: 250px; width: 100%; margin-top: 20px; overflow: auto">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>${translator.getString("amount")}</th>
                                    <th>${translator.getString("price")}</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <#list order.getItems() as item>
                                <tr id="item-list-tr">
                                    <td class="textleft"><img class="img-circle textleft" src="../../../${item.getLogo()}" height="100" width="100"> </td>
                                    <td style="padding-top: 30px" ><p >${item.getName()}</p></td>
                                    <td style="padding-top: 30px" class="td-right">
                                        1
                                    </td>
                                    <td style="padding-top: 30px" class="td-right">${item.getPrice()?string?replace(",",".")}.000</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
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
