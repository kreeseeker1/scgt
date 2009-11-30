package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_SALA", primaryKey = "id_sala")
public class Sala implements Serializable {
	@PersistentField(fieldName="id_sala", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField(size="15")
	private String identificacao;

	@PersistentField
	private int capacidade;

	public Sala() {
		super();
	}

	public Sala(String identificacao, int capacidade) {
		super();
		this.identificacao = identificacao;
		this.capacidade = capacidade;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
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
	    
	    retValue = "Sala ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "identificacao = " + this.identificacao + TAB
	        + "capacidade = " + this.capacidade + TAB
	        + " )";
	
	    return retValue;
	}
}