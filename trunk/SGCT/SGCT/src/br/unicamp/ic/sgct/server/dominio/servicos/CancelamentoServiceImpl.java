package br.unicamp.ic.sgct.server.dominio.servicos;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento.CancelamentoService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.InscricaoTO;
import br.unicamp.ic.sgct.client.dominio.to.Inscricao_SessaoTO;
import br.unicamp.ic.sgct.client.dominio.to.SessaoTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;
import br.unicamp.ic.sgct.server.dominio.entidades.Inscricao;
import br.unicamp.ic.sgct.server.dominio.entidades.Inscricao_Sessao;
import br.unicamp.ic.sgct.server.dominio.entidades.Usuario;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CancelamentoServiceImpl extends RemoteServiceServlet implements
		CancelamentoService {

	//INICIO - EXERCICIO 5
	
	private boolean aceitarCancelamento(Date dataAtual, Date dataEfetivacaoPagamento) {
		
		System.out.println(dataEfetivacaoPagamento);
		System.out.println(dataAtual);
		
		int MSEGUNDOS_DIA = 24 * 60 * 60 * 1000;
		int diferenca = 0;

		Date dataAux = new Date(dataAtual.getTime() - MSEGUNDOS_DIA);

		while(dataAux.after(dataEfetivacaoPagamento)){

			if (dataAux.getDay() != 0 && dataAux.getDay() != 6){
				diferenca++;
				dataAux = new Date(dataAux.getTime() - MSEGUNDOS_DIA);
			}
		}

		if (diferenca > 7) {
			return false;
		}else {
			return true;
		}
	}
	
	// FIM - EXERCICIO 5
	
	public void cancelar(UsuarioTO usuario) throws InfraException {
		List<Usuario> lstUsuario;
		List<Inscricao_Sessao> listInscricaoSessao;
		System.out
				.println("\nCancelamentoServiceImpl :: cancelar(Usuario) acionado");

		try {
			
			if (usuario.getListInscricaoSessaoTO().isEmpty()) {
				throw new InfraException("Usu\u00e1rio deve selecionar pelo menos um evento para cancelamento!");
			}
			
			
			Usuario usuarioPersist = new Usuario(usuario);
			
			AmmentosConnection.instance();

			lstUsuario = Ammentos.load(Usuario.class,
					new SqlQueryFilter("email like '" + usuarioPersist.getEmail() + 
							"' and senha like '" + usuarioPersist.getSenha() + "'"));

			if (lstUsuario != null && lstUsuario.isEmpty()) {
				throw new InfraException("Usu\u00e1rio n\u00e3o se encontra " +
						"inscrito para o evento!"); 
			}
			
			listInscricaoSessao = Ammentos.load(Inscricao_Sessao.class, new SqlQueryFilter("id_insc like '" + lstUsuario.get(0).getInscUsuario().get(0).getId()+ "'"));
			
			for (Inscricao_SessaoTO inscricao_SessaoTO : usuario.getListInscricaoSessaoTO()) {
				
				for (Inscricao_Sessao inscricao_Sessao : listInscricaoSessao) {
					
					if (inscricao_Sessao.getId() == inscricao_SessaoTO.getId()) {
						
						if (lstUsuario != null && !lstUsuario.isEmpty()) {
							// Verifica se hï¿½ mais de um usuï¿½rio registrado com o mesmo
							// email e senha
							if (lstUsuario.size() > 1) {
								throw new InfraException("H\u00e1 mais de um " +
										"usu\u00e1rio registrado com seu email e senha. Favor " +
										"procure o administrador do sistema!");
							}
							// Verificar se o usuário já cancelou sua inscricao, através do 
							// atributo INSCRICAOATIVA da tabela T_USUARIO (se for 'N', ja foi cancelado)
//							if (lstUsuario.get(0).getInscricaoAtiva().equals("N")) {
//								throw new InfraException("Usu\u00e1rio n\u00e3o se encontra mais " +
//								"inscrito para o evento!");
//							}
							
							for (Inscricao inscricao : lstUsuario.get(0).getInscUsuario()) {
								if (inscricao.getSituacao() == 3) {
									throw new InfraException("Usu\u00e1rio n\u00e3o se encontra mais " +
									"inscrito para o evento!");
								}
							}
							
							// Verifica se jï¿½ se passaram 7 dias da inscricao no evento
							// TODO Inserir codigo apos alteracoes do Denis
							Date dataPgto = lstUsuario.get(0).getDataEfetivacaoPagto();
							Date dataAtual = new Date(System.currentTimeMillis());

							if (aceitarCancelamento(dataAtual, dataPgto)) {
								
								// Atualiza atributo INSCRICAOATIVA para 'N'
								//lstUsuario.get(0).setInscricaoAtiva("N");
								
								if (listInscricaoSessao.size() <= 1) {
									lstUsuario.get(0).getInscUsuario().get(0).setSituacao(3);
								}
								
								// Processa o cancelamento do usuario
								Ammentos.openTransaction();
				
								Ammentos.save ( lstUsuario.get(0) );
								Ammentos.save ( lstUsuario.get(0).getPessoa() );
								Ammentos.save( lstUsuario.get(0).getInscUsuario().get(0) );
									
								Ammentos.delete(inscricao_Sessao);
								
								Ammentos.commitTransaction();
						
							}
							else {
								throw new InfraException("Per\u00a1odo de " +
										"cancelamento de sua inscri\u00e7\u00e3o ultrapassado!");
							}
						}
						
					}
				}
			}

		}
		catch (PersistenceException e) {
			throw new InfraException("InfraException: " + e.getMessage());
		}
	}
	
	//INICIO - EXERC 5
	
	public List<InscricaoTO> listarInscricoes(UsuarioTO usuario) throws InfraException, Exception {
		List<Usuario> lstUsuario;
		List<InscricaoTO> listInscricaoTO = new ArrayList<InscricaoTO>();
		
		try {
			
			//Usuario usuarioPersist = new Usuario(usuario);
			
			AmmentosConnection.instance();

			lstUsuario = Ammentos.load(Usuario.class,
					new SqlQueryFilter("email like '" + usuario.getEmail() + 
							"' and senha like '" + usuario.getSenha() + "'"));
			
			
			if (lstUsuario != null && lstUsuario.isEmpty()) {
				throw new InfraException("Usu\u00e1rio n\u00e3o se encontra " +
						"inscrito para nenhum evento!"); 
			}
			
			List<Inscricao> listInscricaoBD = Ammentos.load(Inscricao.class,
					new SqlQueryFilter("id_usuario = '" + lstUsuario.get(0).getId() + 
							"'"));
			
			List<Inscricao_Sessao> listInscricaoSessaoBD = Ammentos.load(Inscricao_Sessao.class,
					new SqlQueryFilter("id_insc = '" + listInscricaoBD.get(0).getId() + 
							"'"));
			
			for (Inscricao inscricao : listInscricaoBD) {
				
				if (inscricao.getSituacao() != 3) {
					InscricaoTO inscricaoTO = new InscricaoTO();
					inscricaoTO.setId(inscricao.getId());
					inscricaoTO.setDt_inscricao(inscricao.getDt_inscricao());
					inscricaoTO.setDt_pagamento(inscricao.getDt_pagamento());
					
					for (Inscricao_Sessao inscricao_Sessao : listInscricaoSessaoBD) {
						Inscricao_SessaoTO inscricao_SessaoTO = new Inscricao_SessaoTO();
						
						inscricao_SessaoTO.setId(inscricao_Sessao.getId());
						inscricao_SessaoTO.setInscricao(inscricaoTO);
						
						SessaoTO sessaoTO = new SessaoTO();
						sessaoTO.setId(inscricao_Sessao.getSessao().getId());
						sessaoTO.setTema(inscricao_Sessao.getSessao().getTema());

						inscricao_SessaoTO.setSessao(sessaoTO);
						
						inscricaoTO.addSessoesInscricao(inscricao_SessaoTO);
					}
					
					inscricaoTO.setSituacao(inscricao.getSituacao());
					
					listInscricaoTO.add(inscricaoTO);
				}
			}
			
		} catch (PersistenceException e) {
			throw new InfraException("InfraException: " + e.getMessage());
		}
		
		return listInscricaoTO;
	}
	
	//FIM - EXERC 5
}
