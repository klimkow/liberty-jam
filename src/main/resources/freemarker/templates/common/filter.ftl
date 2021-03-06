<script>
    $(document).ready(function(){
        $("#filter-container").delegate('div[id^=filter_option]','click', function(e) {
            $("div[id^= 'filter_option']").removeClass("button-selected");
            $(this).addClass("button-selected");
            var boxSizeDiv = $("#box-size");
            if ($(this).attr('id') == "filter_option2") {
                // if filtering by box display size option
                boxSizeDiv.removeClass("box-size-invisible");
                boxSizeDiv.addClass("box-size-visible");
            } else {
                boxSizeDiv.addClass("box-size-invisible");
                boxSizeDiv.removeClass("box-size-visible");
            }
            $('#marketing-active-zone').append('<div id="top-layer"><img src="img/loading.gif"  height="43" width="43"  /> </div>');
            var form = $(this).closest('form');
            var formData = form.serialize();
            filter(formData);
        });

        $("#box-size").delegate('span[id^=size]','click', function(e) {
            $("span[id^=size]").removeClass("size-selected");
            $(this).addClass("size-selected");
            var size = $(this).html();
            $('#filter_option_box_size').val(size);
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
                    <div id="filter-opt-block" class="textleft">
                        <div id="filter_option1" class=" btn btn-default filter_option">${translator.getString("classic")}</div>
                    </div>
                    <div id="separator"></div>
                </form>
                <form name="filter_opt1">
                    <input id="filter_option_box" type="hidden" name="filterOption" value="3">
                    <div id="filter-opt-block" class="textleft">
                        <div id="filter_option3" class=" btn btn-default filter_option">${translator.getString("bouquet_in_the_glass")}</div>
                    </div>
                    <div id="separator"></div>
                </form>
                <form name="filter_opt1">
                    <input id="filter_option_box" type="hidden" name="filterOption" value="2">
                    <input id="filter_option_box_size" type="hidden" name="boxSize">
                    <div id="filter-opt-block" class="textleft">
                        <div id="filter_option2" class=" btn btn-default filter_option">${translator.getString("bouquet_in_the_box")}</div>
                    </div>
                    <div id="box-size" class="box-size-invisible">
                        <span id="size-m">M</span>
                        <span id="size-l">L</span>
                    </div>
                </form>
            </div>
            <p id="bqt-found" style="position: absolute; padding-top: 55px; font-size: 12pt;"></p>
            <div class="slider-container">
                <div id="slider" class="slider"></div>

                <div class="slider-values">
                    <p style="float: right; padding-left: 6px;">${translator.getString("rub")}</p>
                    <p style="float: right; " id="sl_value_to"></p>
                    <p style="float: right; "> - </p>
                    <p style="float: right; " id="sl_value_from"></p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- FILTER MENU END -->