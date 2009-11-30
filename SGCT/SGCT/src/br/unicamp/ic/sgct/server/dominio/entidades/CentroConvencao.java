package br.unicamp.ic.sgct.server.dominio.entidades;

import it.biobytes.ammentos.AutomaticType;
import it.biobytes.ammentos.PersistentEntity;
import it.biobytes.ammentos.PersistentField;
import it.biobytes.ammentos.PersistentList;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
@PersistentEntity(sourceDomain = "T_CENTROCONV", primaryKey = "id_cconv")
public class CentroConvencao implements Serializable {
		@PersistentField(fieldName="id_cconv", automatic=true, automaticType=AutomaticType.FRAMEWORK)
		private long id;

		@PersistentField(size="25")
		private String nome;

		@PersistentField(size="18")
		private String fone;

		@PersistentField(size="18")
		private String fax;

		@PersistentField(size="50")
		private String endereco;
		
		@PersistentList(query = "id_cconv=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)		
		private List<Stand> stands;
		
		@PersistentList(query = "id_cconv=?", cascadeOnSave = true, cascadeOnDelete = false, deleteOnRemove = false)
		private List<Sala> salas;

		public CentroConvencao() {
			super();
		}

		/**
		 * 
		 * @param nome
		 * @param fone
		 * @param fax
		 * @param endereco
		 */
		public CentroConvencao(String nome, String fone, String fax,
				String endereco) {
			super();
			this.nome = nome;
			this.fone = fone;
			this.fax = fax;
			this.endereco = endereco;
		}

		//~ getters & setters
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

		public List<Stand> getStands() {
			return stands;
		}

		public void setStands(List<Stand> stands) {
			this.stands = stands;
		}

		public List<Sala> getSalas() {
			return salas;
		}

		public void setSalas(List<Sala> salas) {
			this.salas = salas;
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

		    retValue = "CentroConvencao ( "
		        + super.toString() + TAB
		        + "id = " + this.id + TAB
		        + "nome = " + this.nome + TAB
		        + "fone = " + this.fone + TAB
		        + "fax = " + this.fax + TAB
		        + "endereco = " + this.endereco + TAB
		        + "stands = " + (this.stands != null ? this.stands.toString() + TAB : "NULL ")
		        + "salas = "  + (this.salas  != null ? this.salas.toString()  + TAB : "NULL ")
		        + " )";

		    return retValue;
		}
}