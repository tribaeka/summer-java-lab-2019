<#import "parts/common.ftl" as c>

<@c.page "Chapter">
    <div class="chapter-content p-5">
        <h2 class="mb-5">${chapter.title}</h2>
        <#list chapter.contentToParagraphs() as paragraph>
            <p>${paragraph}</p>
        </#list>
    </div>
</@c.page>