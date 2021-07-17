var button = document.getElementsByClassName('main__writing-table-button'),
    arr = ["결석","지각","출석"],
    color = ["#ffb9b9", "#fff6b1", "#b8ff8f"]

for (var i = 0; i < button.length; i++) {
    button[i].addEventListener('click', change(i));
}

function change(i){
    if(button[i].value === arr[0]){
        button[i].style.backgroundColor = color[1];
        button[i].value = arr[1];
    }
    else if(button[i].value === arr[1]){
        button[i].style.backgroundColor = color[2];
        button[i].value = arr[2];
    }
    else if(button[i].value === arr[2]){
        button[i].style.backgroundColor = color[0];
        button[i].value = arr[0];
    }
}
