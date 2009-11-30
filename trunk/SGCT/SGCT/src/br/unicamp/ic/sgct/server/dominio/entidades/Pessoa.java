package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_PESSOA", primaryKey = "id_pessoa")
public class Pessoa implements Serializable {
	@PersistentField(fieldName="id_pessoa", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long idPessoa;

	@PersistentField(size="15")
	private String nome;

	@PersistentField(size="15")
	private String sobreNome;

	@PersistentField(size="18")
	private String foneResidencial;

	@PersistentField(size="18")
	private String celular;

	public Pessoa() {
		super();
	}

	/**
	 * 
	 * @param nome
	 * @param sobreNome
	 * @param foneResidencial
	 * @param celular
	 */
	public Pessoa(String nome, String sobreNome, String foneResidencial,
			String celular) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.foneResidencial = foneResidencial;
		this.celular = celular;
	}

	//~ getters & setters
	public long getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(long id) {
		this.idPessoa = id;
	}	

	public String getNomeCompleto() {
		return nome + " " + sobreNome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}
	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}
	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
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
	    
	    retValue = "Pessoa ( "
	        + super.toString() + TAB
	        + "idPessoa = " + this.idPessoa + TAB
	        + "nome = " + this.nome + TAB
	        + "sobreNome = " + this.sobreNome + TAB
	        + "foneResidencial = " + this.foneResidencial + TAB
	        + "celular = " + this.celular + TAB
	        + " )";
	
	    return retValue;
	}
}