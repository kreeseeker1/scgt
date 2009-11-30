package br.unicamp.ic.sgct.server.recursos.persistencia;

import it.biobytes.ammentos.Ammentos;

/**
 * 
 * Classe singleton para obtencao de conexao com banco de dados
 */
public class AmmentosConnection {
	private static String JDBC_URL = "jdbc:hsqldb:hsql://localhost/";

	private static AmmentosConnection ammentosConnection = null;

	private AmmentosConnection() {
	}

	static public AmmentosConnection instance() {
		if (ammentosConnection == null) {
			ammentosConnection = new AmmentosConnection();

			Ammentos.setDataSource(DataSource.instance(JDBC_URL, "sa", ""));
		}
		return ammentosConnection;	
	}

	static public AmmentosConnection instance(String dbUrl, String user,
			String password) {
		if (ammentosConnection == null) {
			ammentosConnection = new AmmentosConnection();

			Ammentos.setDataSource(DataSource.instance(dbUrl, user, password));
		}
		return ammentosConnection;
	}
}