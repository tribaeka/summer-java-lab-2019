<#import "parts/common.ftl" as c>
<#import "parts/bookList.ftl" as b>

<@c.page "Main">
    <div class="row">
        <div class="col-8">
            <div class="d-flex justify-content-between align-items-end">
                <h2>Latest Books</h2>
                <a href="/updates" class="text-danger" style="text-decoration: none;">
                    <h5>M O R E</h5>
                </a>
            </div>
        <hr style="border: 1px solid red;">
        <@b.bookList false/>
        </div>
    </div>
</@c.page>