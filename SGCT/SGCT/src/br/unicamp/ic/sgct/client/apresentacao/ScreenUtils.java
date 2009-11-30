package br.unicamp.ic.sgct.client.apresentacao;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public class ScreenUtils {

	/**
	 * Gera HTML para item de arvore do menu com icone atachado.
	 * 
	 * @param imageProto
	 *            AbstractImagePrototype
	 * @param titulo
	 * @return String (HTML resultante)
	 */
	public static String gerarHtmlParaItem(AbstractImagePrototype imageProto,
			String titulo) {
		return imageProto.getHTML() + " " + titulo;
	}

	/**
	 * Gera HTML para item de arvore do menu simples.
	 * 
	 * @param titulo
	 * 
	 * @return String
	 */
	public static String gerarHtmlParaItem(String titulo) {
		return "<i>" + titulo + "</i>";
	}
}