$(".btn-cancelar-fatura").click(
		function() {
			var id = $(this).data("id");
		
			bootbox.confirm(
					"Tem certeza que deseja alterar o status da fatura?",
					function(result) {
						if (result)
							window.location.href = id + "/cancelar";
					});
		});

$(".btn-pagar-fatura").click(
		function() {
			var id = $(this).data("id");
		
			bootbox.confirm(
					"Tem certeza que deseja marcar esta fatura como paga em <strong>DINHEIRO</strong>?",
					function(result) {
						if (result)
							window.location.href = id + "/pagar";
					});
		});

