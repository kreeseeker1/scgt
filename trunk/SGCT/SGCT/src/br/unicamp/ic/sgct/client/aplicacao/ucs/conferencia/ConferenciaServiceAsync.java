package br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia;

import java.util.List;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConferenciaServiceAsync {
	void listarConferencias(AsyncCallback<List<ConferenciaTO>> callback)
			throws InfraException, Exception;
}