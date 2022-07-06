//скроллинг главная страница
jQuery(document).ready(function(){
    $(".header-tabs a").mPageScroll2id({
        offset: 70
    });
});


//фотогалерея главная страница
var owl = $('.owl-carousel');
owl.owlCarousel({
    items:1,
    loop:true,
    dots: true,
    dotsEach: true
});
$('.play').on('click',function(){
    owl.trigger('play.owl.autoplay',[1000])
})
$('.stop').on('click',function(){
    owl.trigger('stop.owl.autoplay')
})

//табы
$(function() {
	var tab = $('#tabs .tabs-items > div'); 
	tab.hide().filter(':first').show(); 
	
	$('#tabs .tabs-nav a').click(function(){
		tab.hide(); 
		tab.filter(this.hash).show(); 
		$('#tabs .tabs-nav a').removeClass('active');
		$(this).addClass('active');
		return false;
	}).filter(':first').click();

});


$(document).ready(function(){
    $(".accordion-tab-content").hide();
    $(".accordion-tab-title").on('click', function(){
        $(this).next().slideToggle('slow');
        $(this).parent().toggleClass('active');
        $(".accordion-tab-title").not(this).next().slideUp('slow');
        $(".accordion-tab-title").not(this).parent().removeClass('active');
    });
});


var slideIndex = 1;
show_slides(slideIndex);
function plus_slides(n) {
  show_slides(slideIndex += n);
}
function current_slide(n) {
  show_slides(slideIndex = n);
}
function show_slides(n) {
  var i;
  var slides = document.getElementsByClassName("main-slideshow-photo");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}

var _slideIndex = 1;
_show_slides(_slideIndex);
function _plus_slides(n) {
    _show_slides(_slideIndex += n);
}
function _current_slide(n) {
    _show_slides(_slideIndex = n);
}
function _show_slides(n) {
  var i;
  var slides = document.getElementsByClassName("reviews-slideshow-photo");
  var dots = document.getElementsByClassName("reviews-dot");
  if (n > slides.length) {_slideIndex = 1}
  if (n < 1) {_slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[_slideIndex-1].style.display = "block";
  dots[_slideIndex-1].className += " active";
}
