package br.unicamp.ic.sgct.client.apresentacao.ucs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.gwtiger.client.screen.BaseScreen;
import org.gwtiger.client.widget.HoverGridWidget;
import org.gwtiger.client.widget.field.PasswordFieldWidget;
import org.gwtiger.client.widget.field.TextFieldWidget;

import br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento.CancelamentoListener;
import br.unicamp.ic.sgct.client.apresentacao.widgets.ButtonCampoForm;
import br.unicamp.ic.sgct.client.apresentacao.widgets.LabelCampoForm;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.InscricaoTO;
import br.unicamp.ic.sgct.client.dominio.to.Inscricao_SessaoTO;
import br.unicamp.ic.sgct.client.dominio.to.PessoaTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CancelamentoScreen extends BaseScreen implements TableListener, ClickListener{
	private final String MSG_REQUIRED_ERR = "Campo(s) obrigatorio(s) n\u00e3o preenchido(s)!";
	
	private UsuarioTO usuario = null;
	private CancelamentoListener controladora;
	private FlexTable tblUsuario = new FlexTable();

	//dados usuario
	private TextFieldWidget txtbEmail = null;
	private PasswordFieldWidget txtbSenha = null;


	private VerticalPanel panelGeral = new VerticalPanel();
	private VerticalPanel panelDetalhesConf = new VerticalPanel();
	private HorizontalPanel panelBtn = new HorizontalPanel();
	
	private Grid gridInfoAdicional = null;
	private HoverGridWidget gridEventos = new HoverGridWidget();
	private List<Inscricao_SessaoTO> listInscricaoSessaoTO = new ArrayList<Inscricao_SessaoTO>();
	
	ButtonCampoForm btnPesquisar = new ButtonCampoForm("<i>Pesquisar!</i>");
	ButtonCampoForm btnConfirmar= new ButtonCampoForm("<i>Confirmar cancelamento!</i>");
	ButtonCampoForm btnLimpar = new ButtonCampoForm("<i>Limpar dados...</i>");
	
	private List<InscricaoTO> listInscricao = new ArrayList<InscricaoTO>();
	
	/**
	 * 
	 * @param usuario
	 * @param controladora
	 */
	public CancelamentoScreen(UsuarioTO usuario, final CancelamentoListener controladora) {
		super();
		this.controladora = controladora;

		this.usuario = usuario;
		

		panelGeral.setWidth("100%");
		panelGeral.setSpacing(10);
        panelGeral.add( controladora.getMensagemWidget() );
        txtbEmail = new TextFieldWidget("E-mail");
        txtbEmail.setRequired(true);
        tblUsuario.setWidget(0, 0, txtbEmail);        

        txtbSenha = new PasswordFieldWidget("Senha");
        txtbSenha.setRequired(true);
        tblUsuario.setWidget(1, 0, txtbSenha);
        
		tblUsuario.setCellSpacing(05);
		tblUsuario.setCellPadding(05);
		panelBtn.add(btnPesquisar);
        panelBtn.add(btnLimpar);
        panelBtn.setSpacing(6);

        panelGeral.add(tblUsuario);
        
		panelDetalhesConf.setVisible(false);
		panelGeral.add( panelDetalhesConf );
        
        
        panelGeral.add(panelBtn);

        panelGeral.setCellWidth(panelBtn, "100%");

        String width  = String.valueOf( panelGeral.getOffsetWidth() + 5);
		String height = String.valueOf( panelGeral.getOffsetHeight() + 5);
		
		//Acao cancelar
		btnConfirmar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				cancelar();
            }
        });
			
	
		btnPesquisar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				pesquisar();
			}
        });
		
		btnLimpar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				txtbSenha.clear();
				txtbEmail.clear();
				panelDetalhesConf.clear();

				if ( controladora.getMensagemWidget() instanceof MensagemUI ) {
					controladora.getMensagemWidget().setMensagemSucesso("");
					controladora.getMensagemWidget().setMensagemErro("");
					controladora.getMensagemWidget().setVisible(false);
				}
			}
        });
		
		this.initWidget(panelGeral);

	}
	
	private void pesquisar(){
		
		boolean valid = true;
		 
		valid = txtbSenha.validate();
		valid = txtbEmail.validate() && valid;
		
		this.usuario.setEmail(txtbEmail.getText());
		this.usuario.setSenha(txtbSenha.getText());
		
		try {
			
			this.controladora.setBaseScreen(this);
			
			this.controladora.listarInscricoes(usuario);
			
		} catch (InfraException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Direciona para o servidor processamento dos dados coletados
	 */
	private boolean cancelar(){
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
			this.usuario.setListInscricaoSessaoTO(listInscricaoSessaoTO);

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

	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		
		gridInfoAdicional = new Grid();
		gridInfoAdicional.setTitle("Detalhes confer\u00eancia selecionada...");
		
		panelGeral.add(gridInfoAdicional);
		
		showLoading(true);
		
		System.out.println("onCellClicked");
		
		createCheckBox( "TESTE", this ); 
		
		panelGeral.setVisible(true);
		showLoading(false);
		
	}

	public void onClick(Widget sender) {
		
		Iterator sessoesIterator = this.panelDetalhesConf.iterator();

		listInscricaoSessaoTO = new ArrayList<Inscricao_SessaoTO>();
		
		while (sessoesIterator.hasNext()) {
			
			CheckBox checkBox = (CheckBox) sessoesIterator.next();

			if (checkBox.isChecked()) {
				
				String[] string = checkBox.getText().split(" - ");
				Inscricao_SessaoTO inscricao_SessaoTO = new Inscricao_SessaoTO();
				inscricao_SessaoTO.setId(Long.parseLong(string[0]));
				
				listInscricaoSessaoTO.add(inscricao_SessaoTO);
			}
		}
		
	}
	
	private static CheckBox createCheckBox(String text, ClickListener listener) {
		
		CheckBox checkBox = new CheckBox(text);
		checkBox.setText(text);
		
		checkBox.addClickListener(listener);
		
		return checkBox;
	}
	
	public void setInscricoes(List<InscricaoTO> listInscricao) {
		this.listInscricao = listInscricao;
		
		panelBtn.clear();
        panelBtn.add(btnConfirmar);
        panelBtn.add(btnLimpar);
        panelBtn.setSpacing(6);
		
        gridEventos.setHoverStyleName("grid-hover");
		gridEventos.addTableListener(this);

		panelGeral.setWidth("100%");
		panelGeral.setSpacing(8);
		panelGeral.add( new LabelCampoForm("Selecione a confer\u00eancia que deseja cancelar:") );
		panelGeral.add( gridEventos );
		panelGeral.setCellWidth(gridEventos, "100%");
        
		panelDetalhesConf.setVisible(false);
		panelGeral.add( panelDetalhesConf );
		
		panelDetalhesConf.clear();
		
		for (InscricaoTO inscricaoTO : listInscricao) {
			
			for (Inscricao_SessaoTO inscricao_SessaoTO : inscricaoTO.geInscricao_Sessao()) {
				panelDetalhesConf.add( createCheckBox(inscricao_SessaoTO.getId() + " - " + inscricao_SessaoTO.getSessao().getTema(), this));
			}
		}
		
		panelDetalhesConf.setVisible(true);
		
		panelGeral.add(panelBtn);
		
	}
}