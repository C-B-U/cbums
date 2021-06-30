var input = document.getElementById("main__slide-tag-input"),
    button = document.querySelector(".main__slide-tag-button"),
    list = document.querySelector(".main__slide-tag-list");


button.addEventListener("click",function (event){
    event.preventDefault();
    const currentValue = input.value;
    addList(currentValue);
    list.value="";
});


function addList(text){
    var temp = document.createElement("li");
    temp.classList.add("main__slide-tag-list-item");
    temp.innerHTML = text;
    list.appendChild(temp);
}

list.addEventListener('click', function(event){
    const li = event.target;
    list.removeChild(li);
});


