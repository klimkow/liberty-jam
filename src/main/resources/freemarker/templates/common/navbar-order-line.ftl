<#--TODO: REPLACE BY JSON ANSWER-->
${translator.getString("you_have")} ${order.getItems().size()}
<#if order.getItems().size() == 1>
${translator.getString("bouquet1")}
<#if order.getItems().size() > 1>
    <#if order.getItems().size() < 5>
    ${translator.getString("bouquet24")}
    <#else>
    ${translator.getString("bouquet5")}
    </#if>

</#if>
</#if>
${translator.getString("total_amount")} ${order.getAmount()}