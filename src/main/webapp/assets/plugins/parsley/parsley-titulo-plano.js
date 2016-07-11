window.Parsley.addAsyncValidator('titulo-plano', function(xhr) {
	return !xhr.responseJSON.success;
});