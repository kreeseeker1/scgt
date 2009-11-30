package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StandTO implements Serializable {
	private long id;
	private int numero;
	private int metragem;

	public StandTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getMetragem() {
		return metragem;
	}

	public void setMetragem(int metragem) {
		this.metragem = metragem;
	}
}