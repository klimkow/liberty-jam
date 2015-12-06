<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kompliment Admin</title>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-1.10.0.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="page-header" style="text-align: center">
            <img src="img/main-logo.png" style="height:80px;"/>
        </div>
        <div class="row">
            <div class="row">
                <div class="span3 hidden-phone"></div>
                <div class="span6" id="form-login">
                    <form class="form-horizontal well" method="post">
                        <fieldset>
                            <legend>Sign in</legend>
                            <div class="control-group">
                                <div class="control-label">
                                    <label>Username</label>
                                </div>
                                <div class="controls">
                                    <input autofocus="autofocus" type="text" id="login" style="height: 30px" name="j_username" class="input-large"/>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="control-label">
                                    <label>Password</label>
                                </div>
                                <div class="controls">
                                    <input type="password" id="password" style="height: 30px" name="j_password" class="input-large"/>
                                </div>
                            </div>
                            <div style="text-align: center">
                                <div class="control-group">
                                    <div class="controls">
                                        <button type="submit" id="submit" class="btn btn-primary button-loading" data-loading-text="Loading...">Sign in</button>
                                    </div>

                                </div>
                            </div>
                            <#if show_error_message??>
                                <div class="alert alert-danger" role="alert">Incorrect username or password</div>
                            </#if>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>