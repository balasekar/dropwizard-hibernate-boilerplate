<#-- @ftlvariable name="" type="com.product.app.views.PersonView" -->
<#import "Layout.ftl" as layout>
<@layout.layout>
<h1>Users</h1>
<div>
    <ul>
        <#list personList as person>
            <li>${person.name?html}</li>
        </#list>
    </ul>
</div>
</@layout.layout>