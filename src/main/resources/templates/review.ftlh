<#import "parts/common.ftlh" as c>

<@c.page>
    <body class="container">
    <form action="review" method="get" enctype="multipart/form-data">
        <div class="form-group col-md-6" >
            <div class="mb-3">
                <h1>${review.getTitle()}</h1>
                <input type="hidden" name="title" value="${review.getTitle()}" />
            </div>

            <div class="row g-2 align-items-start">
                <div class="col-auto">
                    <p1 name="preview_text">${review.getPreview_text()}</p1>
                    <input type="hidden" name="preview_text" value="${review.getPreview_text()}" />
                </div>
                <#if review.getPreviewImage()??>
                    <div class="col-auto">
                        <div class="card files" style="width: 18rem;" >
                            <img class="card-body" id="thumbnail" src="${review.getPreviewImage()}" />
                            <input type="hidden" id="previewImage" name="previewImage" value="${review.getPreviewImage()}">
                        </div>
                    </div>
                </#if>
            </div>

            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="https://res.cloudinary.com/freshcucumbers/image/upload/v1638799261/SRWRestImageBase/not_found_tu4rnb.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="https://res.cloudinary.com/freshcucumbers/image/upload/v1638799261/SRWRestImageBase/not_found_tu4rnb.jpg" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="https://res.cloudinary.com/freshcucumbers/image/upload/v1638799261/SRWRestImageBase/not_found_tu4rnb.jpg" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>

            <div class="mb-3">
                <p1 name="authorName">${review.getAuthorName()}</p1>
                <input type="hidden" name="authorName" value="${review.getAuthorName()}" />
            </div>
            <div class="mb-3">
                <div name="text">${htmlContent ?no_esc}</div>
                <input type="hidden" name="text" value="${review.getText()}" />
            </div>
            <div class="container d-flex justify-content-center mt-10">
                <div class="row">
                    <div class="col-md-6">
                        <div class="card">
                            <div class="card-body text-center"> <span class="myratings" name="authorsRate"><#if review.getAuthorsRate()??>${review.getAuthorsRate()}<#else>5</#if></span>
                                <h4 class="mt-1">Rate</h4>
                                <fieldset class="rating">
                                    <#list 10..1 as x>
                                        <input type="radio" id="star${x}" name="authorsRate" value=${x} ${(review.getAuthorsRate()?? && review.getAuthorsRate() == x)?then("checked", "disabled")}/>
                                        <label class="full" for="star${x}"></label>
                                    </#list>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
<#--        <#if user.username == review.getAuthorName()>-->
<#--            <button type="submit" class="btn btn-primary" name="button" value="edit">Edit</button>-->
<#--        </#if>-->
    </form>

    <script type="text/javascript">
        $(document).ready(function () {
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
    </script>
    </body>
</@c.page>