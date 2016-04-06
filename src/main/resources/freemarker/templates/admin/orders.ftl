<!DOCTYPE html>
<html lang="en">
<script>

    function deleteOrders()
    {
        var idArray = 'orders_ids=';
        $(".select-checkox").each(function() {
            if ($(this).is(":checked")) {
                var id = $(this).next('input').val();
                idArray += id + '-';
            }
        });
        $.ajax({
            type: 'GET',
            url: "/administrator/delete_orders",
            data: idArray
        });
    }

</script>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Kompliment Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="../css/sb-admin.css" rel="stylesheet">


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

        <div style="min-height: 100vh;" id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Orders
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/administrator">Control panel</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Orders
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row" style="float: right"><span style="cursor: pointer; color:darkred" onclick="deleteOrders()">Delete</span></div>
                <div style="padding-top: 30px;" class="row">
                    <div style="width: 100%; height: 100%">
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>Name</th>
                                        <th>Date created</th>
                                        <th>Payment type</th>
                                        <th>Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#if orders??>
                                        <#list orders as order>
                                        <tr>
                                            <td><input id="isSelectedChBox" class="select-checkox" name="isSelectedChBox" type="checkbox">
                                                <input type="hidden" id="orderId" value="${order.getId()}">
                                            </td>
                                            <td onclick="redirect('orders/order?id=${order.getId()}')">${order.getUser().getName()} <#if order.getUser().getSurname()??>${order.getUser().getSurname()}</#if></td>
                                            <td onclick="redirect('orders/order?id=${order.getId()}')"><#if order.getDateCreatedView()??>${order.getDateCreatedView()}</#if></td>
                                            <td onclick="redirect('orders/order?id=${order.getId()}')"><#if order.getPayTypeView()??>${order.getPayTypeView()}</#if></td>
                                            <td onclick="redirect('orders/order?id=${order.getId()}')">${order.getAmount()}.000 BYR</td>
                                        </tr>
                                        </#list>
                                    </#if>
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
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/liberty-admin.js"></script>

</body>

</html>
