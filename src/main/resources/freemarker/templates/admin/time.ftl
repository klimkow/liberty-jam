<script src="http://code.jquery.com/jquery.js"></script>

<script>
    function updateDisabledTime(dateParam)
    {
        turnOnLoadingGlass(true);
        $.ajax({
            type: 'POST',
            url: "getTimesForDate",
            data: {date : dateParam},
            success: function(msg) {
                var ar = msg.split("&");
                $('.dlv-time-item').each(function(i, obj) {
                    var id = $(this).attr('id').replace('timeOption', '');
                    var isDisabled = ar.indexOf(id) != '-1';
                    if (isDisabled) {
                        $(this).addClass("dlv-time-item-disabled");
                    } else {
                        $(this).removeClass("dlv-time-item-disabled");
                    }
                });
                for (i = 0; i < ar.length; i++) {
                    $('#timeOption' + ar[i]).addClass("dlv-time-item-disabled");
                }
                turnOnLoadingGlass(false);
            }
        });
    }

    $(document).ready(function() {

        // DELEGATE CLICK EVENT OF TIME CONTAINER
        $("#timeOptionsContainer").delegate('div[id^=timeOption]','click', function(e) {
            $(this).toggleClass("dlv-time-item-disabled");
            var isDisabled = $(this).hasClass("dlv-time-item-disabled");
            $(this).attr("disabled", isDisabled);
            var id = $(this).attr('id').replace('timeOption', '');
            document.getElementById('time' + id).value = isDisabled;
        });

        $( "#timeForm" ).submit(function( event ) {
            event.preventDefault();
            $('#date').val($('#datetimepicker10 div').datepicker('getDate'));
            var form = $(this).closest('form');
            var data = form.serialize();
            var url = "disableTime";
            postRequest(data, url);
        });

        updateDisabledTime(new Date());
    });
</script>

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
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/bootstrap-datepicker.min.css" />

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
                                <i class="fa fa-table"></i> Time management
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <form method="POST" id="timeForm" name="timeForm">
                <div style="width: 100%; height: 100%">
                    <div class="row">
                        <div style="float: left; width: 40%;font-family: BadScript;font-size: 13pt;">
                            <div style="border-bottom: 1px #B1AEAE solid;">
                                <label for="comment">${translator.getString("dlv_deliver_date")}</label>
                            </div>
                            <div style="float: left;" class='input-group date'
                                 id='datetimepicker10' >
                                <input id="date" type="hidden" name="date">
                                <div class="attentica-font16" <#if delivery??>data-date="${delivery.getDateView()}"</#if>></div>
                                <script type="text/javascript">
                                    $(function () {
                                        var startDate = new Date();
                                        $('#datetimepicker10 div').datepicker({
                                            startDate: startDate,
                                            datesDisabled: ['09/06/2015', '09/21/2015'],
                                            language: "ru"
                                        }).on('changeDate', function (ev) {
                                            updateDisabledTime($('#datetimepicker10 div').datepicker('getDate'));
                                        }).datepicker("setDate", "0");
                                    });
                                </script>
                            </div>

                        </div>
                        <div style="font-family: BadScript; font-size: 13pt;float: right;  width: 50%;">
                            <div style="margin-bottom:10px; width:99%;
                    border-bottom: 1px #B1AEAE solid;" class="row">
                                <label for="comment">${translator.getString("dlv_deliver_time")}</label>
                            </div>
                            <div id="timeOptionsContainer">
                                <div id="timeOption1" class="dlv-time-item">09.00 - 11.00</div>
                                <div id="timeOption2" class="dlv-time-item">11.00 - 13.00</div>
                                <div id="timeOption3" class="dlv-time-item">13.00 - 15.00</div>
                                <div id="timeOption4" class="dlv-time-item">15.00 - 17.00</div>
                                <div id="timeOption5" class="dlv-time-item">17.00 - 19.00</div>
                                <div id="timeOption6" class="dlv-time-item">19.00 - 21.00</div>
                                <input id="time1" type="hidden" name="time1">
                                <input id="time2" type="hidden" name="time2">
                                <input id="time3" type="hidden" name="time3">
                                <input id="time4" type="hidden" name="time4">
                                <input id="time5" type="hidden" name="time5">
                                <input id="time6" type="hidden" name="time6">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <button class="btn btn-default" style="float: right;" type="submit">Apply</button>
                    </div>
                </div>
                </form>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <#--<script src="http://code.jquery.com/jquery.js"></script>-->

    <!-- Bootstrap Core JavaScript -->
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/liberty-admin.js"></script>
    <script src="../js/bootstrap-datepicker.min.js"></script>
    <script src="../js/bootstrap-datepicker.js"></script>
    <script src="../locales/bootstrap-datepicker.ru.min.js"></script>

</body>

</html>
