package br.unicamp.ic.sgct.client.aplicacao.comum;

import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;

/**
 * Implementacao base (super-classe) das controladoras (ListenerImpl). Prove recursos gerais para
 * controladoras, como acesso ao widget <code>MensagemUI<code>.
 * 
 */
public abstract class BaseListenerImpl implements BaseListener {
	private MensagemUI mensagemWidget;

	/**
	 * 
	 * @param msgWidget
	 */
	public BaseListenerImpl(MensagemUI msgWidget) {
		super();
		this.mensagemWidget = msgWidget;
	}

	public MensagemUI getMensagemWidget() {
		return mensagemWidget;
	}

	public void setMensagemWidget(MensagemUI mensagemWidget) {
		this.mensagemWidget = mensagemWidget;
	}

	public void setMensagemErro(String mensagem) {
		this.mensagemWidget.setMensagemErro(mensagem);
	}

	public void setMensagemSucesso(String mensagem) {
		this.mensagemWidget.setMensagemSucesso(mensagem);
	}
}