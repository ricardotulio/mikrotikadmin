package br.com.ricardotulio.mikrotikadmin.model;

public enum UF {

	AC("ac"), AL("al"), AP("ap"), AM("am"), BA("ba"), CE("ce"), DF("df"), ES("es"), GO("go"), MA("ma"), MT("mt"), MS(
			"ms"), MG("mg"), PA("pa"), PB("pb"), PR("pr"), PE("pe"), PI(
					"pi"), RJ("rj"), RN("rn"), RS("rs"), RO("ro"), RR("rr"), SC("sc"), SP("sp"), SE("se"), TO("to");

	private String valor;

	UF(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return this.valor;
	}

}
