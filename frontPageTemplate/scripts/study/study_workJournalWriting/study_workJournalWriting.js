var button = document.getElementsByClassName('main__writing-table-button'),
    arr = ["결석","지각","출석"],
    color = ["#fff6b1","#b8ff8f", "#ffb9b9"];

for (var i = 0; i < button.length; i++) {
    button[i].addEventListener('click', function(){
        if(button[i].innerText === arr[i]){
            button[i].innerText = arr[(i + 1) % 3];
            button[i].style.backgroundColor=color[(i + 1) % 3];
        }
    });
}