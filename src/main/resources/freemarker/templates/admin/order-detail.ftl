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


    <#--<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries &ndash;&gt;-->
    <#--<!-- WARNING: Respond.js doesn't work if you view the page via file:// &ndash;&gt;-->
    <#--<!--[if lt IE 9]>-->
        <#--<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>-->
        <#--<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>-->
    <#--<![endif]&ndash;&gt;-->

</head>

<body>

    <div id="wrapper">

       <#include "/admin/navbar.ftl">

        <div style="height: 100%;" id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 style="font-size: 25px;" class="page-header">
                            Order ${order.getId()} - Description
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="/administrator">Control panel</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> <a href="/administrator/orders">Orders</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-table"></i> Description
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <div class="row">
                    <div style="width: 100%; height: 100%">
                        <div style="float: left; width: 50%;">
                            <div class="panel panel-default">
                                <div class="panel-heading">Buyer information</div>
                                <div class="panel-body">
                                    <p><b>Name:</b> <#if user.getName()??>${user.getName()}</#if></p>
                                    <p><b>Surname:</b> <#if user.getSurname()??>${user.getSurname()}</#if></p>
                                    <p><b>Email:</b> <#if user.getEmail()??>${user.getEmail()}</#if></p>
                                    <p><b>Phone:</b> <#if user.getPhone()??>${user.getPhone()}</#if></p>
                                </div>
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <p><b>Date created:</b> <#if order.getDateCreatedView()??>${order.getDateCreatedView()}</#if></p>
                                        <p><b>Payment type:</b> <#if order.getPayTypeView()??>${order.getPayTypeView()}</#if></p>
                                        <#if order.getPaymentType()?? && order.getPaymentType() == 2>
                                            <p><b>Payment confirmation number:</b> <#if order.getConfirmationNumber()??>${order.getConfirmationNumber()}</#if></p>
                                            <FORM name="check_payment_form" METHOD="POST" action="https://pay139.paysec.by/orderresult/orderresult.cfm">
                                                <INPUT TYPE="HIDDEN" NAME="Ordernumber" VALUE="${order.getId()}">
                                                <INPUT TYPE="HIDDEN" NAME="Merchant_ID" VALUE="477696">
                                                <INPUT TYPE="HIDDEN" NAME="Login" VALUE="EugeneSal0v">
                                                <INPUT TYPE="HIDDEN" NAME="Password" VALUE="htcwildfire132">
                                                <INPUT TYPE="HIDDEN" NAME="Format" VALUE="3">
                                                <button class="btn btn-default" type="submit">Check payment aprovement</button>
                                                <#--<DIV class="btn btn-default" onclick="checkPayment()">Check payment aprovement</div>-->
                                            </FORM>
                                        </#if>
                                    </li>
                                </ul>
                                <div style="font-size: 14pt;" class="panel-footer text-center">
                                    <p><b>Order total price: <#if order.getAmount()??>${order.getAmount()}.</#if>000 BYR</b></p>
                                </div>

                            </div>
                        </div>
                        <div style="float:right; width:40%;">
                            <div class="panel panel-default">
                                <div class="panel-heading">Deliver to</div>
                                <div class="panel-body">
                                    <p><b>Name:</b> <#if info.getName()??>${info.getName()}</#if></p>
                                    <p><b>Phone:</b> <#if info.getPhone()??>${info.getPhone()}</#if></p>
                                    <p><b>Wish:</b> <#if info.getMessage()??>${info.getMessage()}</#if></p>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">Delivery time</div>
                                <div class="panel-body">
                                    <p><b>Delivery date:</b> <#if info.getDateView()??>${info.getDateView()}</#if></p>
                                    <p><b>Delivery time:</b> <#if info.getTimeView()??>${info.getTimeView()}</#if></p>
                                </div>
                            </div>
                            <br>
                            <div class="panel panel-default">
                                <div class="panel-heading">Delivery address</div>
                                <div class="panel-body">
                                    <p><b>Street:</b> <#if info.getAddress()??>${info.getAddress()}</#if></p>
                                    <p><span style="float: left; width: 33%"><b>House:</b> <#if info.getAddressHouse()??>${info.getAddressHouse()}</#if></span>
                                    <span style="float: left; width: 33%"><b>Door:</b> <#if info.getAddressDoor()??>${info.getAddressDoor()}</#if></span>
                                    <span style="float: left; width: 33%"><b>Floor:</b> <#if info.getAddressFloor()??>${info.getAddressFloor()}</#if></span></p>
                                    <p><b>Delivery zone:</b> <#if info.getDeliveryPrice()??>${info.getDeliveryPrice()}</#if></p>
                                </div>
                            </div>
                        </div>
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
                                    <td>
                                        <div class="item-name-cart-block">
                                            <p class="item-name-cart">${item.getName()}</p>
                                            <#if order.getIQWithItem(item).isWithPaper()><span>${translator.getString("item_paper_option")}</span><br></#if>
                                            <#if order.getIQWithItem(item).isWithVase()><span>${translator.getString("item_vase_option_added")}</span></#if>
                                        </div>
                                    </td>
                                    <td style="padding-top: 30px" class="td-right">
                                        ${order.getIQWithItem(item).getItemQuantity()}
                                    </td>
                                    <td style="padding-top: 30px" class="td-right">${item.getPriceIncludingDiapasons(order.getIQWithItem(item).getItemQuantity())?string?replace(",",".")}.000</td>
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
