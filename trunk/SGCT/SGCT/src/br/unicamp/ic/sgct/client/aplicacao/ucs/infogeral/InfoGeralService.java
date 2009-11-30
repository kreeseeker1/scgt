package br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral;

import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("info-geral")
public interface InfoGeralService extends RemoteService {
	String _RELATIVE_PATH = "info-geral";

	String carregarInfoGeral(int secao) throws InfraException;
}