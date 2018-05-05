(function(){
//bind touch event
$('.c-slider').each(function(index, container){
	//bind touch event
	var element, moved, startX, startY, lastX, step;
	element = $(container).find('ul')[0]; //element to delegate
	moveX = 0;	//move x coordinate
	startX = 0; //starting x coordinate
	lastX = 0;	//last x coordinate
	
	moveY = 0;	//move y coordinate
	startY = 0; //starting y coordinate
	scrollH = 0;
	scrollL = 0;
	step = 2;	//the px amount to move
	vstep = 20;//vertical px move
	if (element.addEventListener) {
		//touchstart           
		element.addEventListener("touchstart", function(e) {
		    moved = false;
		    moveX = e.touches[0].clientX;
		    startX = e.touches[0].clientX;
		    lastX = e.touches[0].clientX;
		    moveY = e.touches[0].clientY;
		    startY = e.touches[0].clientY;
		    scrollH = $(document).scrollTop();
		    scrollL = $(document).scrollLeft();
		}, false);
		//touchmove
		element.addEventListener("touchmove", function(e) {
			e.preventDefault();
		    if (Math.abs(e.touches[0].clientY - moveY) > vstep){
		    	moveY = e.touches[0].clientY;
		    	scrollH = scrollH - moveY + startY;
		    	scroll(scrollL, scrollH);
		    	startY = e.touches[0].clientY;
		    	scrollH = $(document).scrollTop();
		    }
		    //only horizen move
			if (Math.abs(e.touches[0].clientX - moveX) > step){
				moveX = e.touches[0].clientX;
			}
			lastX = e.touches[0].clientX;
		}, false);
		//touchend
		element.addEventListener("touchend", function(e) {
			if(Math.abs(lastX - startX) < step) return false;
			if (lastX > startX){
				$(this).parents('.c-slider').trigger('prev');
			}else{
				$(this).parents('.c-slider').trigger('next');
			}
		}, false);
	}
});
})();