window.Parsley.addAsyncValidator('login-duplicado', function(xhr) {
	return !xhr.responseJSON.success;
});