(document).getElementById('add-new-tag').onclick = function () {
    let text = document.getElementById("tag").value;
    let template =`
                <span class="badge bg-primary"> #${text}</span>
                <input type="hidden" name="tags" value="${text}" />
                `;

    let container = document.getElementById('tags-row');
    let div = document.createElement('div');
    div.className="col-auto"
    div.innerHTML = template;
    container.appendChild(div);
}