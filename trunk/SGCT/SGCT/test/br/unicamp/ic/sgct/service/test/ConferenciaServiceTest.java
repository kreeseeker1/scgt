package br.unicamp.ic.sgct.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaService;
import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaServiceAsync;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ConferenciaServiceTest extends GWTTestCase {
	private final String _MODULE_NAME = "br.unicamp.ic.sgct.SisGesConfTec";

	private ConferenciaServiceAsync service;

/*	@Before
	public void setUp() {
		try {
			AmmentosConnection.instance();

			service = GWT.create(ConferenciaService.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}*/

	// #######[ TESTES FLUXO BASE ]#######
	@Test
	public void testListarConferencias() {
		try {
			service = GWT.create(ConferenciaService.class);

			AmmentosConnection.instance();			

			AsyncCallback<List<ConferenciaTO>> callback = new AsyncCallback<List<ConferenciaTO>>() {
				public void onFailure(Throwable caught) {
					Assert.assertTrue(false);
				}

				public void onSuccess(List<ConferenciaTO> result) {
					for (ConferenciaTO conferenciaTO : result) {
						System.out.println( conferenciaTO.toString() );
					}
					Assert.assertTrue(true);
				}
			};
			service.listarConferencias(callback);
		}
		catch (InfraException e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Override
	public String getModuleName() {
		return _MODULE_NAME;
	}
}