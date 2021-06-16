const slideList = document.querySelector('.main__slide-list');
const slideContents = document.querySelectorAll('.main__study-slide');
const slideBtnNext = document.querySelector('.main__study-buttonB');
const slideBtnPrev = document.querySelector('.main__study-buttonA'); 
const slideLen = slideContents.length; 
const slideWidth = 130;
const slideSpeed = 300; 
const startNum = 300;
const tablet = window.matchMedia("all and (min-width:768px) and (max-width:1023px)");

slideList.style.width = slideWidth * (slideLen) + "px";
let curIndex = 0; 



slideBtnNext.addEventListener('click', function() {
    if (curIndex < slideLen - 4) { //스터디 개수 증가하면 숫자를 더 낮은 수로 변경
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * (curIndex + 1)) + "px, 0px, 0px)";
        curSlide = slideContents[++curIndex];
    } 
    
    
});


slideBtnPrev.addEventListener('click', function() {
    curSlide = slideContents[--curIndex];
    if (curIndex >= 0) {
        slideList.style.transition = slideSpeed + "ms";
        slideList.style.transform = "translate3d(-" + (slideWidth * curIndex) + "px, 0px, 0px)";
        
    }
});





