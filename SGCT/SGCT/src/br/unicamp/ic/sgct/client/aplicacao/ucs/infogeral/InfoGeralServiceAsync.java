package br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface InfoGeralServiceAsync {

	void carregarInfoGeral(int secao, AsyncCallback<String> callback);	
}