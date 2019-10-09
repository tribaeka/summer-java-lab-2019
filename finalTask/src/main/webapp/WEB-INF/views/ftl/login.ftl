<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as a>

<@c.page "Log In">
    <@a.auth "/login" false />
</@c.page>