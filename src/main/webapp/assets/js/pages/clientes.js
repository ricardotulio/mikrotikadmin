$(".btn-excluir-cliente").click(function() {
	var _this = $(this);
	var id = _this.data("id");
	var nome = _this.data("nome");
	
	bootbox.confirm("Tem certeza de que deseja excluir o cliente \"<strong>" + nome + "</strong>\"?", function(result) {
		if(result)
			window.location.href = "excluir/" + id;
	});
});