package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TrabalhoTO implements Serializable {
	private long idTrabalho;
	private String titulo;
	private String resumo;
	private PreletorTO preletor;

	public TrabalhoTO() {
	}

	public long getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(long idTrabalho) {
		this.idTrabalho = idTrabalho;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public PreletorTO getPreletor() {
		return preletor;
	}

	public void setPreletor(PreletorTO preletor) {
		this.preletor = preletor;
	}
}