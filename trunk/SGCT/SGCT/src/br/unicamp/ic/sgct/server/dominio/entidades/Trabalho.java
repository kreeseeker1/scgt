package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_TRABALHO", primaryKey = "id_trabalho")
public class Trabalho implements Serializable {
	@PersistentField(fieldName="id_trabalho", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long idTrabalho;

	@PersistentField(size="15")
	private String titulo;

	@PersistentField(size="50")
	private String resumo;

	@PersistentField(fieldName="id_preletor", type=FieldTypeEnum.ENTITY)
	private Preletor preletor;

	public Trabalho() {
		super();
	}

	/**
	 * 
	 * @param titulo
	 * @param resumo
	 * @param preletor
	 */
	public Trabalho(String titulo, String resumo, Preletor preletor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.preletor = preletor;
	}

	//~ getters & setters
	public long getIdTrabalho() {
		return idTrabalho;
	}

	public void setIdTrabalho(long id) {
		this.idTrabalho = id;
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

	public Preletor getPreletor() {
		return preletor;
	}

	public void setPreletor(Preletor preletor) {
		this.preletor = preletor;
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
	    
	    retValue = "Trabalho ( "
	        + super.toString() + TAB
	        + "idTrabalho = " + this.idTrabalho + TAB
	        + "titulo = " + this.titulo + TAB
	        + "resumo = " + this.resumo + TAB
	        + "preletor = " + (this.preletor != null ? this.preletor.getEmail() + TAB : "NULL ")
	        + " )";
	
	    return retValue;
	}	
}