//скроллинг главная страница
jQuery(document).ready(function(){
    $(".header-tabs a").mPageScroll2id({
        offset: 71
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