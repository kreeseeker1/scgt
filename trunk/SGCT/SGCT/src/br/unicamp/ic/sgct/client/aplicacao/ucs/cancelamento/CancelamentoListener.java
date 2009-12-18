package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListener;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

public interface CancelamentoListener extends BaseListener {

	/**
	 * Faz cancelamento do usuario dado seu email e senha cadastradas.
	 */
	public abstract void cancelar(UsuarioTO usuario);
	
	public abstract void listarInscricoes(UsuarioTO usuario)throws InfraException, Exception;
	
	public abstract void setBaseScreen(BaseScreen screen);
}