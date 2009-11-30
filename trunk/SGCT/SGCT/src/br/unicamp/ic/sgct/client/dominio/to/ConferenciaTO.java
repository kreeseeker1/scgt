package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ConferenciaTO implements Serializable {
	private long id;
	private String titulo;
	private String descricao;
	private Date inicio;
	private Date termino;
	private String endereco;
	private Date inicioChamadaTrabalhos;
	private Date terminoChamadaTrabalhos;
	private CentroConvencaoTO centroConvencao;
	private List<SessaoTO> sessoesConferencia;

	public ConferenciaTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Date getInicioChamadaTrabalhos() {
		return inicioChamadaTrabalhos;
	}

	public void setInicioChamadaTrabalhos(Date inicioChamadaTrabalhos) {
		this.inicioChamadaTrabalhos = inicioChamadaTrabalhos;
	}

	public Date getTerminoChamadaTrabalhos() {
		return terminoChamadaTrabalhos;
	}

	public void setTerminoChamadaTrabalhos(Date terminoChamadaTrabalhos) {
		this.terminoChamadaTrabalhos = terminoChamadaTrabalhos;
	}

	public CentroConvencaoTO getCentroConvencao() {
		return centroConvencao;
	}

	public void setCentroConvencao(CentroConvencaoTO centroConvencao) {
		this.centroConvencao = centroConvencao;
	}

	public List<SessaoTO> getSessoesConferencia() {
		return sessoesConferencia;
	}

	public void setSessoesConferencia(List<SessaoTO> sessoesConferencia) {
		this.sessoesConferencia = sessoesConferencia;
	}
}