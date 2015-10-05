<script>
    $(document).ready(function(){

        $("#filter-container").delegate('#filter_option','click', function(e) {
            $(this).toggleClass("button-selected");
            $('#marketing-active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
            var form = $(this).closest('form');
            var formData = form.serialize();
            filter(formData);
        });


    });
</script>

<!-- FILTER MENU -->
<div class="row">
    <div class="content-section-a">
        <div id="filter" class="container">
            <p style="padding-right: 30px;" class=" textleft margintop25">${translator.getString("choose_type_and_price")}</p>
            <div id="filter-container">
                <form name="filter_opt1">
                    <input id="filter_option_classic" type="hidden" name="filterOption" value="1">
                    <div style="margin-top:22px; padding-right: 10px" class="textleft">
                        <div id="filter_option" class=" btn btn-default">${translator.getString("classic")}</div>
                    </div>
                </form>
                <form name="filter_opt1">
                    <input id="filter_option_box" type="hidden" name="filterOption" value="2">
                    <div style="margin-top:22px; padding-right: 10px" class="textleft">
                        <div id="filter_option" class=" btn btn-default">${translator.getString("bouquet_in_the_box")}</div>
                    </div>
                </form>
                <form name="filter_opt1">
                    <input id="filter_option_box" type="hidden" name="filterOption" value="3">
                    <div style="margin-top:22px; padding-right: 10px" class="textleft">
                        <div id="filter_option" class=" btn btn-default">Букет в вазе</div>
                    </div>
                </form>
            </div>
            <p id="bqt-found" style="position: absolute; padding-top: 55px; font-size: 12pt;"></p>
            <div id="slider" class="slider"></div>
        </div>
    </div>
</div>

<!-- FILTER MENU END -->