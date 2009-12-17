package br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao;

import br.unicamp.ic.sgct.client.SisGesConfTec;
import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListenerImpl;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Controladora da Registrar usuario
 * 
 */
public class InscricaoListenerImpl extends BaseListenerImpl implements
		InscricaoListener {
	private InscricaoServiceAsync service = GWT
			.create(InscricaoService.class);

	public InscricaoListenerImpl(MensagemUI msgWidget) {
		super(msgWidget);

		// Initialize the service proxy.
		if (service == null) {
			service = GWT.create(InscricaoService.class);

		    ServiceDefTarget target = (ServiceDefTarget) service;
			target.setServiceEntryPoint( SisGesConfTec.instance().getModuleRelativeURL() + InscricaoService._RELATIVE_PATH);
		}
	}

	/**
	 * 
	 */
	public void inscrever(UsuarioTO usuario) {
		// Set up the callback object.
		AsyncCallback<UsuarioTO> callback = new AsyncCallback<UsuarioTO>() {
			public void onFailure(Throwable caught) {
				setMensagemErro(caught.getMessage());
			}

			public void onSuccess(UsuarioTO usuario) {
				setMensagemSucesso(" Usu\u00e1rio registrado no site! ");
			}
		};
		// Make the call to the service.
		service.inscrever(usuario, callback);
	}

}