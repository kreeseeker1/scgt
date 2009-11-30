package br.unicamp.ic.sgct.server.recursos.persistencia;

import it.biobytes.ammentos.BaseDataSource;

/**
 * 
 * Classe singleton que representa fonte de dados para mapear a base de dados
 */
public class DataSource extends BaseDataSource {

	private final static String driver = "org.hsqldb.jdbcDriver";

	private static DataSource dataSource = null;

	private DataSource(String dbUrl, String user, String password) {
		super(driver, dbUrl, user, password);
	}

	static public DataSource instance(String dbUrl, String user, String password) {
		if (dataSource == null) {
			dataSource = new DataSource(dbUrl, user, password);
		}
		return dataSource;
	}
}