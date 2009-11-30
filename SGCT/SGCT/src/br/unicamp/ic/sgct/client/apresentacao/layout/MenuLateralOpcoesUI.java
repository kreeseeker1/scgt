package br.unicamp.ic.sgct.client.apresentacao.layout;

import br.unicamp.ic.sgct.client.SisGesConfTec;
import br.unicamp.ic.sgct.client.apresentacao.ScreenUtils;
import br.unicamp.ic.sgct.client.apresentacao.recursos.ImagensResource;
import br.unicamp.ic.sgct.client.apresentacao.widgets.ScreenTreeItem;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * Constroi o menu lateral da aplicacao formado de arvore de opcoes do menu (itens representado funcionalidades do sistema).
 * Ao ser escolhida uma funcionalidade eh acionada a aplicacao que representa esta e a tela desta eh renderizada na parte central
 * da tela do menu (PaginaConteudoUI).  
 */
public class MenuLateralOpcoesUI extends Composite implements
		SelectionHandler<TreeItem> {

	/**
	 * Widget composto de TreeItems que podem ser expandidos e selecionados
	 */
	private Tree tree;

	/**
	 * 
	 * @param imgRsrc
	 */
	public MenuLateralOpcoesUI(ImagensResource imgRsrc) {
		tree = new Tree();
		tree.addSelectionHandler(this);

		// Item About
/*		ScreenTreeItem itemAbout = new ScreenTreeItem(
				"Sobre a confer\u00eancia", imgRsrc.noimage(),
				SisGesConfTec.SOBRE_SCREEN);
		tree.addItem(itemAbout);
		itemAbout.setState(true);*/

		/**
		// Item Submissao
		TreeItem itemSubmissao = new TreeItem( ScreenUtils.gerarHtmlParaItem(imgRsrc.folder(),
				"Submiss\u00e3o de trabalhos") );
		tree.addItem(itemSubmissao);

		// Sub-itens
		adicionarSubItem(itemSubmissao, null, ScreenUtils
				.gerarHtmlParaItem("Trabalho t\u00e9cnico"), imgRsrc.noimage());
		adicionarSubItem(itemSubmissao, null, ScreenUtils
				.gerarHtmlParaItem("Relato de experi\u00eancia"), imgRsrc
				.noimage());
		itemSubmissao.setState(true);*/

		// Item Comite
/*		ScreenTreeItem itemComite = new ScreenTreeItem("Comit\u00ea",
				imgRsrc.noimage(), SisGesConfTec.SOBRE_SCREEN);
		tree.addItem(itemComite);
		itemComite.setState(true);*/
		
		/**
		// Sub-itens
		adicionarSubItem(itemComite, null, ScreenUtils
				.gerarHtmlParaItem("Organizador"), imgRsrc.noimage());
		adicionarSubItem(itemComite, null, ScreenUtils
				.gerarHtmlParaItem("Programa"), imgRsrc.noimage());
		adicionarSubItem(itemComite, null, ScreenUtils
				.gerarHtmlParaItem("Ind\u00fastria"), imgRsrc.noimage());
		itemComite.setState(true);*/

		// Item Local e inscricoes
/*		ScreenTreeItem itemDatas = new ScreenTreeItem("Local e datas importantes",
				imgRsrc.noimage(), SisGesConfTec.SOBRE_SCREEN);
		tree.addItem(itemDatas);
		itemDatas.setState(true);*/
		
		// Item PRECONF
		TreeItem itemPreconf = new TreeItem(ScreenUtils.gerarHtmlParaItem(
				//imgRsrc.folder(), "Pr\u00e9-Confer\u00eancia"));
				imgRsrc.folder(), "Inscri\u00e7\u00f5es"));
		tree.addItem(itemPreconf);

		ScreenTreeItem itemConferencias = new ScreenTreeItem(
				"Lista de eventos", imgRsrc.noimage(),
				SisGesConfTec.CONFERENCIA_SCREEN);
		itemPreconf.addItem(itemConferencias);		

		ScreenTreeItem itemInscr = new ScreenTreeItem("Realizar inscri\u00e7\u00e3o", imgRsrc
				.noimage(), SisGesConfTec.INSCRICAO_SCREEN);
		itemPreconf.addItem(itemInscr);

		ScreenTreeItem itemCancelar = new ScreenTreeItem(
				"Cancelar inscri\u00e7\u00e3o", imgRsrc.noimage(),
				SisGesConfTec.CANCELAR_INSCRICAO_SCREEN);
		itemPreconf.addItem(itemCancelar);

		itemPreconf.setState(true);
		initWidget(tree);
	}


	/**
	 * Simplifica adicao de sub-itens da arvore de menu que possuem icones atachados.
	 * 
	 * @param itemRaiz
	 * 	          item para o qual sera adicionado um sub-item
	 * 
 	 * @param userObject (opcional)
 	 * 			  objeto (Widget ou HTML) associado ao sub-item conforme necessidade de montagem da PaginaConteudoUI
	 * 
	 * @param titulo
	 *
	 * @param imageProto
	 *			  AbstractImagePrototype
	 *
	 * @return TreeItem 
	 */
	private TreeItem adicionarSubItem(TreeItem itemRaiz, Object userObject,
			String titulo, AbstractImagePrototype imageProto) {

		TreeItem item = new TreeItem( ScreenUtils.gerarHtmlParaItem(imageProto, titulo) );
		item.setUserObject(userObject);

		itemRaiz.addItem(item);

		return item;
	}

	/**
	 * Tratamento evento de selecao de itens do menu
	 */
	public void onSelection(SelectionEvent<TreeItem> event) {
		if ( event.getSelectedItem() instanceof ScreenTreeItem ) {
			int selectedIndex = ((ScreenTreeItem) event.getSelectedItem()).getScreenIndex();

			SisGesConfTec.instance().showScreen( selectedIndex );
		}
	}
}