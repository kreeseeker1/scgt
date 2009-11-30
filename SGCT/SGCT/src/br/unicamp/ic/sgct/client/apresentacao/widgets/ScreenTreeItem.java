package br.unicamp.ic.sgct.client.apresentacao.widgets;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.TreeItem;

public class ScreenTreeItem extends TreeItem {
	private int itemIndex;

	/**
	 * 
	 * @param html - titulo do item de menu
	 * @param itemIndex - indice opcao do menu
	 */
	public ScreenTreeItem(String html, int itemIndex) {
		super( gerarHtmlParaItem(html) );
		this.itemIndex = itemIndex;
	}

	/**
	 * 
	 * @param html - titulo do item de menu
	 * @param imgPrototype 
	 * @param itemIndex - indice opcao do menu
	 */
	public ScreenTreeItem(String html, AbstractImagePrototype imgPrototype, int itemIndex) {
		super( gerarHtmlParaItem(imgPrototype, html) );
		this.itemIndex = itemIndex;
	}

	public int getScreenIndex() {
		return itemIndex;
	}

	/**
	 * Gera HTML para item de arvore do menu com icone atachado.
	 * 
	 * @param imageProto
	 *			  AbstractImagePrototype
	 * @param titulo
	 * @return String (HTML resultante)
	 */
	private static String gerarHtmlParaItem(AbstractImagePrototype imageProto, String titulo) {
		return imageProto.getHTML() + " " + titulo;
	}

	/**
	 * Gera HTML para item de arvore do menu simples.
	 * 
	 * @param titulo
	 * 
	 * @return String
	 */
	private static String gerarHtmlParaItem(String titulo) {
		return "<i>" + titulo + "</i>";
	}	
}