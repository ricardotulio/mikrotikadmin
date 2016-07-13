window.ParsleyValidator.addValidator("cep", function(val, req) {
	var regExp = new RegExp("^\\d{5}-\\d{3}$");
	return regExp.test(val);
}, 32);