<#import "parts/common.ftl" as c>
<#import "parts/auth.ftl" as a>
<#import "parts/bookList.ftl" as bl>

<@c.page "Profile">
    <div class="row py-4 border border-secondary rounded">
        <div class="col-2">
            <img src="${user.imagePath}" alt="" width="125" height="125">

            <button type="button" data-toggle="modal" data-target="#uploadPhoto"
               class="btn btn-secondary btn-sm d-block w-75 ml-1 mt-1">Add photo</button>

            <div class="modal fade" id="uploadPhoto" tabindex="-1" role="dialog" aria-labelledby="uploadPhotoLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Update photo</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <form action="/user/changePhoto" method="post" enctype="multipart/form-data">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label for="file">Select file</label>
                                    <input type="file" class="form-control" name="file" placeholder="Select file">
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary">Upload</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-2">
            <div class="d-flex justify-content-end"><@a.logout/></div>

        </div>
    </div>
    <@bl.smallBookList/>
</@c.page>