let i = 3;
(document).getElementById('add-new-tag').onclick = function () {
    let text = document.getElementById("tag").value;
    let template = `
                <div class="col-auto">
            <span class="badge bg-primary" name="tags[${i}]">fsdf</span>
        </div>`;

    let container = document.getElementById('tag-container');
    let div = document.createElement('div');
    div.innerHTML = template;
    container.appendChild(div);
    i++;
}

$(document).ready(function () {
    $('#fileImage').change(function () {
        showImageThumbnail(this);
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
    file = fileInput.files[0];
    reader = new FileReader();

    reader.onload = function (e) {
        $('#thumbnail').attr('src', e.target.result);
    };

    reader.readAsDataURL(file);
}

