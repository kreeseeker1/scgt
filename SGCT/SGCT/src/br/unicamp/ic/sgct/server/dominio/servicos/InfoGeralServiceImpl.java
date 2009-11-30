package br.unicamp.ic.sgct.server.dominio.servicos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral.InfoGeralService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InfoGeralServiceImpl extends RemoteServiceServlet
		implements InfoGeralService {

	/**
	 * carregarInfoGeral
	 */
	public String carregarInfoGeral(int secao) throws InfraException {
		String infoGeral = null;

		//TODO: API Servlet retorna contextPath nulo qdo servlet eh deployada na raiz 
		String contextPath = this.getThreadLocalRequest().getContextPath();

		String url = SecaoInfoGeral.mapearUrlInfoGeral(secao);

		InputStream is = null;
		if (contextPath != null && !"".equals(contextPath)) {
			is = this.getClass().getClassLoader().getResourceAsStream(contextPath + url);
		}
		else {
			is = this.getClass().getClassLoader().getResourceAsStream(url);
		}

		if (is != null) {
			System.out.println("Carregou!!!");

			infoGeral = converterStreamToString(is);
		}
		else {
			System.out.println("Nao pode ler arquivo estatico!!!");
		}
		return infoGeral;
	}

	/**
	 * converterStreamToString 
	 */
	private String converterStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader( new InputStreamReader( is ) );

		StringBuilder stb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				stb.append(line + "\n");
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		return stb.toString();
	}
}