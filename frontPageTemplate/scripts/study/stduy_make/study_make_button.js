var nextButton = document.getElementById("nextButton")
    prevButton = document.getElementById("prevButton")
    submitButton=document.getElementById("submitButton"),
    currentIndex = 0;

nextButton.addEventListener('click', function(){
    currentIndex++;
    updateButton();
});
prevButton.addEventListener('click', function(){
    currentIndex--;
    updateButton();
});

function updateButton(){

    if(currentIndex == 2){
        nextButton.classList.add('disabled');
        submitButton.classList.remove('disabled');
        
    }
    else{
        nextButton.classList.remove('disabled');	
        submitButton.classList.add('disabled');
        
    }
}
