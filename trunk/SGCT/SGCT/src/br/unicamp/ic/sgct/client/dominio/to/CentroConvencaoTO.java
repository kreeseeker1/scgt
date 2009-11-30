package br.unicamp.ic.sgct.client.dominio.to;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class CentroConvencaoTO implements Serializable {
	private long id;
	private String nome;
	private String fone;
	private String fax;
	private String endereco;
	private List<StandTO> stands;
	private List<SalaTO> salas;

	public CentroConvencaoTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<StandTO> getStands() {
		return stands;
	}

	public void setStands(List<StandTO> stands) {
		this.stands = stands;
	}

	public List<SalaTO> getSalas() {
		return salas;
	}

	public void setSalas(List<SalaTO> salas) {
		this.salas = salas;
	}
}