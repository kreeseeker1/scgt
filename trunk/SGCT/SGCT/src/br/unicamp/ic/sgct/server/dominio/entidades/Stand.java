package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_STAND", primaryKey = "id_stand")
public class Stand implements Serializable {
	@PersistentField(fieldName="id_stand", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField
	private int numero;

	@PersistentField
	private int metragem;

	public Stand() {
		super();
	}

	public Stand(int numero, int metragem) {
		super();
		this.numero = numero;
		this.metragem = metragem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getMetragem() {
		return metragem;
	}

	public void setMetragem(int metragem) {
		this.metragem = metragem;
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
	    
	    retValue = "Stand ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "numero = " + this.numero + TAB
	        + "metragem = " + this.metragem + TAB
	        + " )";
	
	    return retValue;
	}
}