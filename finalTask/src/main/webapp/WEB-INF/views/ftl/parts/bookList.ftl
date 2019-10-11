<#macro bookList isDoubleCol>
<div class="row">
    <#list books as book>
        <#if isDoubleCol>
            <div class="col-6">
            <div class="card my-1" style="width: 32rem;">
        <#else>
             <div class="col-12">
             <div class="card my-1" style="width: 45rem;">
        </#if>
                <div class="row">
                    <div class="col-2">
                        <img src="${book.imagePath}" class="card-img-left" alt="..." width="80" height="120">
                    </div>
                    <div class="col-10">
                        <div class="card-body">
                            <div class="d-flex justify-content-between">
                                <h5 class="card-title">${book.title}</h5>
                                <p class="text-secondary">
                                    <small>${book.getLastChapter().getTimeAfterUpdating()}</small>
                                </p>
                            </div>
                            <p class="card-text">
                                <#list book.genres as genre>
                                    <button type="button" class="btn btn-outline-success btn-sm">${genre.title}</button>
                                </#list>
                            <h6>${book.getLastChapter().getTitle()}</h6>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </#list>
</div>
</#macro>