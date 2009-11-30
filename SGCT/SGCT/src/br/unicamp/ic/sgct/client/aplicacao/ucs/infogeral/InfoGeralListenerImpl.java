package br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.SisGesConfTec;
import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListenerImpl;
import br.unicamp.ic.sgct.client.apresentacao.ucs.SobreScreen;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class InfoGeralListenerImpl extends BaseListenerImpl
		implements InfoGeralListener {

	BaseScreen screen;
	
	private InfoGeralServiceAsync service = GWT.create(InfoGeralService.class);

	public InfoGeralListenerImpl(MensagemUI msgWidget) {
		super(msgWidget);

		// Initialize the service proxy.
		if (service == null) {
			service = GWT.create(InfoGeralService.class);

		    ServiceDefTarget target = (ServiceDefTarget) service;
			target.setServiceEntryPoint( SisGesConfTec.instance().getModuleRelativeURL() + InfoGeralService._RELATIVE_PATH);		
		}
	}

	/**
	 * 
	 * @param int
	 */
	public void carregarInfoGeral(int secao) throws InfraException {
		service.carregarInfoGeral(secao, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				setInfoGeral(result);
			}

			public void onFailure(Throwable caught) {
				setMensagemErro(caught.getMessage());
			}
		});
	}

	public void setInfoGeral(String infoGeral) {
		((SobreScreen) this.screen).setInfoGeral(infoGeral);
	}

	public void setBaseScreen(BaseScreen screen) {
		this.screen = screen;
	}
}