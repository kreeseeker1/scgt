package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class UsuarioTO implements Serializable {
	private long id;
	private String senha;
	private String email;
	//private String inscricaoAtiva;
	private List<InscricaoTO> inscUsuario = new ArrayList<InscricaoTO>();
	private List<Inscricao_SessaoTO> listInscricaoSessaoTO;
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

//	public String getInscricaoAtiva() {
//		return inscricaoAtiva;
//	}
//
//	public void setInscricaoAtiva(String inscricaoAtiva) {
//		this.inscricaoAtiva = inscricaoAtiva;
//	}
	
	public PessoaTO getPessoa() {
		return pessoa;
	}

	public List<InscricaoTO> getInscUsuario() {
		return inscUsuario;
	}

	public void setInscUsuario(List<InscricaoTO> inscUsuario) {
		this.inscUsuario = inscUsuario;
	}
	
	public void addInscUsuario(InscricaoTO inscricaoTO){
		this.inscUsuario.add(inscricaoTO);
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

	public List<Inscricao_SessaoTO> getListInscricaoSessaoTO() {
		return listInscricaoSessaoTO;
	}

	public void setListInscricaoSessaoTO(
			List<Inscricao_SessaoTO> listInscricaoSessaoTO) {
		this.listInscricaoSessaoTO = listInscricaoSessaoTO;
	}
	
}