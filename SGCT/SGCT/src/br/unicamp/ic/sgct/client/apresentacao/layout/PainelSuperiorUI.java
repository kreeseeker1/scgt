package br.unicamp.ic.sgct.client.apresentacao.layout;

import br.unicamp.ic.sgct.client.apresentacao.recursos.ImagensResource;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Image;

/**
 * Cabecalho
 */
public class PainelSuperiorUI extends Composite {

	public PainelSuperiorUI(ImagensResource imgRsrc) {
		DockPanel dockPanel = new DockPanel();
		dockPanel.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		dockPanel.setSpacing(10);

		Image logo = imgRsrc.logo().createImage();
		dockPanel.add(logo, DockPanel.CENTER);

		initWidget(dockPanel);
	}
}