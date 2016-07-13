window.Parsley.addAsyncValidator('titulo-plano-duplicado', function(xhr) {
	return !xhr.responseJSON.success;
});