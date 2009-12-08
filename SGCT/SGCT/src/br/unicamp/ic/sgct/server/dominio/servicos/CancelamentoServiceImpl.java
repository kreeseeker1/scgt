package br.unicamp.ic.sgct.server.dominio.servicos;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;

import java.util.Date;
import java.util.List;

import br.unicamp.ic.sgct.client.aplicacao.ucs.cancelamento.CancelamentoService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
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

	private boolean aceitarCancelamento(Date dataAtual, Date dataEfetivacaoPagamento) {
		
		System.out.println(dataEfetivacaoPagamento);
		System.out.println(dataAtual);

		int MSEGUNDOS_DIA = 24 * 60 * 60 * 1000;
		int diferenca = (int)((dataAtual.getTime() - 
				dataEfetivacaoPagamento.getTime()) / MSEGUNDOS_DIA); 

		if (diferenca > 7) {
 			return false;
		}
		else {
			return true;
		}
	}

	public void cancelar(UsuarioTO usuario) throws InfraException {
		List<Usuario> lstUsuario;
		List<Inscricao_Sessao> listInscricaoSessao;
		System.out
				.println("\nCancelamentoServiceImpl :: cancelar(Usuario) acionado");

		try {
			Usuario usuarioPersist = new Usuario(usuario);
			
			AmmentosConnection.instance();

			lstUsuario = Ammentos.load(Usuario.class,
					new SqlQueryFilter("email like '" + usuarioPersist.getEmail() + 
							"' and senha like '" + usuarioPersist.getSenha() + "'"));

			listInscricaoSessao = Ammentos.load(Inscricao_Sessao.class, new SqlQueryFilter("id_insc like '" +lstUsuario.get(0).getInscUsuario().get(0).getId()+ "'"));
			
			if (lstUsuario != null && lstUsuario.isEmpty()) {
				throw new InfraException("Usu\u00e1rio n\u00e3o se encontra " +
						"inscrito para o evento!"); 
			}
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
//				if (lstUsuario.get(0).getInscricaoAtiva().equals("N")) {
//					throw new InfraException("Usu\u00e1rio n\u00e3o se encontra mais " +
//					"inscrito para o evento!");
//				}
				
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
					
					lstUsuario.get(0).getInscUsuario().get(0).setSituacao(3);
					
					// Processa o cancelamento do usuario
					Ammentos.openTransaction();
	
					Ammentos.save ( lstUsuario.get(0) );
					Ammentos.save ( lstUsuario.get(0).getPessoa() );
					Ammentos.save( lstUsuario.get(0).getInscUsuario().get(0) );
	
					for (Inscricao_Sessao inscricao_Sessao : listInscricaoSessao) {
						Ammentos.delete(inscricao_Sessao);
					}
					
					Ammentos.commitTransaction();
			
				}
				else {
					throw new InfraException("Per\u00a1odo de " +
							"cancelamento de sua inscri\u00e7\u00e3o ultrapassado!");
				}
				
			}

		}
		catch (PersistenceException e) {
			throw new InfraException("InfraException: " + e.getMessage());
		}
	}
}