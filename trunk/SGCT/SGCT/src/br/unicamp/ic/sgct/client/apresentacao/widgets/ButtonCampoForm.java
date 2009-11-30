package br.unicamp.ic.sgct.client.apresentacao.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class ButtonCampoForm extends Button {

	public ButtonCampoForm() {
		super();
		addStyleDependentName("Form");
	}

	public ButtonCampoForm(Element element) {
		super(element);
		addStyleDependentName("Form");
	}

	public ButtonCampoForm(String html, ClickHandler handler) {
		super(html, handler);
		addStyleDependentName("Form");
	}

	public ButtonCampoForm(String html) {
		super(html);
		addStyleDependentName("Form");
	}
}