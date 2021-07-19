let attend = document.getElementsByClassName('main__writing-table-attend'),
    color = ["#ffb9b9", "#fff6b1", "#b8ff8f"];

function switchValue(element, stu) {
    if(element.innerText=='결석') {
        element.innerText = '지각';
        element.style.backgroundColor = color[1];
        attend.item(stu).value = '1';

    }else if(element.innerText=='지각') {
        element.innerText = '출석'
        element.style.backgroundColor = color[2];
        attend.item(stu).value = '2';
    }else {
        element.innerText = '결석'
        element.style.backgroundColor = color[0];
        attend.item(stu).value = '0';
    }
}