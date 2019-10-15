<#import "parts/common.ftl" as c>

<@c.page "Book"><!-- need throw books title on page title -->
    <div class="row my-2 py-4 border border-secondary rounded">
        <div class="col-2">
            <img src="${book.imagePath}" alt="" width="205" height="310">
        </div>
        <div class="col-8">
            <div class="ml-5">
                <h2 class="my-2">${book.title}</h2>
                <h5 class="my-2">Author: <small>${book.author}</small></h5>
                <h5 class="my-2">Latest: <small><a href="#" class="chapter-link">${book.getLastChapter().title}</a></small></h5>
                <#list book.genres as genre>
                    <button type="button" class="btn btn-outline-success btn-sm">${genre.title}</button>
                </#list>
                <br>
                <#if user??>
                    <#if isFollowed>
                        <form action="/user/unfollow" method="post">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-outline-danger my-2">Unfollow</button>
                        </form>
                    <#else>
                        <form action="/user/follow" method="post">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button type="submit" class="btn btn-outline-danger my-2">Follow</button>
                        </form>
                    </#if>
                </#if>
            </div>

        </div>
    </div>
    <div class="row my-2 py-4 border border-secondary rounded">
        <#list book.chapters as chapter>
            <div class="col-3 py-2">
                <div class="card">
                    <a href="/chapter/${book.titleToUrl()}/${chapter.titleToUrl()}" class="btn btn-outline-secondary">
                        <div class="card-body">
                            <h5 class="card-title">${chapter.title}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${chapter.getFormatedUploadDate()}</h6>
                        </div>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</@c.page>