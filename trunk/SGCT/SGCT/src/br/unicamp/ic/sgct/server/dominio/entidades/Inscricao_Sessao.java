package br.unicamp.ic.sgct.server.dominio.entidades;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.FieldTypeEnum;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;

import java.io.Serializable;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_INSCRICAO_SESSAO", primaryKey = "id_insc_sessao")
public class Inscricao_Sessao implements Serializable{
	public static final String PERSISTENCE_DOMAIN = "T_INSCRICAO_SESSAO";
	
	@PersistentField(fieldName="id_insc_sessao", automatic=true, automaticType=AutomaticType.FRAMEWORK)
	private long id;
	
	@PersistentField(fieldName="id_insc", type=FieldTypeEnum.ENTITY)
	private Inscricao inscricao;
	
	@PersistentField(fieldName="id_sessaoconf", type=FieldTypeEnum.ENTITY)
	private Sessao sessao;
	
	
	public Inscricao_Sessao() {
		super();
	}
	
	/**
	 * 
	 * @param sessoes
	 * @param dt_inscricao
	 * @param dt_pagamento
	 * @param situacao
	 */
	public Inscricao_Sessao(Inscricao inscricao, Sessao sessao) {
		super();
		this.inscricao = inscricao; 
		this.sessao = sessao;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
}
