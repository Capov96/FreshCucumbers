<#import "parts/common.ftlh" as c>
<head>
    <link rel="stylesheet" href="/static/images_drop_area.css">
    <link rel="stylesheet" href="/static/image_drop_area.css">
</head>
<@c.page>
<body class="container">
<h1 id="title">Convert Markdown to HTML</h1>
<form action="/view" method="post" enctype="multipart/form-data">
<#--    <fieldset>-->
    <div class="bd-content ps-lg-4">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="${review.getTitle()?if_exists}"></input>
        </div>

        <div class="mb-3" id="tag-container">
            <label for="title" class="form-label">Tags</label>
            <div class="row g-3">
                <div class="col-auto">
                    <input type="text" class="form-control" id="tag" name="tag">
                </div>
                <div class="col-auto" id="add-button">
                    <a href="javascript:;" id="add-new-tag" class="add-new-tag">Add</a>
                </div>
            </div>
            <div class="row g-3" id="tags-row">

            </div>
        </div>

        <div class="row g-2 align-items-start">
            <div class="col-8">
                <label for="preview_text" class="form-label">Preview text</label>
                <textarea class="form-control" id="preview_text" name="preview_text" rows="3">${review.getPreview_text()?if_exists}</textarea>
            </div>
            <div class="col-auto">
                <div class="card files" style="width: 18rem;" id="thumbnail" >
                    <label>Upload Your File </label>
                    <input type="file" class="card-body" id="fileImage" name="previewFile">
                </div>
            </div>
        </div>

        <div class="mb-3">
            <label for="text" class="form-label">Review text</label>
            <textarea class="form-control" id="text" name="text" rows="10">${review.getText()?if_exists}</textarea>
        </div>

        <div id="drop-area">
            <form class="my-form">
                <div id="gallery"></div>
                <p>Загрузите изображения с помощью диалога выбора файлов или перетащив нужные изображения в выделенную область</p>
                <input type="file" id="fileElem" multiple accept="image/*" onchange="handleFiles(this.files)">
                <label class="button" for="fileElem">Выбрать изображения</label>
            </form>
        </div>

        <div id="my-drop-area">
            <div id="gallery"></div>
            <p>Загрузите изображения с помощью диалога выбора файлов или перетащив нужные изображения в выделенную область</p>
            <div class="files" style="width: 50%; align-items: center" id="thumbnail" >
                <input type="file" class="card-body" id="fileImage" name="previewFile">
            </div>
        </div>

        <div class="container d-flex justify-content-center mt-10">
            <div class="row">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body text-center"> <span class="myratings" name="authorsRate"><#if review.getAuthorsRate()??>${review.getAuthorsRate()}<#else>5</#if></span>
                            <h4 class="mt-1">Rate</h4>
                            <fieldset class="rating">
                                <#list 10..1 as x>
                                    <input type="radio" id="star${x}" name="authorsRate" value=${x} ${(review.getAuthorsRate()?? && review.getAuthorsRate() == x)?then("checked", "")}/>
                                    <label class="full" for="star${x}"></label>
                                </#list>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Submit</button>
    </div>
<#--    </fieldset>-->
    <script>
            $("input[type='radio']").click(function(){
                var sim = $("input[type='radio']:checked").val();
                if (sim<3) {
                    $('.myratings').css('color','red');
                    $(".myratings").text(sim);
                } else{
                    $('.myratings').css('color','green');
                    $(".myratings").text(sim);
                }
            });
    </script>
    <script src="js/image_drop.js"></script>
    <script src="js/tag_form.js"></script>
    <script src="js/preview_image_form.js"></script>

</form>

</body>
</@c.page>