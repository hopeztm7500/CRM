(function($){
	var Controller = window.Controller;
	
	var controller = new Controller();
	controller.bindMyPageHander = function(){
		$('#uploadform [type=file]').on('change', $.proxy(this.fileChanged, this));
	};
	
	controller.fileChanged = function(e){
		if(e.target.files.length > 0){
			$('#uploadform [name=name]').val(e.target.files[0].name);	
		}
	};
	controller.initialize();
	
})(jQuery);