package br.unicamp.ic.sgct.client.apresentacao.ucs;

import java.util.List;

import org.gwtiger.client.screen.BaseScreen;
import org.gwtiger.client.widget.HoverGridWidget;

import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaListener;
import br.unicamp.ic.sgct.client.apresentacao.widgets.LabelCampoForm;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

public class ConferenciaScreen extends BaseScreen implements TableListener {
	private Grid grid = null;
	private VerticalPanel panel = new VerticalPanel();
	private HoverGridWidget resultGrid = new HoverGridWidget();
	private VerticalPanel panelDetalhesConf = new VerticalPanel();

	private DateTimeFormat _dtTimeFormat = DateTimeFormat.getFormat("dd/MM/yyy");	

	private List<ConferenciaTO> conferencias;
	private ConferenciaListener controladora;

	public ConferenciaScreen(ConferenciaListener controladora) {
		super();
		this.controladora = controladora;

		resultGrid.setHoverStyleName("grid-hover");
		resultGrid.addTableListener(this);

		panel.setWidth("100%");
		panel.setSpacing(8);

		panel.add( controladora.getMensagemWidget() );
		panel.add( new LabelCampoForm("Selecione a confer\u00eancia desejada") );
		panel.add( resultGrid );

		panel.setCellWidth(resultGrid, "100%");

		panelDetalhesConf.setVisible(false);
		panel.add( panelDetalhesConf );

		this.initWidget(panel);

		try {
			controladora.setBaseScreen(this);

			//
			controladora.listarConferencias();

			showLoading(true);

			montarGridConferencias();
		}
		catch (InfraException e) {
			this.controladora.setMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
		catch (Exception e) {
			this.controladora.setMensagemErro( e.getMessage() );
			e.printStackTrace();
		}
	}

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

		panelDetalhesConf.setVisible(true);

		showLoading(false);
	}

    /**
     * montarGridConferencias 
     */
    private void montarGridConferencias() {
		//adequa estado da UI
    	showLoading(false);
		resultGrid.clearGrid();
		controladora.getMensagemWidget().limparMensagem();
		((LabelCampoForm) panel.getWidget(1)).setVisible(true);

		if (conferencias != null && !conferencias.isEmpty()) {
			setHeader();
			RowFormatter rowFormatter = resultGrid.getRowFormatter();

			ConferenciaTO conferencia;
			for (int row = 0; row < conferencias.size(); row++) {
				conferencia = conferencias.get(row);

				showLoading(true);
				if (row+1 % 2 == 0) {
					rowFormatter.setStyleName(row+1, HoverGridWidget.STYLE_ROW_EVEN);
				}
				else {
					rowFormatter.setStyleName(row+1, HoverGridWidget.STYLE_ROW_ODD);
				}

				showLoading(false);

				resultGrid.setId(row+1, String.valueOf(conferencia.getId()));
				resultGrid.setText(row+1, 1, conferencia.getTitulo());

				showLoading(false);				

				resultGrid.setText(row+1, 2, _dtTimeFormat.format( conferencia.getInicio() ));
				resultGrid.setText(row+1, 3, _dtTimeFormat.format( conferencia.getTermino() ));
			}
		}
		else {
			((LabelCampoForm) panel.getWidget(1)).setVisible(false);
			controladora.setMensagemErro("N\u00e3o h\u00e1 confer\u00eancias agendadas.");
		}
    }

	/**
	 * montarGridInfoAdicional 
	 */
	private void montarGridInfoAdicional(ConferenciaTO to) {
		if (to != null) {
			grid = new Grid(3, 2);
			grid.setBorderWidth(0);
			grid.setCellSpacing(4);
			grid.setCellPadding(6);
			grid.setTitle("Detalhes confer\u00eancia selecionada...");

			grid.setWidget(0, 0, new LabelCampoForm("Descri\u00e7\u00e3o"));
			grid.setWidget(1, 0, new LabelCampoForm("In\u00edcio chamada trabalhos"));
			grid.setWidget(2, 0, new LabelCampoForm("T\u00e9rmino chamada trabalhos"));

			grid.setWidget(0, 1, new LabelCampoForm(to.getDescricao() != null ? to.getDescricao().trim() : ""));
			grid.setWidget(1, 1, new LabelCampoForm( _dtTimeFormat.format( to.getInicioChamadaTrabalhos() ) ));
			grid.setWidget(2, 1, new LabelCampoForm( _dtTimeFormat.format( to.getTerminoChamadaTrabalhos() ) ));

			panelDetalhesConf.setVisible(true);
			panelDetalhesConf.add(grid);
		}
	}

    /**
     * setHeader
     */
	private void setHeader() {
		resultGrid.setWidth(600 + "px");
		resultGrid.addHeader(1, "  T\u00edtulo  ");
		resultGrid.addHeader(2, "  In\u00edcio  ");
		resultGrid.addHeader(3, "  T\u00e9rmino  ");
	}
}