package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ApresentacaoTO implements Serializable {
	private long id;
	private TrabalhoTO trabalho;
	private PreletorTO preletor;
	private int horarioInicio;
	private int duracao;

	public ApresentacaoTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TrabalhoTO getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(TrabalhoTO trabalho) {
		this.trabalho = trabalho;
	}

	public PreletorTO getPreletor() {
		return preletor;
	}

	public void setPreletor(PreletorTO preletor) {
		this.preletor = preletor;
	}

	public int getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(int horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
}