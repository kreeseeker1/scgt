package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_APRES", primaryKey = "id_apres")
public class Apresentacao implements Serializable {
	@PersistentField(fieldName="id_apres", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField(fieldName="id_trabalho", type=FieldTypeEnum.ENTITY)
	private Trabalho trabalho;
	
	@PersistentField(fieldName="id_usuario", type=FieldTypeEnum.ENTITY)
	private Preletor preletor;

	@PersistentField(fieldName="id_sessaoconf", type=FieldTypeEnum.ENTITY)
	private Sessao sessao;

	@PersistentField
	private int horarioInicio;
	
	@PersistentField
	private int duracao;

	public Apresentacao() {
		super();
	}

	/**
	 * 
	 * @param trabalho
	 * @param preletor
	 * @param horarioInicio
	 * @param duracao
	 */
	public Apresentacao(Trabalho trabalho, Preletor preletor,
			int horarioInicio, int duracao) {
		super();
		this.trabalho = trabalho;
		this.preletor = preletor;
		this.horarioInicio = horarioInicio;
		this.duracao = duracao;
	}

	//~ getters & setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
	}

	public Preletor getPreletor() {
		return preletor;
	}

	public void setPreletor(Preletor preletor) {
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

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
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
	    
	    retValue = "Apresentacao ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "trabalho = " + (this.trabalho != null ? this.trabalho.toString() + TAB : "NULL ")
	        + "preletor = " + (this.preletor != null ? this.preletor.toString() + TAB : "NULL ")
	        + "horarioInicio = " + this.horarioInicio + TAB
	        + "duracao = " + this.duracao + TAB
	        + " )";
	
	    return retValue;
	}

	
}