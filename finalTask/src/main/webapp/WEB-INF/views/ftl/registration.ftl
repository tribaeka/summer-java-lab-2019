<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as a>

<@c.page "Registration">
    ${message?ifExists}
    <@a.auth "/registration" true />
</@c.page>