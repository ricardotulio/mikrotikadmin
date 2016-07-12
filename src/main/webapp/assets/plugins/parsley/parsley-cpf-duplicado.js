window.Parsley.addAsyncValidator('cpf-duplicado', function(xhr) {
	return !xhr.responseJSON.success;
});