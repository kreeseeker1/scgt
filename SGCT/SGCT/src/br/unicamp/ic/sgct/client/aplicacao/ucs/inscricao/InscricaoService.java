package br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("inscricao")
public interface InscricaoService extends RemoteService {
	String _RELATIVE_PATH = "inscricao"; 

	void inscrever(UsuarioTO usuario) throws InfraException;
}