<#import "parts/common.ftl" as c>

<@c.page "Users">
    <h2>List of users</h2>
    <table>
        <#list users as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.imagePath}</td>
            </tr>
        </#list>
    </table>
</@c.page>