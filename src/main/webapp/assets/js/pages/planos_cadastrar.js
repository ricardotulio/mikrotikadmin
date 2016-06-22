$("#form-cadastrar-plano #btn-salvar").click(function(event) {
	event.preventDefault();
	
	if($("#form-cadastrar-plano").parsley().validate()) {
		$("#form-cadastrar-plano").submit();
	} 		
});

$("#form-editar-plano #btn-salvar").click(function(event) {
	event.preventDefault();
	
	if($("#form-editar-plano").parsley().validate()) {
		$("#form-editar-plano").submit();
	} 		
});	