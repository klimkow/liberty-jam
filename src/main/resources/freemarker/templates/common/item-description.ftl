<div class="container ">
    <div class="item-description-container">
    <div class="item-photo vertical-center">
        <img src="${selectedItem.getLogo()}" height="400" width="400">
    </div>
    <div class="item-description vertical-center">
        <div class="description-text">
            <h2>${selectedItem.getName()}</h2>
            <p>${selectedItem.getDescription()}</p>
        </div>
        <div class="item-price">
            <div style="font-family: Attentica4F; font-size: 25pt; font-weight:bold; margin-left: 10px; margin-top:15px; padding-right: 15px" class="textleft">
                <p class="textleft">${selectedItem.getPrice()?string?replace(",",".")}.000</p>
            </div>
            <div style="margin-top:20px; padding-right: 10px" class="textleft">
                <p class="textleft"><a class="btn btn-default" href="#">Добавить в корзину</a></p>
            </div>
        </div>
    </div>
    </div>
</div>