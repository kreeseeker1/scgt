package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import java.util.List;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.InscricaoTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CancelarUsuarioService</code>.
 */
public interface CancelamentoServiceAsync {

	void cancelar(UsuarioTO usuario, AsyncCallback<UsuarioTO> callback);
	
	void listarInscricoes(UsuarioTO usuario,AsyncCallback<List<InscricaoTO>> callback) throws InfraException, Exception;
}