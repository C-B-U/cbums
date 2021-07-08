// clicked가 있음 -> 이미 열려있는 애
// clicked가 없음 -> 닫혀있는 애
document.querySelector('.openedStudy__list--btn').addEventListener('click', function () {
    if (document.querySelector('.openedStudy__list--btn').classList.contains('clicked')) {
        // clicked가 있으면 == 즉 닫으려고 버튼을 누른거면
        document.querySelector('.openedStudy__list--btn').classList.remove('clicked');
        // clicked를 지우고 == 토글 버튼을 원상복귀 시키고
        document.querySelector('.openedStudy__table').style.display = 'none';
        // block 클래스를 지워라 == 표가 안보이게 해라
    }
    else {
        // 그게 아니고 만약 clicked가 없으면 == 이제 막 토글버튼을 누르는 거면
        document.querySelector('.openedStudy__list--btn').classList.add('clicked');
        // clicked를 만들어서 토글 버튼을 180도 돌리고
        document.querySelector('.openedStudy__table').style.display = 'block';
        // 표가 보이도록 해라
    }
});

// 함수 만들어서 개수별 토글 버튼 구현하기