if ($("#dataContrato").attr("readonly") == undefined) {
	$("#dataContrato").datepicker({
		language : "pt-BR",
		autoclose : true
	});
}

$('#form-cliente').parsley().on('field:validated', function() {
	var ok = $('.parsley-error').length === 0;
	$('.bs-callout-info').toggleClass('hidden', !ok);
	$('.bs-callout-warning').toggleClass('hidden', ok);
}).on('form:submit', function() {
	return true; // Don't submit form for this demo
});

// $("#form-cadastrar-cliente #btn-salvar").click(function(event) {
// event.preventDefault();
//	
// if($("#form-cadastrar-cliente").parsley().validate()) {
// $("#form-cadastrar-cliente").submit();
// }
// });

$("#form-editar-cliente #btn-salvar").click(function(event) {
	event.preventDefault();

	if ($("#form-editar-cliente").parsley().validate()) {
		$("#form-editar-cliente").submit();
	}
});

$("#cpf").inputmask("999.999.999-99");
$("#cep").inputmask("99999-999");
$("#telefone").inputmask("(99) 9999-9999");
$("#celular").inputmask("(99) [9]9999-9999");