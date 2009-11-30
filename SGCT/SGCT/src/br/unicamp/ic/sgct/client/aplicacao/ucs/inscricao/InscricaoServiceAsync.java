package br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao;

import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RegistrarUsuarioService</code>.
 */
public interface InscricaoServiceAsync {

	void inscrever(UsuarioTO usuario, AsyncCallback<UsuarioTO> callback);
}