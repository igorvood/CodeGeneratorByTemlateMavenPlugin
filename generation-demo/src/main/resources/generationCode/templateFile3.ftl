<#--/*-->
<#--
<#--
<#list multiMaps as l>
    ${l.key} => <#list l.val as l1> ${l1.key} ->${l1.val}|  </#list>
</#list>
-->
<#--<#list map as l>-->
<#--    ${l.key} = ${l.val}-->
<#--</#list>-->
<#--======================= multiList=============================-->
<#macro generateClass multiList>
    <#list multiList as l>
        package com.example.demo.generate

        enum class ${l.key} {
        <#list l.val as l1> ${l1}, </#list>
        }
        ===================separator class======================
    </#list>
</#macro>

<@generateClass multiList/>
<#flush>