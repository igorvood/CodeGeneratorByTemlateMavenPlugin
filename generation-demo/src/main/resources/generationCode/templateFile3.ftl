<#assign conn = default_connection()/>
<#assign par = template_param()/>
<#assign util = util_methods()/>
<#assign multiMaps = par['multiMaps']/>
<#assign map = par['map']/>
<#assign multiList = par['multiList']/>


<#function get_tables>
    <#local sql>
        with enums as (
        select aat.owner,
        aat.table_name,
        acc.constraint_name,
        listagg(acc.COLUMN_name) within group ( order by 1) col
        from ALL_ALL_TABLES aat
        join ALL_CONSTRAINTS ac
        on (ac.owner, ac.table_name, ac.constraint_type) = ((aat.owner, aat.table_name, 'P'))
        join ALL_CONS_COLUMNS acc
        on (acc.owner, acc.table_name, acc.constraint_name) = ((ac.owner, ac.table_name, ac.constraint_name))
        where aat.table_name like 'META_E%'
        --       and aat.owner = 'JP'
        group by aat.owner, aat.table_name, acc.constraint_name
        having count(1) = 1
        )
        select e.table_name,
        'select listagg('||e.col||', '',''||chr(10)) within group ( order by id) val from '||e.owner||'.'||e.table_name sel
        from enums e
    </#local>
    <#local tab = conn.query(sql)/>
    <#return tab/>
</#function>

<#function get_ids sel>
    <#local i = conn.query(sel)/>
    <#return i/>
</#function>

<#assign tables = get_tables()/>toCamelCaseFirstLetterLower
<#macro generateClass tab>
    <#list tab as t>
        package com.example.demo.generate

        //      ${util.toCamelCaseFirstLetterLower(t.TABLE_NAME)}

        enum class ${util.toCamelCase(t.TABLE_NAME)} {
        <#list get_ids(t.SEL) as iid>
            ${iid.VAL}
        </#list>
        }

        ===================separator class======================

    </#list>
</#macro>

<@generateClass tables/>








<#flush>