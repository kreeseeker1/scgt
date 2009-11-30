package br.unicamp.ic.sgct.client.aplicacao.comum;

import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;

/**
 * Interface base das controladoras (ListenerImpl). Prove recursos gerais para
 * controladoras, como acesso ao widget <code>MensagemUI<code>.
 * 
 */
public interface BaseListener {

	public abstract MensagemUI getMensagemWidget();

	public abstract void setMensagemWidget(MensagemUI mensagemWidget);

	public abstract void setMensagemErro(String mensagem);

	public abstract void setMensagemSucesso(String mensagem);

}