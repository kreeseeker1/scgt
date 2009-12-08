package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
public class SessaoTO implements Serializable {
	private long id;
	private Date data;
	/**
	 * Recebe um dos possiveis valores de Periodos
	 */
	private int sequencialPeriodo;
	private SalaTO sala;
	private List<ApresentacaoTO> apresentacoes;
	private ConferenciaTO conferencia;
	private String tema;
	private Periodo periodo;

	public SessaoTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getSequencialPeriodo() {
		return sequencialPeriodo;
	}

	public void setSequencialPeriodo(int sequencialPeriodo) {
		this.sequencialPeriodo = sequencialPeriodo;
	}

	public SalaTO getSala() {
		return sala;
	}

	public void setSala(SalaTO sala) {
		this.sala = sala;
	}

	public List<ApresentacaoTO> getApresentacoes() {
		return apresentacoes;
	}

	public void setApresentacoes(List<ApresentacaoTO> apresentacoes) {
		this.apresentacoes = apresentacoes;
	}

	public ConferenciaTO getConferencia() {
		return conferencia;
	}

	public void setConferencia(ConferenciaTO conferencia) {
		this.conferencia = conferencia;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

}