function getItemPriceFromDiapasons(count) {
    <#if diapasons??>
        <#list diapasons as diapason>
            alert('${diapason.countFrom}' + '${diapason.countTo}');
            if (count >= ${diapason.countFrom} && count <= ${diapason.countTo}) {
                return ${diapason.price};
            }
        </#list>

    </#if>
    return ${defaultPrice};
}
