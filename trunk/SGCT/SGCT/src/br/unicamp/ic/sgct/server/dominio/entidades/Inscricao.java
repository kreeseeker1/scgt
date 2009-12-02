package br.unicamp.ic.sgct.server.dominio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;
import it.biobytes.ammentos.PersistentList;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_INSCRICAO", primaryKey = "id_insc")
public class Inscricao implements Serializable{
	@PersistentField(fieldName="id_insc", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;
	
	@PersistentList(query = "id_sessaoconf=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)
	private List<Sessao> sessoes;
	
	@PersistentField(type=FieldTypeEnum.DATE)
	private Date dt_inscricao;
	
	@PersistentField(type=FieldTypeEnum.DATE)
	private Date dt_pagamento;
	
	@PersistentField
	private int situacao;

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
	public Inscricao(List<Sessao> sessoes, Date dt_inscricao, Date dt_pagamento, int situacao) {
		super();
		this.sessoes = sessoes; 
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

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
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
