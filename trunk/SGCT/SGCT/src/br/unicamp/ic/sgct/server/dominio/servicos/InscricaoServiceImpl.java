package br.unicamp.ic.sgct.server.dominio.servicos;

import java.util.List;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;
import br.unicamp.ic.sgct.client.aplicacao.ucs.inscricao.InscricaoService;
import br.unicamp.ic.sgct.client.dominio.exception.InfraException;
import br.unicamp.ic.sgct.client.dominio.to.UsuarioTO;
import br.unicamp.ic.sgct.server.dominio.entidades.Usuario;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class InscricaoServiceImpl extends RemoteServiceServlet implements
		InscricaoService {

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

			Ammentos.openTransaction();
			
			// Se o usuário não existia no banco de dados, insira-o normalmente
			if (lstUsuario != null && lstUsuario.isEmpty()) {

				Ammentos.save( usuarioPersist.getPessoa() );
				Ammentos.save(usuarioPersist);
	
			}
			// Se um usuário foi encontrado, execute um update alterando o
			// atributo INSCRICAOATIVA para 'S', desde que o usuário já
			// tenha se isncrito e esteja com a inscricao cancelada
			if (lstUsuario != null && !lstUsuario.isEmpty() ) {
				// Verifica se o usuário já estava inscrito, com inscricao ativa
				if (lstUsuario.get(0).getInscricaoAtiva().equals("S")) {
					throw new InfraException("Usu\u00e1rio j\u00e1 est\u00e1 " +
					"inscrito para o evento!");
					
				}
				
				// Atualiza atributo INSCRICAOATIVA para 'N'
				lstUsuario.get(0).setInscricaoAtiva("S");
				
				Ammentos.save ( lstUsuario.get(0) );
				Ammentos.save ( lstUsuario.get(0).getPessoa() );

			}
			Ammentos.commitTransaction();

		}
		catch (PersistenceException e) {
			throw new InfraException("InfraException: " + e.getMessage());
		}
	}
}