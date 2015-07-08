var definirMascaras = function(){
	$("#data").mask("99/99/9999");
	$("#horario").mask("99:99");
};

var controlaValorFrequencia = function() {
	habilitarFrequencia();
};

var habilitarFrequencia = function() {
	var frequencia = $("#frequencia");

	if (frequencia.val() === "NENHUMA") {
		$("#valorFrequencia").val("0").prop("disabled", true);
	} else {
		$("#valorFrequencia").prop("disabled", false);
	}
};

var removerItem = function() {
	$(this).closest("tr").fadeOut(function(){
		this.remove();
	});
};

var inicializa = function() {
	definirMascaras();
	habilitarFrequencia();

	$("#frequencia").change(controlaValorFrequencia);
	$(".btn-remover").click(removerItem);
};

$(inicializa);