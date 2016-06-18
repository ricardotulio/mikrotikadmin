$("#btn-excluir-plano").click(function() {
	var _this = $(this);
	
	bootbox.confirm("Tem certeza de que deseja excluir este plano?", function(result) {
		if(result)
			window.location.href = 'excluir/' + _this.attr('data-plano-id');
	});
});