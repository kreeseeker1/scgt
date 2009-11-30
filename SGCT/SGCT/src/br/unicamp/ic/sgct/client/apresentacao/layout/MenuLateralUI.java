package br.unicamp.ic.sgct.client.apresentacao.layout;

import br.unicamp.ic.sgct.client.apresentacao.recursos.ImagensResource;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.Widget;

public class MenuLateralUI extends Composite {

	private int nextHeaderIndex = 0;
	private DecoratedStackPanel stackPanel = new DecoratedStackPanel();

	/**
	 * Constructs a new shortcuts widget using the specified images.
	 * 
	 * @param imgRsrc
	 *            a bundle that provides the images for this widget
	 */
	public MenuLateralUI(ImagensResource imgRsrc) {
		// Create the groups within the stack panel.
		add(new MenuLateralOpcoesUI(imgRsrc), "     M  E  N  U");
		initWidget(stackPanel);
	}

	@Override
	protected void onLoad() {
		// Show the menu group by default.
		stackPanel.showStack(0);
	}

	private void add(Widget widget, String caption) {
		widget.addStyleName("mail-StackContent");
		stackPanel.add(widget, createHeaderHTML(caption), true);
	}

	private void add(Widget widget, AbstractImagePrototype imageProto,
			String caption) {
		widget.addStyleName("mail-StackContent");
		stackPanel.add(widget, createHeaderHTML(imageProto, caption), true);
	}

	/**
	 * Creates an HTML fragment that places an image & caption together, for use
	 * in a group header.
	 * 
	 * @param imageProto
	 *            an image prototype for an image
	 * @param caption
	 *            the group caption
	 * @return the header HTML fragment
	 */
	private String createHeaderHTML(String caption) {
		nextHeaderIndex++;

		String captionHTML = "<table class='caption' cellpadding='0' cellspacing='0'>"
				+ "<tr><td align=\"center\" class='rcaption'><b style='white-space:nowrap'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ caption + "</b></td></tr></table>";
		return captionHTML;
	}

	/**
	 * Creates an HTML fragment that places an image & caption together, for use
	 * in a group header.
	 * 
	 * @param imageProto
	 *            an image prototype for an image
	 * @param caption
	 *            the group caption
	 * @return the header HTML fragment
	 */
	private String createHeaderHTML(AbstractImagePrototype imageProto,
			String caption) {
		nextHeaderIndex++;

		String captionHTML = "<table class='caption' cellpadding='0' cellspacing='0'>"
				+ "<tr><td class='lcaption'>"
				+ imageProto.getHTML()
				+ "</td><td class='rcaption'><b style='white-space:nowrap'>"
				+ caption + "</b></td></tr></table>";
		return captionHTML;
	}
}