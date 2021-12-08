<#import "parts/common.ftlh" as c>

<@c.page>
<body class="container">
<h1>Convert Markdown to HTML</h1>
<form action="/view" method="post" enctype="multipart/form-data">
    <fieldset>
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
                <div class="card files" style="width: 18rem;" >
                    <img class="card-img-top" id="thumbnail" alt="logoPreview" />
                    <label>Upload Your File </label>
                    <input type="file" class="card-body" id="fileImage" name="previewFile">
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label for="text" class="form-label">Review text</label>
            <textarea class="form-control" id="text" name="text" rows="10">${review.getText()?if_exists}</textarea>
        </div>

        <div class="col-auto">
            <div class="card files" style="width: 18rem;" >
                <img class="card-img-top" id="thumbnail_f1" alt="logoPreview" />
                <label>Upload Your File </label>
                <input type="file" class="card-body" id="f1" name="images[]">
            </div>
            <div class="card files" style="width: 18rem;" >
                <img class="card-img-top" id="thumbnail_f2" alt="logoPreview" />
                <label>Upload Your File </label>
                <input type="file" class="card-body" id="f2" name="images[]">
            </div>
            <div class="card files" style="width: 18rem;" >
                <img class="card-img-top" id="thumbnail_f3" alt="logoPreview" />
                <label>Upload Your File </label>
                <input type="file" class="card-body" id="f3" name="images[]">
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
    </fieldset>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#fileImage').change(function () {
                showImageThumbnail(this);
            });
            $('#f1').change(function () {
                showImageThumbnail1(this);
            });
            $('#f2').change(function () {
                showImageThumbnail2(this);
            });
            $('#f3').change(function () {
                showImageThumbnail3(this);
            });

            $("input[type='radio']").click(function(){
                var sim = $("input[type='radio']:checked").val();
                //alert(sim);
                if (sim<3) {
                    $('.myratings').css('color','red');
                    $(".myratings").text(sim);
                } else{
                    $('.myratings').css('color','green');
                    $(".myratings").text(sim);
                }
            });
        });

        function showImageThumbnail(fileInput) {
            let elementId = this.name;
            file = fileInput.files[0];
            reader = new FileReader();

            reader.onload = function (e) {
                $('#thumbnail').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
        }
        function showImageThumbnail1(fileInput) {
            let elementId = this.name;
            file = fileInput.files[0];
            reader = new FileReader();

            reader.onload = function (e) {
                $('#thumbnail_f1').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
        }
        function showImageThumbnail2(fileInput) {
            let elementId = this.name;
            file = fileInput.files[0];
            reader = new FileReader();

            reader.onload = function (e) {
                $('#thumbnail_f2').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
        }
        function showImageThumbnail3(fileInput) {
            let elementId = this.name;
            file = fileInput.files[0];
            reader = new FileReader();

            reader.onload = function (e) {
                $('#thumbnail_f3').attr('src', e.target.result);
            };
            reader.readAsDataURL(file);
        }

        let i = 0;
        (document).getElementById('add-new-tag').onclick = function () {
            let text = document.getElementById("tag").value;
            let template =`
                <span class="badge bg-primary"> #`+text+`</span>
                <input type="hidden" name="tags[]" value="`+text+`" />
                `;

            let container = document.getElementById('tags-row');
            let div = document.createElement('div');
            div.className="col-auto"
            div.innerHTML = template;
            container.appendChild(div);
            i++;
        }
    </script>
</form>

</body>
</@c.page>