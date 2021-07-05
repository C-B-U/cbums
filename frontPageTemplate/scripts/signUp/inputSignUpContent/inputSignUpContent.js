// 입력 비밀번호와 확인 비밀번호가 다를 경우 alert or 경고

var slides = document.querySelector('.boxList__infor'),
    slide = document.querySelectorAll('.boxList__infor li'),
    currentIdx = 0,
    slideCount = slide.length,
    slideMargin = 0,
    slideWidth = document.querySelector('.article__inner').clientWidth

    function moveSlide(num){ // current를 매개변수로 받을 예정
        document.querySelector('.boxList__infor').style.left = -num * slideWidth +'px';
        currentIdx = num;
    }

document.querySelector('.next').addEventListener('click', function(){
    if(currentIdx<slideCount-1){
        moveSlide(currentIdx + 1);
        console.log(currentIdx);
    }
    // document.querySelector('.next').addEventListener('click', function(){
    //     if(currentIdx == slideCount - 1){
    //         document.querySelector('.next').style.display = 'none';
    //     }
    // });
});

document.querySelector('.prev').addEventListener('click', function(){
    if(currentIdx>0){
        moveSlide(currentIdx - 1);
        console.log(currentIdx);
    }
    // document.querySelector('.prev').addEventListener('click', function(){
    //     if(currentIdx <= slideCount - 1){
    //         document.querySelector('.next').style.removeProperty("display");
    //     }
    // });
});

if(slideWidth<=380){
    document.querySelector('.')
}
