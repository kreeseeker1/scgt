package br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao;

import br.unicamp.ic.sgct.client.aplicacao.comum.BaseListener;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

public interface InscricaoListener extends BaseListener {

	/**
	 * Faz registro no site criando usuario com login valendo e-mail fornecido e
	 * que deve ser unico dentre usuarios cadastrados.
	 */
	public abstract void inscrever(UsuarioTO usuario);
	
}