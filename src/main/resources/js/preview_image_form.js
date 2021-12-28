$(document).ready(function () {
    $('#fileImage').change(function () {
        showImageThumbnail(this);
    });

    function showImageThumbnail(fileInput) {
        let file = fileInput.files[0];
        let reader = new FileReader();

        reader.onload = function (e) {
            let img = document.createElement('img')
            img.src = e.target.result;
            img.className = "card-img-top";
            img.id = "previewImage";
            let element = document.getElementById("previewImage");
            if (element == null) {
                document.getElementById('thumbnail').prepend(img);
            } else {
                element.src = e.target.result;
            }
            // $('#thumbnail').attr('src', e.target.result);
        };
        reader.readAsDataURL(file);
    }
});