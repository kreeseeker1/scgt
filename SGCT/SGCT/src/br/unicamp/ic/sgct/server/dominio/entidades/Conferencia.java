package br.unicamp.ic.sgct.server.dominio.entidades;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;
import it.biobytes.ammentos.PersistentList;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_CONFERENCIA", primaryKey = "id_conf")
public class Conferencia implements Serializable {
	public static final String PERSISTENCE_DOMAIN = "T_CONFERENCIA";

	@PersistentField(fieldName="id_conf", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;

	@PersistentField(size="20")
	private String titulo;
	
	@PersistentField(size="150")
	private String descricao;

	@PersistentField(type=FieldTypeEnum.DATE)
	private Date inicio;

	@PersistentField(type=FieldTypeEnum.DATE)
	private Date termino;

	@PersistentField(size="50")
	private String endereco;

	@PersistentField(type=FieldTypeEnum.DATE)
	private Date inicioChamadaTrabalhos;
	
	@PersistentField(type=FieldTypeEnum.DATE)
	private Date terminoChamadaTrabalhos;

	@PersistentField(fieldName="id_cconv", type=FieldTypeEnum.ENTITY)
	private CentroConvencao centroConvencao;

	@PersistentList(query = "id_conf=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)
	private List<Sessao> sessoesConferencia;

	public Conferencia() {
		super();
	}

	/**
	 * 
	 * @param titulo
	 * @param descricao
	 * @param inicio
	 * @param termino
	 * @param endereco
	 * @param inicioChamadaTrabalhos
	 * @param terminoChamadaTrabalhos
	 * @param centro
	 */
	public Conferencia(String titulo, String descricao, Date inicio,
			Date termino, String endereco, Date inicioChamadaTrabalhos,
			Date terminoChamadaTrabalhos, CentroConvencao centro, List<Sessao> sessoes) {
		super();
		this.titulo = titulo;
		this.descricao = descricao;
		this.inicio = inicio;
		this.termino = termino;
		this.endereco = endereco;
		this.inicioChamadaTrabalhos = inicioChamadaTrabalhos;
		this.terminoChamadaTrabalhos = terminoChamadaTrabalhos;
		this.centroConvencao = centro;
		this.sessoesConferencia = sessoes;
	}

	//~ getters & setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	/**
	 * Inicio para submissao do resumo de artigos
	 * 
	 * @return java.util.Date
	 */
	public Date getInicioChamadaTrabalhos() {
		return inicioChamadaTrabalhos;
	}

	public void setInicioChamadaTrabalhos(Date inicioChamadaTrabalhos) {
		this.inicioChamadaTrabalhos = inicioChamadaTrabalhos;
	}

	/**
	 * Termino para submissao do resumo de artigos
	 * 
	 * @return java.util.Date
	 */
	public Date getTerminoChamadaTrabalhos() {
		return terminoChamadaTrabalhos;
	}

	public void setTerminoChamadaTrabalhos(Date terminoChamadaTrabalhos) {
		this.terminoChamadaTrabalhos = terminoChamadaTrabalhos;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CentroConvencao getCentroConvencao() {
		return centroConvencao;
	}

	public void setCentroConvencao(CentroConvencao centroConvencao) {
		this.centroConvencao = centroConvencao;
	}

	public List<Sessao> getSessoesConferencia() {
		return sessoesConferencia;
	}

	public void setSessoesConferencia(List<Sessao> sessoesConferencia) {
		this.sessoesConferencia = sessoesConferencia;
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
	    
	    retValue = "Conferencia ( "
	        + super.toString() + TAB
	        + "id = " + this.id + TAB
	        + "titulo = " + this.titulo + TAB
	        + "descricao = " + this.descricao + TAB
	        + "inicio = " + this.inicio + TAB
	        + "termino = " + this.termino + TAB
	        + "endereco = " + this.endereco + TAB
	        + "inicioChamadaTrabalhos = " + this.inicioChamadaTrabalhos + TAB
	        + "terminoChamadaTrabalhos = " + this.terminoChamadaTrabalhos + TAB
	        +  "centroConvencao = " + (this.centroConvencao != null ? this.centroConvencao + TAB : "NULL ")
	        + " )";
	
	    return retValue;
	}
}