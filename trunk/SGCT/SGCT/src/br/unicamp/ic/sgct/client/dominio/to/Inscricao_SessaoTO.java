package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Inscricao_SessaoTO implements Serializable {

	private long id;
	private InscricaoTO inscricao;
	private SessaoTO sessao;
	
	public Inscricao_SessaoTO() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public InscricaoTO getInscricao() {
		return inscricao;
	}
	public void setInscricao(InscricaoTO inscricao) {
		this.inscricao = inscricao;
	}
	public SessaoTO getSessao() {
		return sessao;
	}
	public void setSessao(SessaoTO sessao) {
		this.sessao = sessao;
	}
	
}
