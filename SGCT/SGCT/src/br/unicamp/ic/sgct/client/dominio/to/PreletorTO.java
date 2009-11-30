package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PreletorTO implements Serializable {
	private long id;
	private String instituicao;
	private TrabalhoTO trabalho;

	public PreletorTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public TrabalhoTO getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(TrabalhoTO trabalho) {
		this.trabalho = trabalho;
	}
}