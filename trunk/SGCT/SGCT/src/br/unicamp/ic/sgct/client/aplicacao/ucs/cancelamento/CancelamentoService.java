package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("cancelamento")
public interface CancelamentoService extends RemoteService {
	String _RELATIVE_PATH = "cancelamento"; 

	void cancelar(UsuarioTO usuario) throws InfraException;
}