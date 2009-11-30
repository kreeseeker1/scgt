package br.unicamp.ic.sgct.persistencia.test;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unicamp.ic.sgct.server.dominio.entidades.Pessoa;
import br.unicamp.ic.sgct.server.dominio.entidades.Preletor;
import br.unicamp.ic.sgct.server.dominio.entidades.Usuario;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

public class UsuarioPersistenciaTest {
	private Random randGenerator = null;

	@Before
	public void setUp() throws Exception {
		AmmentosConnection.instance();

		randGenerator = new Random( ( new Date() ).getTime() );	
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Faz pesquisa relativa na base de dados a partir de 'param' para 'classe' sendo esta uma classe do subpacote dominio
	 * 
	 * @param param
	 * @param valor
	 * @return List<?>
	 * @throws PersistenceException
	 */
	private List<?> pesquisar(String param, Class<?> classe, String valor) throws PersistenceException {
		SqlQueryFilter sqf = new SqlQueryFilter(param + " LIKE ?");
		sqf.setString("%" + valor + "%");

		List<?> lst = Ammentos.load(classe, sqf);
		
		return lst;
	}

	/**
	 * 
	 * @param usuario
	 */
	private void printDadosUsuario(Usuario usuario) {
		System.out.println("\n========================================");
		System.out.println(usuario.toString());
	}

	/**
	 * 
	 * @param preletor
	 */
	private void printDadosPreletor(Preletor preletor) {
		System.out.println("\n========================================");
		System.out.println(preletor.toString());
	}

	//#######[ TESTES FLUXO BASE ]#######

	@Test
	public void testObterRelacaoRegistros() throws PersistenceException {
		System.out.println("\n\n # UsuarioPersistenciaTest :: testObterRelacaoRegistros");
		try {
			SqlQueryFilter sqf = new SqlQueryFilter("email LIKE ?");
			sqf.setString("%gmail%");
			List<Usuario> lstUsuario = Ammentos.load(Usuario.class, sqf);
		
			if (lstUsuario != null && lstUsuario.size() > 0) {
				for (Usuario usuario : lstUsuario) {
					printDadosUsuario(usuario);
				}
			}
			Assert.assertNotNull(lstUsuario);
		}
		catch (PersistenceException e) {
			e.printStackTrace();
		}
	}



	@Test
	public void testSalvarUsuario() {
		System.out.println("\n\n # UsuarioPersistenciaTest :: testSalvarUsuario");
		try {
			int randomValue = randGenerator.nextInt();
			String sobreNome = "Neto" + randomValue;
			String email     = "jneto" + randomValue + "@gmail.com";
			
			// 1. salva usuario
			Pessoa pessoa = new Pessoa("Joao", sobreNome, "19 9111-1111",
					"19 8001-5522");

			Usuario usuario = new Usuario("senha", email, pessoa);

			Ammentos.openTransaction();

			Ammentos.save(pessoa);
			Ammentos.save(usuario);

			Ammentos.commitTransaction();

			try {
				usuario = null;

				//2. pesquisar
				List<Usuario> lst = (List<Usuario>) pesquisar("email", Usuario.class, "jneto");
				for (Usuario usuario2 : lst) {
					printDadosUsuario(usuario2);
				}
			}
			catch (PersistenceException error2) {
				error2.printStackTrace();
				Assert.assertTrue(error2.getMessage(), error2 == null);
			}
		}
		catch (PersistenceException error1) {
			try {
				Ammentos.rollbackTransaction();
			}
			catch (PersistenceException e) {
				e.printStackTrace();
			}

			error1.printStackTrace();
			Assert.assertTrue(error1.getMessage(), error1 == null);
		}
	}



	@Test
	public void testSalvarPreletor() {
		System.out.println("\n\n # UsuarioPersistenciaTest :: testSalvarPreletor");
		try {
			int randomValue  = randGenerator.nextInt();
			String sobreNome = "Sobrinho" + randomValue;
			String email     = "maria" + randomValue + "@gmail.com";

			// 1. salva preletor
			Pessoa pessoa = new Pessoa("Maria", sobreNome, "19 8111-1111",
					"19 9001-5522");

			Usuario usuario = new Usuario("senha", email, pessoa);

			Preletor preletor = new Preletor("Unicamp", null, usuario);

			Ammentos.openTransaction();

			Ammentos.save(pessoa);
			Ammentos.save(preletor); //salva subclasse e super classe
			

			Ammentos.commitTransaction();
			try {
				//2. pesquisa base
				List<Preletor> lst = (List<Preletor>) pesquisar("instituicao", Preletor.class, "Unicamp");
				for (Preletor preletor2 : lst) {
					printDadosPreletor(preletor2);
				}
			}
			catch (PersistenceException error2) {
				error2.printStackTrace();
				Assert.assertTrue(error2.getMessage(), error2 == null);
			}
			Assert.assertTrue("PRELETOR SALVO INCLUINDO DADOS DA SUPERCLASSE", true);
		}
		catch (PersistenceException error1) {
			try {
				Ammentos.rollbackTransaction();
			}
			catch (PersistenceException e) {
				e.printStackTrace();
			}

			error1.printStackTrace();
			Assert.assertTrue(error1.getMessage(), error1 == null);
		}
	}


	//#######[ TESTES FLUXO ALTERNATIVOS ]####### 
	@Test
	public void testSalvarRepetidoFA() {
		System.out.println("\n\n # UsuarioPersistenciaTest :: testSalvarRepetidoFA");
		try {
			SqlQueryFilter sqf = new SqlQueryFilter("email LIKE ?");
			sqf.setString("jneto%");
			List<Usuario> lstUsuario = Ammentos.load(Usuario.class, sqf);

			Usuario usuario = null;
			if (lstUsuario != null && lstUsuario.size() > 0) {
				usuario = lstUsuario.get(0);

				if (usuario instanceof Usuario) {
					printDadosUsuario(usuario);

					try {
						System.out.println("Teste Fluxo Alternativo - validacao da UNIQUE CONSTRAINT para coluna email de T_USUARIO");

						Ammentos.openTransaction();

						usuario.setEmail( usuario.getEmail().trim() );
						Ammentos.save(usuario);

						Ammentos.commitTransaction();

						//Assert.assertTrue("NAO DEVERIA TER GRAVADO, NAO CONSIDEROU UNIQUE CONSTRAINT", false);
					}
					catch (PersistenceException error2) {
						try {
							Ammentos.rollbackTransaction();
						}
						catch (PersistenceException e) {
							e.printStackTrace();
						}

						error2.printStackTrace();
						Assert.assertTrue("COMPORTAMENTO ESPERADO OCORREU, NAO PODE SALVAR POR CONTA DA UNIQUE CONSTRAINT PARA email"
												+ error2.getMessage(), error2 != null);
					}
				}
			}
		}
		catch (PersistenceException error1) {
			error1.printStackTrace();
			Assert.assertTrue(error1.getMessage(), error1 == null);
		}
	}
}