package br.unicamp.ic.sgct.client.apresentacao.widgets;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Label;

public class LabelCampoForm extends Label {

	public LabelCampoForm() {
		super();
		addStyleDependentName("Form");
	}

	public LabelCampoForm(Element element) {
		super(element);
		addStyleDependentName("Form");
	}

	public LabelCampoForm(String text, boolean wordWrap) {
		super(text, wordWrap);
		addStyleDependentName("Form");
	}

	public LabelCampoForm(String text) {
		super(text);
		addStyleDependentName("Form");
	}
}