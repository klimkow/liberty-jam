<div class="container ">
    <div class="item-description-container">
    <div class="item-photo">
        <img src="${selectedItem.getLogo()}" height="400" width="400">
    </div>
    <div class="item-description">
        <div class="description-text">
            <h2>${selectedItem.getName()}</h2>
            <p>${selectedItem.getDescription()}</p>
        </div>
        <div class="item-price">
            <div style="font-family: Attentica4F; font-size: 25pt; font-weight:bold; margin-left: 10px; margin-top:15px; padding-right: 15px" class="textleft">
                <p class="textleft">${selectedItem.getPrice()?string?replace(",",".")}.000</p>
            </div>
            <div style="margin-top:20px; padding-right: 10px" class="textleft">
                <div id="add-to-cart" class="btn btn-default <#if order??><#if order.isSelected(selectedItem)>btn-item-in-cart</#if></#if>" >
                <#if order??&&order.isSelected(selectedItem)>
                    <img style="margin-right: 2px" src="img/ok_symb2.png" width="13" height="13"/>${translator.getString("item_int_the_cart")}
                <#else>${translator.getString("add_to_cart")}
                </#if></div>
            </div>
        </div>
    </div>
    </div>
</div>