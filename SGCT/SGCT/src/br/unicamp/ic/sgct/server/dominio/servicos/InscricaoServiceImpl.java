package br.unicamp.ic.sgct.server.dominio.servicos;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao.InscricaoService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.Inscricao_SessaoTO;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;
import br.unicamp.ic.sgct.server.dominio.entidades.Inscricao;
import br.unicamp.ic.sgct.server.dominio.entidades.Inscricao_Sessao;
import br.unicamp.ic.sgct.server.dominio.entidades.Pessoa;
import br.unicamp.ic.sgct.server.dominio.entidades.Sessao;
import br.unicamp.ic.sgct.server.dominio.entidades.Usuario;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InscricaoServiceImpl extends RemoteServiceServlet implements
		InscricaoService {

	//INICIO - EXERC. 4
	
	public void inscrever(UsuarioTO usuario) throws InfraException {
		List<Usuario> lstUsuario;
		
		System.out
				.println("\nInscricaoServiceImpl :: inscrever(Usuario) acionado");

		try {
			
			Usuario usuarioPersist = new Usuario(usuario);
			
			AmmentosConnection.instance();

			lstUsuario = Ammentos.load(Usuario.class,
					new SqlQueryFilter("email like '" + usuarioPersist.getEmail() + 
							"'"));

			Inscricao inscricaoPersist = new Inscricao();
			
			inscricaoPersist.setDt_inscricao(usuario.getInscUsuario().get(0).getDt_inscricao());
			inscricaoPersist.setDt_pagamento(usuario.getInscUsuario().get(0).getDt_pagamento());
			inscricaoPersist.setSituacao(usuario.getInscUsuario().get(0).getSituacao());
			inscricaoPersist.setUsuario(usuarioPersist);
			
			List<Inscricao_Sessao> listInscricaoSessaoPersist = new ArrayList<Inscricao_Sessao>();
			long sessaoId = 0;
			String sessaoNome = null;
			
			for (Inscricao_SessaoTO inscricao_Sessao : usuario.getListInscricaoSessaoTO()) {
				
				Inscricao_Sessao inscricao_SessaoPersist = new Inscricao_Sessao();
				inscricao_SessaoPersist.setInscricao(inscricaoPersist);
				
				Sessao sessaoPersist = new Sessao();
				sessaoPersist.setId(inscricao_Sessao.getSessao().getId());
				sessaoId = inscricao_Sessao.getSessao().getId();
				sessaoNome = inscricao_Sessao.getSessao().getTema();
				
				inscricao_SessaoPersist.setSessao(sessaoPersist);
				
				listInscricaoSessaoPersist.add(inscricao_SessaoPersist);
				
			}
			
			if (sessaoId != 0) {
				List<Sessao> listSessao = Ammentos.load(Sessao.class,
						new SqlQueryFilter("id_sessaoconf = '" + sessaoId + "'"));
				
				List<Inscricao_Sessao> listInscricaoSessaoBD = Ammentos.load(Inscricao_Sessao.class,
						new SqlQueryFilter("id_sessaoconf = '" + sessaoId + "'"));
				
				
				
				int capacidadeTotal = listSessao.get(0).getSala().getCapacidade();
				int capacidadeParcial = listInscricaoSessaoBD.size();
				
				if ( capacidadeTotal <= capacidadeParcial) {
					throw new InfraException("Não há mais vagas para o(s) evento(s) selecionado(s): " + sessaoNome + ". Favor selecionar outro(s) de seu interesse.");
				}
			}
			
			Ammentos.openTransaction();
			
			// Se o usuário não existia no banco de dados, insira-o normalmente
			if (lstUsuario != null && lstUsuario.isEmpty()) {

				Ammentos.save( usuarioPersist.getPessoa() );
				Ammentos.save(usuarioPersist);
				Ammentos.save(inscricaoPersist);
				
				for (Inscricao_Sessao inscricao_SessaoPersiste : listInscricaoSessaoPersist) {
					Ammentos.save(inscricao_SessaoPersiste);
				}
				
	
			}
			// Se um usuário foi encontrado, execute um update alterando o
			// atributo INSCRICAOATIVA para 'S', desde que o usuário já
			// tenha se isncrito e esteja com a inscricao cancelada
			if (lstUsuario != null && !lstUsuario.isEmpty() ) {
				// Verifica se o usuário já estava inscrito, com inscricao ativa

				for (Inscricao inscricao : lstUsuario.get(0).getInscUsuario()) {
					if (inscricao.getSituacao() == 1) {
						throw new InfraException("Usu\u00e1rio j\u00e1 est\u00e1 " +
						"inscrito para o evento!");
					}
				}
				
				// Atualiza atributo INSCRICAOATIVA para 'N'
				//lstUsuario.get(0).setInscricaoAtiva("S");
				
				List<Inscricao> listInscricaoBD = Ammentos.load(Inscricao.class,
						new SqlQueryFilter("id_usuario = '" + lstUsuario.get(0).getId() + 
								"'"));
				
				lstUsuario.get(0).setDataEfetivacaoPagto(usuario.getDataEfetivacaoPagto());
				lstUsuario.get(0).setInscUsuario(listInscricaoBD);
				
				Pessoa pessoa = new Pessoa();
				pessoa.setCelular(usuario.getPessoa().getCelular());
				pessoa.setFoneResidencial(usuario.getPessoa().getFoneResidencial());
				pessoa.setIdPessoa(lstUsuario.get(0).getPessoa().getIdPessoa());
				pessoa.setNome(usuario.getPessoa().getNome());
				pessoa.setSobreNome(usuario.getPessoa().getSobreNome());
				
				lstUsuario.get(0).setPessoa(pessoa);
				lstUsuario.get(0).setSenha(usuario.getSenha());
				
				listInscricaoBD.get(0).setUsuario(lstUsuario.get(0));
				listInscricaoBD.get(0).setSituacao(1);
				
				Ammentos.save ( lstUsuario.get(0) );
				Ammentos.save ( lstUsuario.get(0).getPessoa() );
				Ammentos.save(listInscricaoBD.get(0));
				
				for (Inscricao_Sessao inscricao_SessaoPersiste : listInscricaoSessaoPersist) {
					inscricao_SessaoPersiste.setInscricao(listInscricaoBD.get(0));
					Ammentos.save(inscricao_SessaoPersiste);
				}
				
			}
			Ammentos.commitTransaction();
			
			

		}
		catch (PersistenceException e) {
			throw new InfraException("InfraException: " + e.getMessage());
		}
	}
	//FIM - EXERC. 4
}