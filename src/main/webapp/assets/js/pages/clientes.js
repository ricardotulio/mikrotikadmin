$("#btn-excluir-cliente").click(function() {
	var _this = $(this);
	
	bootbox.confirm("Tem certeza de que deseja excluir este cliente?", function(result) {
		if(result)
			window.location.href = 'excluir/' + _this.attr('data-cliente-id');
	});
});

$("#dataContrato").datepicker({language: "pt-BR"});