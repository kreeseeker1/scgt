package br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListener;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

public interface InfoGeralListener extends BaseListener {
	void carregarInfoGeral(int secao) throws InfraException;
	
	/**
	 * 
	 * @param org.gwtiger.client.screen.BaseScreen
	 */
	public abstract void setBaseScreen(BaseScreen screen);	
}