$(function(){
	$('.mod-tab-menu ul').on('click','li',function(){
		var now=$(this);
		var index=now.parent('ul').index();
		var activeRow;
		activeRow=$('.mod-tab-menu ul').eq(index);
		activeRow.find('li.active').removeClass('active');
		now.addClass('active');

		//评论和笔记切换
		//console.log("index:",activeRow.find('li.active').index());
		
		switch(activeRow.find('li.active').index()){
			case 0:{
				$("#note").prop("hidden",true);
				$("#comment").prop("hidden",false);
			};break;
			case 1:{
				$("#comment").prop("hidden",true);
				$("#note").prop("hidden",false);
			};break;
			default:;
		}
	});

	$("#follow").on('click',function(){
		//console.log("value",$(this).val());
		if($(this).val()==0){
			$(this).val(1);
			$(this).text("取消关注");
			$(this).removeClass('btn-primary');
			$(this).addClass('btn-default');
		}else{
			$(this).val(0);
			$(this).text("关注");
			$(this).removeClass('btn-default');
			$(this).addClass('btn-primary');
		}
	});
});