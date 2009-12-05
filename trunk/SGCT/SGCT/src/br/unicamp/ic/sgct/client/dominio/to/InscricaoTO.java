package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class InscricaoTO implements Serializable {

	private long id;
	private Date dt_inscricao;
	private Date dt_pagamento;
	private int situacao;
	private List<SessaoTO> sessoesInscricao = new ArrayList<SessaoTO>();
	
	public InscricaoTO() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List<SessaoTO> getSessoesInscricao() {
		return sessoesInscricao;
	}
	public void setSessoesInscricao(List<SessaoTO> sessoesInscricao) {
		this.sessoesInscricao = sessoesInscricao;
	}
	public void addSessoesInscricao(SessaoTO sessoesInscricaoTO) {
		this.sessoesInscricao.add(sessoesInscricaoTO);
	}
	
}