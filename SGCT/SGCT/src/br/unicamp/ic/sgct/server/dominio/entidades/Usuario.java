package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_USUARIO", primaryKey = "id_usuario", validator = "br.unicamp.ic.sgct.server.validation.InscricaoValidator")
public class Usuario implements Serializable {
	@PersistentField(fieldName="id_usuario", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField(size="08")
	private String senha;

	@PersistentField(size="01")
	private String inscricaoAtiva;

	@PersistentField(size="30")
	private String email;

	@PersistentField(fieldName="id_pessoa", type=FieldTypeEnum.ENTITY)
	private Pessoa pessoa;

	@PersistentField(type=FieldTypeEnum.DATE)
	private Date dataEfetivacaoPagto;	

	public Usuario() {
		super();
	}

	/**
	 * 
	 * @param idUsuario
	 */
	public Usuario(long id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 * @param senha
	 * @param email
	 * @param pessoa
	 */
	public Usuario(String senha, String email, Pessoa pessoa) {
		super();
		this.senha = senha;
		this.email = email;
		this.pessoa = pessoa;
	}

	public Usuario(UsuarioTO to) {
		this.senha = to.getSenha();
		this.email = to.getEmail();
		this.inscricaoAtiva = to.getInscricaoAtiva();
		this.dataEfetivacaoPagto = to.getDataEfetivacaoPagto();
		// System.out.println("Usuario.dataEfetivacaoPagto: " + this.dataEfetivacaoPagto);
		
		this.pessoa = new Pessoa();
		this.pessoa.setCelular( to.getPessoa().getCelular() );
		this.pessoa.setFoneResidencial( to.getPessoa().getFoneResidencial() );
		this.pessoa.setNome( to.getPessoa().getNome() );
		this.pessoa.setSobreNome( to.getPessoa().getSobreNome() );
	}
	
	//~ getters & setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getInscricaoAtiva() {
		return inscricaoAtiva;
	}
	public void setInscricaoAtiva(String inscricaoAtiva) {
		this.inscricaoAtiva = inscricaoAtiva;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataEfetivacaoPagto() {
		return dataEfetivacaoPagto;
	}

	public void setDataEfetivacaoPagto(Date dataEfetivacaoPagto) {
		this.dataEfetivacaoPagto = dataEfetivacaoPagto;
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
	    
	    retValue = "Usuario ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "senha = " + this.senha + TAB
	        + "inscricaoAtiva = " + this.senha + TAB
	        + "email = " + this.email + TAB
	        + "pessoa = " + (this.pessoa != null ? this.pessoa.toString() + TAB : "NULL ")
	        + " )";

	    return retValue;
	}
}