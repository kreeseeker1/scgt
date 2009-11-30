package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CancelarUsuarioService</code>.
 */
public interface CancelamentoServiceAsync {

	void cancelar(UsuarioTO usuario, AsyncCallback<UsuarioTO> callback);
}