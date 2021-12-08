<div class="row row-cols-auto">
    <#list reviews as review>
<#--        <div class="card border-success mb-3 me-3">-->
        <div class="card me-3">
            <#if review.getPreviewImage()??>
                <img src="${review.getPreviewImage()}" class="img-thumbnail" width="300" height="255">
            <#else>
                <img src="/img/not_found.jpg" class="card-img-top" width="150" height="255">
            </#if>
            <div class="card-body" style="padding: 0">
                <h5 class="card-title ms-3">${review.tag?if_exists}</h5>
                <div class = "card-footer text-muted">
                    <a href="/reviews/${review.id}")>${review.title}</a>
                    <p class="card-text ms-3">${review.getPreview_text()}</p>
                    <a href="/user-reviews/${review.author.id}")>${review.authorName}</a>
                </div>
            </div>
        </div>
    </#list>
</div>