package br.unicamp.ic.sgct.client.apresentacao.ucs;

import org.gwtiger.client.screen.BaseScreen;
import org.gwtiger.client.widget.field.PasswordFieldWidget;
import org.gwtiger.client.widget.field.TextFieldWidget;

import br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento.CancelamentoListener;
import br.unicamp.ic.sgct.client.apresentacao.widgets.ButtonCampoForm;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.to.PessoaTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CancelamentoScreen extends BaseScreen {
	private final String MSG_REQUIRED_ERR = "Campo(s) obrigatorio(s) n\u00e3o preenchido(s)!";
	
	private UsuarioTO usuario = null;
	private CancelamentoListener controladora;

/*    private StackPanel panelTabelas = new StackPanel();*/
	private FlexTable tblUsuario = new FlexTable();

	//dados usuario
	private TextFieldWidget txtbEmail = null;
	private PasswordFieldWidget txtbSenha = null;

	/*private ValidatePanel panelValidate = new ValidatePanel();*/	

	/**
	 * 
	 * @param usuario
	 * @param controladora
	 */
	public CancelamentoScreen(UsuarioTO usuario, final CancelamentoListener controladora) {
		super();
		this.controladora = controladora;

		this.usuario = usuario;
		
/*		ScrollPanel panelScroll = new ScrollPanel();
		panelScroll.setAlwaysShowScrollBars(true);*/		

		VerticalPanel panelGeral = new VerticalPanel();
		panelGeral.setWidth("100%");
		panelGeral.setSpacing(10);
        panelGeral.add( controladora.getMensagemWidget() );
/*        panelGeral.add(panelTabelas);
	
        panelTabelas.setWidth("100%");*/
        txtbEmail = new TextFieldWidget("E-mail");
        txtbEmail.setRequired(true);
        /*txtbEmail.setFocus(true);*/ //TODO: recompilar lib      
        tblUsuario.setWidget(0, 0, txtbEmail);        

        txtbSenha = new PasswordFieldWidget("Senha");
        txtbSenha.setRequired(true);
        tblUsuario.setWidget(1, 0, txtbSenha);
	
		tblUsuario.setCellSpacing(05);
		tblUsuario.setCellPadding(05);
/*		panelTabelas.add(tblUsuario, "Cancelamento de inscri\u00e7\u00e3o");*/
		
		ButtonCampoForm btnConfirmar = new ButtonCampoForm("<i>Confirmar cancelamento!</i>");
		ButtonCampoForm btnLimpar = new ButtonCampoForm("<i>Limpar dados...</i>");
	
        HorizontalPanel panelBtn = new HorizontalPanel();
        panelBtn.add(btnConfirmar);
        panelBtn.add(btnLimpar);
        panelBtn.setSpacing(6);

        panelGeral.add(tblUsuario);

        panelGeral.add(panelBtn);

        panelGeral.setCellWidth(panelBtn, "100%");

        String width  = String.valueOf( panelGeral.getOffsetWidth() + 5);
		String height = String.valueOf( panelGeral.getOffsetHeight() + 5);
/*		panelScroll.setSize(width + "px", height + "px");
		panelScroll.add(panelGeral);
*/	
		//Acao cancelar
		btnConfirmar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
            }
        });
			
	
		btnLimpar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				txtbSenha.clear();
				txtbEmail.clear();

				if ( controladora.getMensagemWidget() instanceof MensagemUI ) {
					controladora.getMensagemWidget().setMensagemSucesso("");
					controladora.getMensagemWidget().setMensagemErro("");
					controladora.getMensagemWidget().setVisible(false);
				}
			}
        });
			
	///*		txtbConfirmacao.addKeyUpHandler(new KeyUpHandler() {
	//			public void onKeyUp(KeyUpEvent event) {
	//				txtbNome.setFocus(true);
	//			}
	//		});
	//
	//		txtbConfirmacao.addKeyPressHandler(new KeyPressHandler() {
	//			public void onKeyPress(KeyPressEvent event) {
	//				if (event.isControlKeyDown())
	//					txtbNome.setFocus(true);
	//			}
	//		});
	//*/

		this.initWidget(panelGeral);

	}
	
	/*
	 * Direciona para o servidor processamento dos dados coletados
	 */
	private boolean cancelar() {
		boolean valid = true;
		
		valid = txtbSenha.validate();
		valid = txtbEmail.validate() && valid;

		if (!valid) {
			this.controladora.getMensagemWidget().setMensagemErro(MSG_REQUIRED_ERR);			
			return valid;
		}
        else {
        	this.usuario.setEmail(txtbEmail.getText());
			this.usuario.setSenha(txtbSenha.getText());

			PessoaTO pessoa = new PessoaTO();
			pessoa.setNome("");
			pessoa.setSobreNome("");
			pessoa.setFoneResidencial("");
			pessoa.setCelular("");

			this.usuario.setPessoa(pessoa);

			this.controladora.cancelar(usuario);
        }
        return true;
	}
}