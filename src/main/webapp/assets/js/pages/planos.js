$(".btn-excluir-plano").click(function() {
	var _this = $(this);
	var id = _this.data("id");
	var titulo = _this.data("titulo");
	
	bootbox.confirm("Tem certeza de que deseja excluir o plano \"<strong>" + titulo + "\"</strong>?", function(result) {
		if(result)
			window.location.href = "excluir/" + id;
	});
});