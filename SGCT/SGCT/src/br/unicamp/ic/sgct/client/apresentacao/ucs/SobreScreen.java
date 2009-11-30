package br.unicamp.ic.sgct.client.apresentacao.ucs;

import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral.InfoGeralListener;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class SobreScreen extends BaseScreen {
	InfoGeralListener controladora;
	private String infoGeral;

	Label lblHtml = new Label("--");

	public SobreScreen(InfoGeralListener controladora) {
		super();

		this.controladora = controladora;

		FlowPanel panelGeral = new FlowPanel();
		panelGeral.add(controladora.getMensagemWidget());

		controladora.getMensagemWidget().limparMensagem();
		panelGeral.clear();

		showLoading(false);

		panelGeral.add(controladora.getMensagemWidget());
		panelGeral.add(lblHtml);
		panelGeral.setWidth("95%");

		dockPanel.add(panelGeral);

		this.initWidget(dockPanel);
	}

	/**
	 * load method is called when the screen first opens
	 */
	public void load() {
		try {
			controladora.setBaseScreen(this);
			controladora.carregarInfoGeral(1);

			lblHtml.setText(this.infoGeral);
		} catch (InfraException e) {
			e.printStackTrace();
			System.out
					.println("Arquivo de recurso relativo a pagina estatica nao pode ser carregado.");
		}
	}

	public String getInfoGeral() {
		return infoGeral;
	}

	public void setInfoGeral(String strInfoGeral) {
		this.infoGeral = strInfoGeral;
	}
}