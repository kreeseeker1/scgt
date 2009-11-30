package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SalaTO implements Serializable {
	private long id;
	private String identificacao;
	private int capacidade;

	public SalaTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}
}