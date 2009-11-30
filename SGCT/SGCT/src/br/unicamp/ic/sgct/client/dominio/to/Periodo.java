package br.unicamp.ic.sgct.client.dominio.to;

public enum Periodo {
	MATUTINO(1, "Matutino"),
	VESPERTINO(2, "Vespertino"),
	NOTURNO(3, "Noturno");

	private int sequencial;

	private String descricao;

	private Periodo(int sequencial, String descricao) {
		this.sequencial = sequencial;
		this.descricao = descricao;
	}

	public int getSequencial() {
		return sequencial;
	}

	public void setSequencial(int sequencial) {
		this.sequencial = sequencial;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}