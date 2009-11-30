package br.unicamp.ic.sgct.server.dominio.entidades;

import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.OnSave;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;
import it.biobytes.ammentos.When;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_PRELETOR", primaryKey = "id_usuario")
public class Preletor extends Usuario {
	@PersistentField(fieldName="id_usuario")
	private long id;

	@PersistentField(size="30")
	private String instituicao;

	@PersistentField(fieldName="id_trabalho", type=FieldTypeEnum.ENTITY)
	private Trabalho trabalho;

	public Preletor() {
		super();
	}

	/**
	 * 
	 * @param idUsuario
	 */
	public Preletor(long id) {
		super(id);
	}

	/**
	 * 
	 * @param instituicao
	 * @param trabalho
	 */
	public Preletor(String instituicao, Trabalho trabalho, Usuario usuario) {
		super(usuario.getSenha(), usuario.getEmail(), usuario.getPessoa());

		this.instituicao = instituicao;
		this.trabalho    = trabalho;
	}

	@OnSave(When.BEFORE)
	private void synchronizeKeys() {
		this.id = super.getId();
	}

	//~ getters & setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public Trabalho getTrabalho() {
		return trabalho;
	}

	public void setTrabalho(Trabalho trabalho) {
		this.trabalho = trabalho;
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
	    
	    retValue = "Preletor ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "instituicao = " + this.instituicao + TAB
	        + "trabalho = " + (this.trabalho != null ? this.trabalho + TAB : "NULL ")
	        + " )";
	
	    return retValue;
	}
}