window.ParsleyValidator.addValidator("telefone", function(val, req) {
	var regExp = /\([1-9][1,3,5,7,9]\) [9]?[0-9]{4}-[0-9]{4}/;
	return regExp.test(val);
}, 32);