package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UsuarioTO implements Serializable {
	private long id;
	private String senha;
	private String email;
	private String inscricaoAtiva;
	private PessoaTO pessoa;
	private Date dataEfetivacaoPagto;

	public UsuarioTO() {
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricaoAtiva() {
		return inscricaoAtiva;
	}

	public void setInscricaoAtiva(String inscricaoAtiva) {
		this.inscricaoAtiva = inscricaoAtiva;
	}

	public PessoaTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaTO pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataEfetivacaoPagto() {
		return dataEfetivacaoPagto;
	}

	public void setDataEfetivacaoPagto(Date dataEfetivacaoPagto) {
		this.dataEfetivacaoPagto = dataEfetivacaoPagto;
	}
}