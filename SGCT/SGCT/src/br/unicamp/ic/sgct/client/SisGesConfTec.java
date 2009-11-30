package br.unicamp.ic.sgct.client;

import org.gwtiger.client.screen.BaseEntryScreen;
import org.gwtiger.client.screen.BaseScreen;

import br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento.CancelamentoListenerImpl;
import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaListenerImpl;
import br.unicamp.ic.sgct.client.aplicacao.ucs.infogeral.InfoGeralListenerImpl;
import br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao.InscricaoListenerImpl;
import br.unicamp.ic.sgct.client.apresentacao.layout.MenuLateralUI;
import br.unicamp.ic.sgct.client.apresentacao.layout.PainelSuperiorUI;
import br.unicamp.ic.sgct.client.apresentacao.recursos.ImagensResource;
import br.unicamp.ic.sgct.client.apresentacao.ucs.CancelamentoScreen;
import br.unicamp.ic.sgct.client.apresentacao.ucs.ConferenciaScreen;
import br.unicamp.ic.sgct.client.apresentacao.ucs.InscricaoScreen;
import br.unicamp.ic.sgct.client.apresentacao.ucs.SobreScreen;
import br.unicamp.ic.sgct.client.apresentacao.widgets.MensagemUI;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SisGesConfTec extends BaseEntryScreen implements ResizeHandler {
	private static SisGesConfTec _instance = new SisGesConfTec();

	private ImagensResource imgRsrc;
	private PainelSuperiorUI topPanel;
	private MenuLateralUI menuPanel;

	public static final int INSCRICAO_SCREEN = 1;
	public static final int LOGIN_SCREEN = 2;
	public static final int SOBRE_SCREEN = 3;
	public static final int CANCELAR_INSCRICAO_SCREEN = 4;
	public static final int CONFERENCIA_SCREEN = 5;

	private String moduleRelativeURL;

	private SisGesConfTec() {
        // Use a module-relative URLs to ensure that this client code can find
		// its context path, even when the URL changes (as might happen when you
		// deploy this as a webapp under an external servlet container).
		this.moduleRelativeURL = GWT.getModuleBaseURL();
	}

	public static SisGesConfTec instance() {
		if (_instance == null) {
			_instance = new SisGesConfTec();
		}
		return _instance;
	}

	/**
	 * All new screens that are defined should be setup here
	 */
	@Override
	protected BaseScreen getScreenFirstTime(int index) {
		BaseScreen screen = null;
		switch (index) {
		case INSCRICAO_SCREEN:
			screen = new InscricaoScreen(new UsuarioTO(),
					new InscricaoListenerImpl(new MensagemUI()),
					new ConferenciaListenerImpl(new MensagemUI()));
			break;
		case CANCELAR_INSCRICAO_SCREEN:
			screen = new CancelamentoScreen(new UsuarioTO(), new CancelamentoListenerImpl( new MensagemUI() ));
			break;
		case SOBRE_SCREEN:
			screen = new SobreScreen(new InfoGeralListenerImpl( new MensagemUI() ));
			break;
		case CONFERENCIA_SCREEN:
			screen = new ConferenciaScreen( new ConferenciaListenerImpl( new MensagemUI() ) );
			break;
		default:
			screen = new InscricaoScreen(new UsuarioTO(),
					new InscricaoListenerImpl(new MensagemUI()),
					new ConferenciaListenerImpl(new MensagemUI()));
			break;
		}
		return screen;
	}

	/**
	 * This method constructs the application user interface by instantiating
	 * controls and hooking up event handler.
	 */
	public void onModuleLoad() {
		_instance = this;

		// Instantiate an application-level image bundle. This object will
		// provide programmatic access to all the images needed by widgets.
		imgRsrc = GWT.create(ImagensResource.class);

		topPanel = new PainelSuperiorUI(imgRsrc);
		menuPanel = new MenuLateralUI(imgRsrc);

		DockPanel outerPanel = new DockPanel();
		outerPanel.add(topPanel, DockPanel.NORTH);
		outerPanel.add(menuPanel, DockPanel.WEST);

		// IMPORTATE: getMainPanel() sera uma das GUIs do tipo BaseScreen criadas no projeto
		// e sera exibida na parte direita da tela com ajuda dos metodos getScreenFirstTime(int) e
		// showScreen() conforme hierarquia de heranca estabelecida p/ subclasses de BaseEntryScreen e
		// BaseScreen
		outerPanel.add(getMainPanel(), DockPanel.CENTER);

		outerPanel.setWidth("100%");
		outerPanel.setSpacing(4);
		outerPanel.setCellWidth(getMainPanel(), "100%");

		// Hook the window resize event, so that we can adjust the UI.
		Window.addResizeHandler(this);

		// Get rid of scrollbars, and clear out the window's built-in margin,
		// because we want to take advantage of the entire client area.
		Window.enableScrolling(false);
		Window.setMargin("0px");

		// Finally, add the outer panel to the RootPanel, so that it will be
		// displayed.
		RootPanel.get().clear();
		RootPanel.get().add(outerPanel);

		// Call the window resized handler to get the initial sizes setup. Doing
		// this in a deferred command causes it to occur after all widgets'
		// sizes have been computed by the browser.
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				onWindowResized(Window.getClientWidth(), Window
						.getClientHeight());
			}
		});

		onWindowResized(Window.getClientWidth(), Window.getClientHeight());
	}

	public void onResize(ResizeEvent event) {
		onWindowResized(event.getWidth(), event.getHeight());
	}

	public void onWindowResized(int width, int height) {
		// Adjust the menu panel and detail area to take up the available
		// room in the window.
		int shortcutHeight = height - menuPanel.getAbsoluteTop() - 8;
		if (shortcutHeight < 1) {
			shortcutHeight = 1;
		}
		menuPanel.setHeight(shortcutHeight + "px");
	}

	public String getModuleRelativeURL() {
		return moduleRelativeURL;
	}

	public void setModuleRelativeURL(String moduleRelativeURL) {
		this.moduleRelativeURL = moduleRelativeURL;
	}

	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
}