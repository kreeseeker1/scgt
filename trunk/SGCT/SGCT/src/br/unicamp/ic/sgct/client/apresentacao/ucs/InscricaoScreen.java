package br.unicamp.ic.sgct.client.apresentacao.ucs;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.gwtiger.client.screen.BaseScreen;
import org.gwtiger.client.widget.HoverGridWidget;
import org.gwtiger.client.widget.field.DateFieldWidget;
import org.gwtiger.client.widget.field.MaskTextFieldWidget;
import org.gwtiger.client.widget.field.PasswordFieldWidget;
import org.gwtiger.client.widget.field.TextFieldWidget;

import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaListener;
import br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao.InscricaoListener;
import br.unicamp.ic.sgct.client.apresentacao.widgets.ButtonCampoForm;
import br.unicamp.ic.sgct.client.apresentacao.widgets.LabelCampoForm;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;
import br.unicamp.ic.sgct.client.dominio.to.PessoaTO;
import br.unicamp.ic.sgct.client.dominio.to.SessaoTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class InscricaoScreen extends BaseScreen implements TableListener {
	private String strDataPagto;
	private Date data;
	private DateFieldWidget dtField;
	private DateTimeFormat _dtTimeFormat = DateTimeFormat.getFormat("dd/MM/yyy");

	private VerticalPanel panelGeral = new VerticalPanel();
    DecoratedTabPanel panelTab = new DecoratedTabPanel();
    HorizontalPanel panelBtn = new HorizontalPanel();

	private UsuarioTO usuario = null;    

	//recursos Inscricao UI
    private static final String MSG_REQUIRED_ERR = "Campo(s) obrigatorio(s) n\u00e3o preenchido(s)!";
	private static final String MSG_PASSWD_ERR = "Senha e sua confirma\u00e7\u00e3o n\u00e3o conferem!!";
	private static final String MSG_PASSWD_SIZE_ERR = "Senha fornecida n\u00e3o \u00e9 v\u00e1lida !";
	private static final String MSG_NOME_ERR = "Nome informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_EMAIL_ERR = "Email informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_SOBRENOME_ERR = "Sobrenome informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_CELULAR_ERR = "Telefone celular informado n\u00e3o \u00e9 v\u00e1lido!";
	private static final String MSG_FONE_ERR = "Telefone residencial informado n\u00e3o \u00e9 v\u00e1lido";
	private static final String MSG_DATA_ERR = "Data de efetiva\u00e7\u00e3o do pagamento deve ser anterior ao dia de hoje!";
	
	private InscricaoListener controladora;

	private FlexTable tblUsuario = new FlexTable();

	//dados usuario
	private TextFieldWidget txtbEmail = null;
	private PasswordFieldWidget txtbSenha = null;
	private PasswordFieldWidget txtbConfirmacao = null;

	//dados pessoais
	private TextFieldWidget txtbNome = null;
	private TextFieldWidget txtbSobreNome = null;
	private MaskTextFieldWidget txtbFone = null;
	private MaskTextFieldWidget txtbCelular = null;

	//recursos Conferencia UI
	private Grid gridInfoAdicional = null;
	private VerticalPanel panel = new VerticalPanel();
	private HoverGridWidget gridEventos = new HoverGridWidget();
	private VerticalPanel panelDetalhesConf = new VerticalPanel();

	private List<ConferenciaTO> conferencias;
	private ConferenciaListener ctrlConferencia;

	/**
	 * 
	 * @param usuario
	 * @param controladora
	 * @param conferenciaCtrl
	 */
	public InscricaoScreen(UsuarioTO usuario, final InscricaoListener controladora,
			final ConferenciaListener ctrlConferencia) {
		super();
		strDataPagto = _dtTimeFormat.format( new Date() );

		this.usuario = usuario;
		this.controladora = controladora;
		this.ctrlConferencia = ctrlConferencia;

        panelGeral.add( controladora.getMensagemWidget() );
        panelGeral.add( ctrlConferencia.getMensagemWidget() );

        montarConferenciaUI();
		montarInscricaoUI();

	    HTML strTab1Title = new HTML("Dados confer\u00eancia");
	    panelTab.add(panel, strTab1Title);

	    HTML strTab2Title = new HTML("Inscri\u00e7\u00e3o");
	    panelTab.add(tblUsuario, strTab2Title);

	    panelTab.selectTab(0);
	    panelTab.setWidth("750px");
	    panelTab.setAnimationEnabled(true);

	    panelGeral.add( panelTab );

		ButtonCampoForm btnConfirmar = new ButtonCampoForm("<i>Confirmar inscri\u00e7\u00e3o!</i>");
		ButtonCampoForm btnLimpar = new ButtonCampoForm("<i>Limpar dados...</i>");

        panelBtn.add(btnConfirmar);
        panelBtn.add(btnLimpar);
        panelBtn.setSpacing(6);

        panelGeral.add(panelBtn);
        panelGeral.setCellWidth(panelBtn, "85%");
		panelGeral.setWidth("95%");
		panelGeral.setSpacing(6);

        String width  = String.valueOf( panelGeral.getOffsetWidth() ) + "px";

		ScrollPanel panelScroll = new ScrollPanel();
		panelScroll.add(panelGeral);
		panelScroll.setSize(width, "5px");
		panelScroll.setAlwaysShowScrollBars(true);
		panelScroll.ensureVisible( panelGeral );

		//Acao inscrever
		btnConfirmar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				inscrever();
            }
        });

		//Acao limpar dados form.
		btnLimpar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				txtbConfirmacao.clear();
				txtbSenha.clear();
				txtbEmail.clear();
				txtbNome.clear();
				txtbSobreNome.clear();
				txtbFone.clear();
				txtbCelular.clear();
				dtField.clear();

				if ( controladora.getMensagemWidget() instanceof MensagemUI ) {
					controladora.getMensagemWidget().setMensagemSucesso("");
					controladora.getMensagemWidget().setMensagemErro("");
					controladora.getMensagemWidget().setVisible(false);
				}
			}
        });

		this.initWidget( panelScroll );
	}

	/**
	 * montarInscricaoUI 
	 */
	private void montarInscricaoUI() {
        txtbEmail = new TextFieldWidget("E-mail");
        txtbEmail.setRequired(true);
        tblUsuario.setWidget(0, 0, txtbEmail);        

        txtbSenha = new PasswordFieldWidget("Senha");
        txtbSenha.setRequired(true);
        tblUsuario.setWidget(1, 0, txtbSenha);

        txtbConfirmacao = new PasswordFieldWidget("Confirma\u00e7\u00e3o");
        txtbConfirmacao.setRequired(true);
        tblUsuario.setWidget(1, 1, txtbConfirmacao);

        txtbNome = new TextFieldWidget("Nome");
		txtbNome.setRequired(true);
		tblUsuario.setWidget(2, 0, txtbNome);

		txtbSobreNome = new TextFieldWidget("Sobrenome");
		txtbSobreNome.setRequired(true);
		tblUsuario.setWidget(2, 1, txtbSobreNome);

		txtbFone = new MaskTextFieldWidget("Fone", "(###) ####-####");
		tblUsuario.setWidget(3, 0, txtbFone);

		txtbCelular = new MaskTextFieldWidget("Celular", "(###) ####-####");
		tblUsuario.setWidget(3, 1, txtbCelular);

	    dtField = new DateFieldWidget("Data efetiva\u00e7\u00e3o pagamento inscri\u00e7\u00e3o: ");
	    dtField.setRequired(true);
	    dtField.setDateFormat("dd/MM/yyyy");
	    dtField.setMask("##/##/####");
	    dtField.setEnabled(false);

		DatePicker dtPicker = new DatePicker();
	    dtPicker.setValue(new Date(), true);

		DateBox dtBox = new DateBox(dtPicker, null, new DateBox.DefaultFormat(_dtTimeFormat));
		dtBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				data = event.getValue();
				strDataPagto = _dtTimeFormat.format(data);
				dtField.setText(strDataPagto);
			}
		});

		tblUsuario.setWidget(4, 0, dtField);
		tblUsuario.setWidget(4, 1, dtBox);

		tblUsuario.setCellSpacing(2);
		tblUsuario.setCellPadding(2);
	}

	/*
	 * Direciona para o servidor processamento dos dados coletados
	 */
	private boolean inscrever() {
		boolean valid = true;

		valid = txtbSenha.validate();
		valid = txtbConfirmacao.validate() && valid;
		valid = txtbEmail.validate() && valid;
		valid = txtbNome.validate() && valid;
		valid = txtbSobreNome.validate() && valid;
		valid = dtField.validate() && valid;
		valid = ( strDataPagto != null ? true : false ) && valid;

		if (!valid) {
			this.controladora.getMensagemWidget().setMensagemErro(MSG_REQUIRED_ERR);			
			return valid;
		}

	    //Validacao senha
		if( !txtbSenha.getText().equals( txtbConfirmacao.getText() )) {
            this.controladora.setMensagemErro(MSG_PASSWD_ERR);
            return false;
        }
		
	    //Senha: tamanho minimo igual a 4 caracteres e maximo igual a 8 caracteres, no minimo um caracter especial, no maximo uma letra
		//maiuscula, campo obrigatorio
//	    if( txtbSenha.getText().length() < 4 || txtbSenha.getText().length() > 8) {
//            this.controladora.setMensagemErro(MSG_PASSWD_SIZE_ERR);
//            
//            return false;
//        }

	    
	    // Validador de nomes
	    // Nome: tamanho minimo igual a 2 caracteres e tamanho maximo igual a 
	    // 15 caracteres, apenas letras
//	    if ( txtbNome.getText().length() < 2 || txtbNome.getText().length() > 15 ){
//        	this.controladora.setMensagemErro(MSG_NOME_ERR);
//        	
//        	return false;
//        }

        
	    // Validador de sobrenomes
        // Sobrenome: tamanho maximo igual a 15 caracteres, apenas letras, 
        // campo obrigatorio
	    // TODO Falha aqui, esquece de tratar A,z
//	    if ( txtbSobreNome.getText().length() > 15 ) {
//        	this.controladora.setMensagemErro(MSG_SOBRENOME_ERR);
//        	
//        	return false;
//        }

	    // Validador de emails
        // Email: tamanho maximo igual a 30 caracteres, apenas emails validos;
	    // TODO Falha aqui, so procura pela @
//	    if ( txtbEmail.getText().length() > 30 || !txtbEmail.getText().contains("@")) {
//	    		//|| (!(Pattern.compile("(\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6})").
//        	this.controladora.setMensagemErro(MSG_EMAIL_ERR);
//        	
//        	return false;
//	    }

	    // Validador de fone residencial
        // Fone residencial: tamanho maximo igual a 18 caracteres, sem letras
	    // TODO Falha aqui, nao aceita caracteres especiais
//	    if ( txtbFone.getText().length() > 18 ) {
//	    	this.controladora.setMensagemErro(MSG_FONE_ERR);
//	        
//	    	return false;
//	    }


	    // Validador de fone celular
	    // Fone celular: tamanho maximo igual a 18 caracteres, sem letras
//	    if ( txtbCelular.getText().length() >= 18 ) {
//	    	this.controladora.setMensagemErro(MSG_CELULAR_ERR);
//	     
//	    	return false;
//   	    }

	    // Validador da data de pgto
	    Date dataAtual = new Date(System.currentTimeMillis());
	    Date dataPgto = data;
	    if ( dataPgto.after(dataAtual) ) {
	    	this.controladora.setMensagemErro(MSG_DATA_ERR);
	     
	    	return false;
   	    }
	    else {
        	this.usuario.setEmail(txtbEmail.getText());
			this.usuario.setSenha(txtbSenha.getText());
			this.usuario.setInscricaoAtiva("S");
			
			if (dtField.getText() != null && !"".equals( dtField.getText() )) {
				// System.out.println("strDataPagto: " + strDataPagto);
				// System.out.println("dtField: " + dtField.getText() );
				this.usuario.setDataEfetivacaoPagto( _dtTimeFormat.parse( strDataPagto ));
			}

			PessoaTO pessoa = new PessoaTO();
			pessoa.setNome(txtbNome.getText());
			pessoa.setSobreNome(txtbSobreNome.getText());
			pessoa.setFoneResidencial(txtbFone.getText());
			pessoa.setCelular(txtbCelular.getText());

			this.usuario.setPessoa(pessoa);

			this.controladora.inscrever(usuario);
        }
        return true;
	}

	/**
	 * 
	 */
	private void montarConferenciaUI() {
		gridEventos.setHoverStyleName("grid-hover");
		gridEventos.addTableListener(this);

		panel.setWidth("100%");
		panel.setSpacing(8);
		panel.add( new LabelCampoForm("Selecione a confer\u00eancia abaixo para mais informa\u00e7\u00f5es:") );
		panel.add( gridEventos );
		panel.setCellWidth(gridEventos, "100%");

		panelDetalhesConf.setVisible(false);
		panel.add( panelDetalhesConf );

		try {
			ctrlConferencia.setBaseScreen(this);

			//
			ctrlConferencia.listarConferencias(panelBtn);

			montarGridConferencias();
		}
		catch (InfraException e) {
			ctrlConferencia.setMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
		catch (Exception e) {
			ctrlConferencia.setMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
	}

	/**
	 * setConferencias 
	 */
	public void setConferencias(List<ConferenciaTO> conferencias) {
		this.conferencias = conferencias;
		//
		montarGridConferencias();
	}

	/**
	 * onCellClicked 
	 */
	public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
		ConferenciaTO to = conferencias.get( row-1 );

		panelDetalhesConf.clear();
		montarGridInfoAdicional(to);

		showLoading(true);

/*		List<SessoesConferenciaTO> lstSessoesTO = to.getSessoesConferencia();

		if (lstSessoesTO != null && !lstSessoesTO.isEmpty()) {
			System.out.println("sessoes conf. size= " + lstSessoesTO.size() );

			for (SessoesConferenciaTO sessoesConfTO : lstSessoesTO) {
				panelDetalhesConf.add( new CheckBox( sessoesConfTO.getData() + " - " + sessoesConfTO.getTema() ) );
			}
		}*/

		List<SessaoTO> lstSessoesTO = to.getSessoesConferencia();

		if (lstSessoesTO != null && !lstSessoesTO.isEmpty()) {
			System.out.println("sessoes conf. size= " + lstSessoesTO.size() );

			
			for (SessaoTO sessoesConfTO : lstSessoesTO) {
				panelDetalhesConf.add( new CheckBox( sessoesConfTO.getData() + " - " + sessoesConfTO.getTema() ) );
			}
		}

		
		panelDetalhesConf.setVisible(true);
		showLoading(false);
	}

    /**
     * montarGridConferencias 
     */
    private void montarGridConferencias() {
		//adequa estado da UI
		gridEventos.clearGrid();
		ctrlConferencia.getMensagemWidget().limparMensagem();

		if (conferencias != null && !conferencias.isEmpty()) {
			setHeader();
			RowFormatter rowFormatter = gridEventos.getRowFormatter();

			ConferenciaTO conferencia;
			for (int row = 0; row < conferencias.size(); row++) {
				conferencia = conferencias.get(row);

				if (row+1 % 2 == 0) {
					rowFormatter.setStyleName(row+1, HoverGridWidget.STYLE_ROW_EVEN);
				}
				else {
					rowFormatter.setStyleName(row+1, HoverGridWidget.STYLE_ROW_ODD);
				}

				gridEventos.setId(row+1, String.valueOf(conferencia.getId()));
				gridEventos.setText(row+1, 1, conferencia.getTitulo());

				gridEventos.setText(row+1, 2, _dtTimeFormat.format( conferencia.getInicio() ));
				gridEventos.setText(row+1, 3, _dtTimeFormat.format( conferencia.getTermino() ));
			}
		}
		else {
			ctrlConferencia.setMensagemErro("N\u00e3o h\u00e1 confer\u00eancias agendadas.");
		}
    }

	/**
	 * montarGridInfoAdicional 
	 */
	private void montarGridInfoAdicional(ConferenciaTO to) {
		if (to != null) {
//			gridInfoAdicional = new Grid(3, 2);
//			gridInfoAdicional.setBorderWidth(0);
//			gridInfoAdicional.setCellSpacing(4);
//			gridInfoAdicional.setCellPadding(6);
			gridInfoAdicional = new Grid();
			gridInfoAdicional.setTitle("Detalhes confer\u00eancia selecionada...");
//
//			gridInfoAdicional.setWidget(0, 0, new LabelCampoForm("Descri\u00e7\u00e3o"));
//			gridInfoAdicional.setWidget(1, 0, new LabelCampoForm("In\u00edcio chamada trabalhos"));
//			gridInfoAdicional.setWidget(2, 0, new LabelCampoForm("T\u00e9rmino chamada trabalhos"));
//
//			gridInfoAdicional.setWidget(0, 1, new LabelCampoForm(to.getDescricao() != null ? to.getDescricao().trim() : ""));
//			gridInfoAdicional.setWidget(1, 1, new LabelCampoForm( _dtTimeFormat.format( to.getInicioChamadaTrabalhos() )));
//			gridInfoAdicional.setWidget(2, 1, new LabelCampoForm( _dtTimeFormat.format( to.getTerminoChamadaTrabalhos() )));

			
//			if (to.getSessoesConferencia() != null ) {
//				
//				int tGrid = to.getSessoesConferencia().size();
//				
//				for (int i = 0; i < to.getSessoesConferencia().size(); i++) {
//					SessaoTO sessaoTO = to.getSessoesConferencia().get(i);
//					
//					gridInfoAdicional = new Grid(3, 2);
//					gridInfoAdicional.setBorderWidth(0);
//					gridInfoAdicional.setCellSpacing(4);
//					gridInfoAdicional.setCellPadding(6);
//					gridInfoAdicional.setTitle("Detalhes confer\u00eancia selecionada...");
//					
//					gridInfoAdicional.setWidget(0, 0, new LabelCampoForm("Descri\u00e7\u00e3o"));
//					gridInfoAdicional.setWidget(1, 0, new LabelCampoForm("In\u00edcio chamada trabalhos"));
//					gridInfoAdicional.setWidget(2, 0, new LabelCampoForm("T\u00e9rmino chamada trabalhos"));
//					
//					
//					gridInfoAdicional.setWidget(0, 1, new LabelCampoForm(sessaoTO.getTema().trim()));
//					gridInfoAdicional.setWidget(1, 1, new LabelCampoForm( _dtTimeFormat.format( to.getInicioChamadaTrabalhos() )));
//					gridInfoAdicional.setWidget(2, 1, new LabelCampoForm( _dtTimeFormat.format( to.getTerminoChamadaTrabalhos() )));
//					
//				}
//				
//			}
			
			
			
			panelDetalhesConf.add(gridInfoAdicional);
		}
	}

    /**
     * setHeader
     */
	private void setHeader() {
		gridEventos.setWidth(500 + "px");
		gridEventos.addHeader(1, "  T\u00edtulo  ");
		gridEventos.addHeader(2, "  In\u00edcio  ");
		gridEventos.addHeader(3, "  T\u00e9rmino  ");
	}
}