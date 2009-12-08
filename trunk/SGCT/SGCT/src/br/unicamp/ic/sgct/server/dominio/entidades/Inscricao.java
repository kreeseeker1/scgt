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
@PersistentEntity(sourceDomain = "T_INSCRICAO", primaryKey = "id_insc")
public class Inscricao implements Serializable{
	@PersistentField(fieldName="id_insc", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;
	
	@PersistentList(query = "id_insc_sessao=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)
	private List<Inscricao_Sessao> inscricao_sessao;
	
	@PersistentField(type=FieldTypeEnum.DATE)
	private Date dt_inscricao;
	
	@PersistentField(type=FieldTypeEnum.DATE)
	private Date dt_pagamento;
	
	@PersistentField
	private int situacao;
	
	@PersistentField(fieldName="id_usuario", type=FieldTypeEnum.ENTITY)
	private Usuario usuario;

	public Inscricao() {
		super();
	}
	
	/**
	 * 
	 * @param sessoes
	 * @param dt_inscricao
	 * @param dt_pagamento
	 * @param situacao
	 */
	public Inscricao(List<Inscricao_Sessao> inscricao_sessao, Date dt_inscricao, Date dt_pagamento, int situacao) {
		super();
		this.inscricao_sessao = inscricao_sessao; 
		this.dt_inscricao = dt_inscricao;
		this.dt_pagamento = dt_pagamento;
		this.situacao = situacao;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Inscricao_Sessao> geInscricao_Sessao() {
		return inscricao_sessao;
	}

	public void setSessoesInscricao(List<Inscricao_Sessao> inscricao_Sessao) {
		this.inscricao_sessao = inscricao_Sessao;
	}
	
	public void addSessoesInscricao(Inscricao_Sessao inscricao_Sessao) {
		this.inscricao_sessao.add(inscricao_Sessao);
	}

	public Date getDt_inscricao() {
		return dt_inscricao;
	}

	public void setDt_inscricao(Date dt_inscricao) {
		this.dt_inscricao = dt_inscricao;
	}

	public Date getDt_pagamento() {
		return dt_pagamento;
	}

	public void setDt_pagamento(Date dt_pagamento) {
		this.dt_pagamento = dt_pagamento;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

}
