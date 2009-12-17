package br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia;

import java.util.List;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.SisGesConfTec;
import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListenerImpl;
import br.unicamp.ic.sgct.client.apresentacao.ucs.ConferenciaScreen;
import br.unicamp.ic.sgct.client.apresentacao.ucs.InscricaoScreen;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class ConferenciaListenerImpl extends BaseListenerImpl implements ConferenciaListener {
	private BaseScreen screen;
	private ConferenciaServiceAsync service;

	public ConferenciaListenerImpl(MensagemUI msgWidget) {
		super(msgWidget);

		getService();
	}

	/**
	 * 
	 * @return ConferenciaServiceAsync
	 */
	public ConferenciaServiceAsync getService() {
		if (service == null) {
			service = GWT.create(ConferenciaService.class);

		    ServiceDefTarget target = (ServiceDefTarget) service;
			target.setServiceEntryPoint( SisGesConfTec.instance().getModuleRelativeURL() + ConferenciaService._RELATIVE_PATH);
		}
		return service;
	}

	/**
	 * 
	 * @return List<ConferenciaTO>
	 * 
	 * @throws InfraException
	 * @throws Exception
	 */
	public void listarConferencias() throws InfraException, Exception {

		
		getService().listarConferencias(new AsyncCallback<List<ConferenciaTO>>() {
			public void onSuccess(List<ConferenciaTO> result) {
				setConferencias( result );
			}

			public void onFailure(Throwable caught){
				setMensagemErro( caught.getMessage() );
				
			}
		});
	}
	
	/**
	 * 
	 * @return List<ConferenciaTO>
	 * 
	 * @throws InfraException
	 * @throws Exception
	 */
	public void listarConferencias(final HorizontalPanel horizontalPanel) throws InfraException, Exception {

		
		getService().listarConferencias(new AsyncCallback<List<ConferenciaTO>>() {
			public void onSuccess(List<ConferenciaTO> result) {
				setConferencias( result );
			}

			public void onFailure(Throwable caught){
				setMensagemErro( "Ocorreu um problema na Base de Dados, favor verificar com o Administrador." );
				
				if (caught instanceof InfraException) {
					horizontalPanel.setVisible(false);
				}
			}
		});
	}

	public void setBaseScreen(BaseScreen screen) {
		this.screen = screen;
	}

	private void setConferencias(List<ConferenciaTO> conferencias) {
		if (this.screen instanceof ConferenciaScreen)
			((ConferenciaScreen) this.screen).setConferencias(conferencias);
		else if(this.screen instanceof InscricaoScreen)
			((InscricaoScreen) this.screen).setConferencias(conferencias);
	}
}