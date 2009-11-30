package br.unicamp.ic.sgct.server.dominio.entidades;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;
import it.biobytes.ammentos.PersistentList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.unicamp.ic.sgct.client.dominio.to.Periodo;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_SESSOESCONF", primaryKey = "id_sessaoconf")
public class Sessao implements Serializable {
	@PersistentField(fieldName="id_sessaoconf", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField(type=FieldTypeEnum.DATE)
	private Date data;

	/**
	 * Recebe um dos possiveis valores de Periodos
	 */
	@PersistentField
	private int sequencialPeriodo;

	@PersistentField(fieldName="id_sala", type=FieldTypeEnum.ENTITY)
	private Sala sala;

	@PersistentList(query = "id_sessaoconf=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)
	private List<Apresentacao> apresentacoes;

	@PersistentField(fieldName="id_conf", type=FieldTypeEnum.ENTITY)
	private Conferencia conferencia;

	@PersistentField(size="20")
	private String tema;

	//transient
	private Periodo periodo;

	public Sessao() {
		super();
	}

	/**
	 * 
	 * @param data
	 * @param sala
	 * @param periodo
	 * @param apresentacoes
	 */
	public Sessao(Date data, Sala sala, Periodo periodo, ArrayList<Apresentacao> apresentacoes) {
		super();
		this.data = data;
		this.sala = sala;
		this.periodo = periodo;
		this.sequencialPeriodo = periodo.getSequencial();
		this.apresentacoes = apresentacoes;
	}

	//~ getters & setters
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

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Conferencia getConferencia() {
		return conferencia;
	}

	public void setConferencia(Conferencia conferencia) {
		this.conferencia = conferencia;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public Sala getSala() {
		return sala;
	}

	public List<Apresentacao> getApresentacoes() {
		return apresentacoes;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public void setApresentacoes(List<Apresentacao> apresentacoes) {
		this.apresentacoes = apresentacoes;
	}

	public boolean isMatutina() {
		return Periodo.MATUTINO.equals(this.periodo);
	}
	
	public boolean isVespertina() {
		return Periodo.VESPERTINO.equals(this.periodo);
	}	

	public boolean isNoturna() {
		return Periodo.NOTURNO.equals(this.periodo);
	}

	/**
	 * Constructs a <code>String</code> with all attributes
	 * in name = value format.
	 *
	 * @return a <code>String</code> representation 
	 * of this object.
	 */
	public String toString()
	{
	    final String TAB = "    ";

	    String retValue = "";

	    retValue = "SessoesConferencia ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "data = " + this.data + TAB
	        + "sequencialPeriodo = " + this.sequencialPeriodo + TAB
	        + "sala = " + (this.sala != null ? this.sala.toString() + TAB : "NULL ")
	        + "apresentacoes = " + (this.apresentacoes != null ? this.apresentacoes.toString() + TAB : "NULL ")
	        + "periodo = " + this.periodo + TAB
	        + " )";

	    return retValue;
	}
}