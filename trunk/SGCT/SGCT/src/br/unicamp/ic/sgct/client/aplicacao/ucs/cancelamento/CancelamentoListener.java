package br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento;

import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListener;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

public interface CancelamentoListener extends BaseListener {

	/**
	 * Faz cancelamento do usuario dado seu email e senha cadastradas.
	 */
	public abstract void cancelar(UsuarioTO usuario);
}