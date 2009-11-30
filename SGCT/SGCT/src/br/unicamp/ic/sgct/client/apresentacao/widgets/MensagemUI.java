package br.unicamp.ic.sgct.client.apresentacao.widgets;

import br.unicamp.ic.sgct.client.apresentacao.recursos.ImagensResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class MensagemUI extends Composite {
	private final String MSG_ERRO_STYLE = "msgWidgetErro";
	private final String MSG_SUCESSO_STYLE = "msgWidgetSucesso";
	
	private Label lblMensagem;
	private HorizontalPanel panelMensagem;

	private String mensagem;
	private boolean visivel;

	private ImagensResource imgRsrc;
	private Image successIcone;	
	private Image warningIcone;

	public MensagemUI() {
		super();

		imgRsrc = GWT.create(ImagensResource.class);

		successIcone = imgRsrc.successmsg().createImage();
		warningIcone = imgRsrc.warningmsg().createImage();

		panelMensagem = new HorizontalPanel();
		panelMensagem.setSize("100%", "30px");

		lblMensagem = new Label();

		panelMensagem.add(successIcone);
		panelMensagem.add(warningIcone);
		panelMensagem.add(lblMensagem);

		this.initWidget(panelMensagem);
		panelMensagem.setVisible(false);
	}

	public boolean isVisivel() {
		return visivel;
	}

	private void setVisivel(boolean visivel) {
		this.visivel = visivel;
		panelMensagem.setVisible(visivel);
	}

	// ~ getters & setters
	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagemErro(String mensagem) {
		this.mensagem = mensagem;

		this.lblMensagem.setText(mensagem);
		this.lblMensagem.addStyleDependentName(MSG_ERRO_STYLE);

		panelMensagem.getWidget(0).setVisible(false);
		panelMensagem.getWidget(1).setVisible(true);
		this.setVisivel(true);
	}

	public void setMensagemSucesso(String mensagem) {
		this.mensagem = mensagem;

		this.lblMensagem.setText(mensagem);
		this.lblMensagem.addStyleDependentName(MSG_SUCESSO_STYLE);

		panelMensagem.getWidget(0).setVisible(true);
		panelMensagem.getWidget(1).setVisible(false);
		this.setVisivel(true);
	}
	
	public void limparMensagem() {
		panelMensagem.getWidget(0).setVisible(false);
		panelMensagem.getWidget(1).setVisible(false);
		this.lblMensagem.setText("");		
	}
}