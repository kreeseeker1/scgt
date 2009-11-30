package br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia;

import java.util.List;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("conferencia")
public interface ConferenciaService extends RemoteService {
	String _RELATIVE_PATH = "conferencia";

	/**
	 * 
	 * @return List<ConferenciaTO>
	 * 
	 * @throws InfraException
	 * @throws Exception
	 */
	public List<ConferenciaTO> listarConferencias() throws InfraException, Exception;
}