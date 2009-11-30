package br.unicamp.ic.sgct.persistencia.test;

import it.biobytes.ammentos.Ammentos;
import it.biobytes.ammentos.PersistenceException;
import it.biobytes.ammentos.query.SqlQueryFilter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unicamp.ic.sgct.server.dominio.entidades.CentroConvencao;
import br.unicamp.ic.sgct.server.dominio.entidades.Sala;
import br.unicamp.ic.sgct.server.dominio.entidades.Stand;
import br.unicamp.ic.sgct.server.recursos.persistencia.AmmentosConnection;

public class CentroConvencaoPersistenciaTest {

	@Before
	public void setUp() throws Exception {
		AmmentosConnection.instance();
	}

	@After
	public void tearDown() throws Exception {
	}

	// #######[ TESTES FLUXO BASE ]#######
	@Test
	public void testObterRecursosCentroConvencao() throws PersistenceException {
		System.out.println("\n\n # CentroConvencaoPersistenciaTest :: testObterRecursosCentroConvencao");
		try {
			SqlQueryFilter sqf = new SqlQueryFilter("id_cconv = ?");
			sqf.setInteger(1);
			CentroConvencao cconv = Ammentos.loadUnique(CentroConvencao.class,
					sqf);

			if (cconv instanceof CentroConvencao) {
				System.out.println(cconv.toString());

				// SALAS
				if (cconv.getSalas() != null) {
					System.out
							.println("\n================[SALAS]========================");
					for (Sala sala : cconv.getSalas()) {
						System.out.println(sala.toString());
					}
				} else {
					System.out
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERRO, getSalas() NULO!");
				}

				// STANDS
				if (cconv.getStands() != null) {
					System.out
							.println("\n================[STANDS]========================");
					for (Stand stand : cconv.getStands()) {
						System.out.println(stand.toString());
					}
				} else {
					System.out
							.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERRO, getSalas() NULO!");
				}
			}
			Assert.assertNotNull(cconv);
		}
		catch (PersistenceException e) {
			e.printStackTrace();
		}
	}
}