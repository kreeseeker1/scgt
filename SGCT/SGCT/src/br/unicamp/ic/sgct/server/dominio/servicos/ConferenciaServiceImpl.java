package br.unicamp.ic.sgct.server.dominio.servicos;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.Query;
import it.biobytes.ammentos.query.SqlQueryFilter;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.sgct.client.aplicacao.ucs.conferencia.ConferenciaService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.CentroConvencaoTO;
import br.unicamp.ic.sgct.client.dominio.to.ConferenciaTO;
import br.unicamp.ic.sgct.client.dominio.to.SalaTO;
import br.unicamp.ic.sgct.client.dominio.to.SessaoTO;
import br.unicamp.ic.sgct.server.dominio.entidades.CentroConvencao;
import br.unicamp.ic.sgct.server.dominio.entidades.Conferencia;
import br.unicamp.ic.sgct.server.dominio.entidades.Sessao;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class ConferenciaServiceImpl extends RemoteServiceServlet implements
		ConferenciaService {

	/**
	 * 
	 */
	public List<ConferenciaTO> listarConferencias() throws InfraException, Exception {
		System.out
				.println("\nConferenciaServiceImpl :: listarConferencias() acionado");

		List<Conferencia> lstConfBD = null;
		List<ConferenciaTO> lstConferencias = null;

		try {
			AmmentosConnection.instance();

			Query sql = new Query(Conferencia.PERSISTENCE_DOMAIN);
			sql.appendFilter( new SqlQueryFilter("TERMINO >= CURRENT_DATE") );
			sql.appendOrderFilter("termino");
			sql.setOrderingDir(Query.ORDERING_DESC);

			lstConfBD = (List<Conferencia>) Ammentos.load(Conferencia.class, sql);
			if (lstConfBD != null && !lstConfBD.isEmpty()) {
				lstConferencias = new ArrayList<ConferenciaTO>();

				ConferenciaTO to = null;
				for (Conferencia conferencia : lstConfBD) {
					to = popularTo(conferencia);
					lstConferencias.add(to);
				}
			}
		}
		catch (PersistenceException e) {
			System.err.println(e.getStackTrace().toString());
			throw new InfraException("InfraException: " + e.getMessage());
		}
		return lstConferencias;
	}

	/**
	 * 
	 * @param Conferencia
	 * @return ConferenciaTO
	 */
	private ConferenciaTO popularTo(Conferencia conferencia) throws Exception {
		System.out
				.println("\nConferenciaServiceImpl :: popularTo() acionado");

		ConferenciaTO to = new ConferenciaTO();
		to.setDescricao( conferencia.getDescricao() );
		to.setEndereco( conferencia.getEndereco() );
		to.setId( conferencia.getId() );
		to.setInicio( conferencia.getInicio() );
		to.setInicioChamadaTrabalhos( conferencia.getInicioChamadaTrabalhos() );
		to.setTermino( conferencia.getTermino() );
		to.setTerminoChamadaTrabalhos( conferencia.getTerminoChamadaTrabalhos() );
		to.setTitulo( conferencia.getTitulo() );

		CentroConvencao centro = conferencia.getCentroConvencao();

		CentroConvencaoTO centroTo = new CentroConvencaoTO();
		centroTo.setEndereco( centro.getEndereco() );
		centroTo.setFax( centro.getFax() );
		centroTo.setFone( centro.getFone() );
		centroTo.setId( centro.getId() );
		centroTo.setNome( centro.getNome() );
		//
		//TODO: carregar associacoes
		centroTo.setSalas( null );
		centroTo.setStands( null );

		
		List<Sessao> sessaoList = conferencia.getSessoesConferencia();
		List<SessaoTO> sessaoListTO = new ArrayList<SessaoTO>();
		
		for (Sessao sessao : sessaoList) {
			SessaoTO sessaoTO = new SessaoTO();
			
			sessaoTO.setTema(sessao.getTema());
			
			SalaTO salaTO = new SalaTO();
			salaTO.setId(sessao.getSala().getId());
			salaTO.setCapacidade(sessao.getSala().getCapacidade());
			salaTO.setIdentificacao(sessao.getSala().getIdentificacao());
			
			sessaoTO.setSala(salaTO);
			
			
			sessaoTO.setId(sessao.getId());
			sessaoTO.setData(sessao.getData());
			sessaoTO.setSequencialPeriodo(sessao.getSequencialPeriodo());
			sessaoTO.setPeriodo(sessao.getPeriodo());
			
			ConferenciaTO conferenciaTO = new ConferenciaTO();
			conferenciaTO.setDescricao( sessao.getConferencia().getDescricao() );
			conferenciaTO.setEndereco( sessao.getConferencia().getEndereco() );
			conferenciaTO.setId( sessao.getConferencia().getId() );
			conferenciaTO.setInicio( sessao.getConferencia().getInicio() );
			conferenciaTO.setInicioChamadaTrabalhos( sessao.getConferencia().getInicioChamadaTrabalhos() );
			conferenciaTO.setTermino( sessao.getConferencia().getTermino() );
			conferenciaTO.setTerminoChamadaTrabalhos( sessao.getConferencia().getTerminoChamadaTrabalhos() );
			conferenciaTO.setTitulo( sessao.getConferencia().getTitulo() );
			
			sessaoTO.setConferencia(conferenciaTO);
			
			sessaoListTO.add(sessaoTO);
		}
		to.setCentroConvencao( centroTo );
		to.setSessoesConferencia( sessaoListTO );

		return to;
	}

	/**
	 *
	 * @param List<ConferenciaTO> 
	 */
	private void printlnDados(List<ConferenciaTO> lstConfTo) {
		System.out.println("=========[ DADOS CONFERENCIA ]=========");
		if (lstConfTo != null && lstConfTo.size() > 0) {
			for (ConferenciaTO conferenciaTO : lstConfTo) {
				printlnDados(conferenciaTO);
			}
		}
		System.out.println("=======================================");
	}

	/**
	 * 
	 * @param ConferenciaTO
	 */
	private void printlnDados(ConferenciaTO confTo) {
		System.out.println("Conferencia = " + confTo.getTitulo());
		System.out.println("Centro conv. = " + confTo.getCentroConvencao().toString());

		System.out.println("Sessoes da conf.: \n\n");
		List<SessaoTO> lstSessoesTo = confTo.getSessoesConferencia();
		if (lstSessoesTo != null && lstSessoesTo.size() > 0) {
			for (SessaoTO sessaoTo : lstSessoesTo) {
				System.out.println(sessaoTo.toString());
			}
		}
		else {
			System.out.println("*** Lista Sessoes conferencia nao carregada para conferencia "
							+ confTo.getTitulo());
		}
	}
}