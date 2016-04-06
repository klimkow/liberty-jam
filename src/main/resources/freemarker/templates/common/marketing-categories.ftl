<script>
    function getBoxCategory()
    {
        // if filtering by box display size option
        var boxSizeDiv = $("#box-size");
        boxSizeDiv.removeClass("box-size-invisible");
        boxSizeDiv.addClass("box-size-visible");
        getCategoryItems(2);
    }

</script>

<div style="height: 367px;" class="container">
    <div class="width33 marketing-category" onclick="getCategoryItems(1)">
        <img class="img-circle" src="img/cat_flower.jpg" height="250" width="250">
        <h2>${translator.getString("classic")}</h2>
    </div>
    <div class="width33 marketing-category" onclick="getCategoryItems(3)">
        <img class="img-circle" src="img/cat_vase.jpg" height="250" width="250">
        <h2>${translator.getString("bouquet_in_the_glass")}</h2>
    </div>
    <div class="width33 marketing-category" onclick="getBoxCategory()">
        <img class="img-circle" src="img/cat_box.jpg" height="250" width="250">
        <h2>${translator.getString("bouquet_in_the_box")}</h2>
    </div>
</div>