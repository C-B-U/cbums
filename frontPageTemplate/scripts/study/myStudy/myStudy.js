document.querySelector('.openedStudy__list--btn').addEventListener('click', function () {
    if (document.querySelector('.openedStudy__list--btn').classList.contains('clicked')) {
        document.querySelector('.openedStudy__list--btn').classList.remove('clicked');
        document.querySelector('.openedStudy__table').style.display = 'none';
    }
    else {
        document.querySelector('.openedStudy__list--btn').classList.add('clicked');
        document.querySelector('.openedStudy__table').style.display = 'block';
    }
});

// 함수 만들어서 개수별 토글 버튼 구현하기
