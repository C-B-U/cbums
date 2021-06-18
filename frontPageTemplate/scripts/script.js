const swiper = new Swiper('.swiper-container', {
    // Optional parameters
    direction: 'horizontal',
    loop: false,
    slidesPerView: 3,
    slidesPerGroup: 3,
    observer: true,
    observeParents: true,
    spaceBetween: 24,
    navigation: {
        nextEl: '.swiper-button-next',
        prevEl: '.swiper-button-prev',
    },
    breakpoints: {
        1280: {
            slidesPerView: 5.5,
            slidesPerGroup:5.5,
        },
        600: {
            slidesPerView: 4,
            slidesPerGroup: 4,
        }

    }
  });


function openNav() {
    document.getElementById('mysidenav').style.width = '250px';
}
function closeNav() {
    document.getElementById('mysidenav').style.width = '0';
}




