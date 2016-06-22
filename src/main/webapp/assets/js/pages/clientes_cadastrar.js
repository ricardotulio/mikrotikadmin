if ($("#dataContrato").attr("readonly") == undefined) {
	$("#dataContrato").datepicker({
		language : "pt-BR",
		autoclose : true
	});
}

$("#form-cadastrar-cliente #btn-salvar").click(function(event) {
	event.preventDefault();
	
	if($("#form-cadastrar-cliente").parsley().validate()) {
		$("#form-cadastrar-cliente").submit();
	} 		
});

$("#form-editar-cliente #btn-salvar").click(function(event) {
	event.preventDefault();
	
	if($("#form-editar-cliente").parsley().validate()) {
		$("#form-editar-cliente").submit();
	} 		
});