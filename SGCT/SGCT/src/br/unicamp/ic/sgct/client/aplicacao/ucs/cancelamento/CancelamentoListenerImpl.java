package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import java.util.List;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.SisGesConfTec;
import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListenerImpl;
import br.unicamp.ic.sgct.client.apresentacao.ucs.CancelamentoScreen;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.InscricaoTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * Controladora da Cancelar inscricao
 * 
 */
public class CancelamentoListenerImpl extends BaseListenerImpl implements
		CancelamentoListener {
	private BaseScreen screen;
	private CancelamentoServiceAsync service = GWT
			.create(CancelamentoService.class);

	public CancelamentoListenerImpl(MensagemUI msgWidget) {
		super(msgWidget);

		// Initialize the service proxy.
		if (service == null) {
			service = GWT.create(CancelamentoService.class);

		    ServiceDefTarget target = (ServiceDefTarget) service;
			target.setServiceEntryPoint( SisGesConfTec.instance().getModuleRelativeURL() + CancelamentoService._RELATIVE_PATH);
		}
	}

	/**
	 * 
	 */
	public void cancelar(UsuarioTO usuario) {
		// Set up the callback object.
		AsyncCallback<UsuarioTO> callback = new AsyncCallback<UsuarioTO>() {
			public void onFailure(Throwable caught) {
				setMensagemErro(caught.getMessage());
			}

			public void onSuccess(UsuarioTO usuario) {
				setMensagemSucesso("Sua inscricao foi cancelada com sucesso!");
			}
		};
		// Make the call to the service.
		service.cancelar(usuario, callback);
	}
	
	
	public void listarInscricoes(UsuarioTO usuario) throws InfraException, Exception {

		service.listarInscricoes(usuario, new AsyncCallback<List<InscricaoTO>>() {
			public void onSuccess(List<InscricaoTO> result) {
				setInscricoes( result );
			}

			public void onFailure(Throwable caught){
				setMensagemErro( caught.getMessage() );
				
			}
		});
	}
	
	public void setBaseScreen(BaseScreen screen) {
		this.screen = screen;
	}

	private void setInscricoes(List<InscricaoTO> inscricao) {
		if (this.screen instanceof CancelamentoScreen)
			((CancelamentoScreen) this.screen).setInscricoes(inscricao);
	}
}