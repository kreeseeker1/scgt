package br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListener;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

import com.google.gwt.user.client.ui.HorizontalPanel;

public interface ConferenciaListener extends BaseListener {

	/**
	 * 
	 * @return List<ConferenciaTO>
	 * 
	 * @throws InfraException
	 * @throws Exception
	 */
	public abstract void listarConferencias()
			throws InfraException, Exception;
	
	public abstract void listarConferencias(HorizontalPanel horizontalPanel) throws InfraException, Exception;
	
	/**
	 * 
	 * @param org.gwtiger.client.screen.BaseScreen
	 */
	public abstract void setBaseScreen(BaseScreen screen);
}