package pruebas;

import static org.junit.Assert.*;
import laberinto.Laberinto;
import laberinto.Sala;

import org.junit.BeforeClass;
import org.junit.Test;

import ed.List;

public class LaberintoTest {
	static Laberinto lab;

	@BeforeClass
	public static void setUp() throws Exception {
		lab = Laberinto.getInstance();
		lab.construirLaberinto(35, 6, 6, 5);
	}

	@Test
	public void testConstruirLaberinto() {
		boolean exito = false;
		if ((lab.getDimx() == 6) && (lab.getDimx() == 6)
				&& (lab.getSala_Puerta() == 35)
				&& (lab.getPuerta().getAltura_Apertura() == 5)) {
			exito = true;
		}

		assertTrue(exito);

	}

	@Test
	public void testPonerVecinas() {
		List<Sala> l = lab.getSalas();
		Sala s = l.getFirst();
		assertTrue((s.getSur().getId_sala() == (s.getId_sala() + lab.getDimy())));

	}

	@Test
	public void testInsertarSala() {
		lab.insertarSala(new Sala(lab.getSalas().size()));
		List<Sala> l = lab.getSalas();
		assertTrue((l.getLast().getId_sala() == (lab.getDimx() * lab.getDimy())));
	}

	@Test
	public void testSalaInicial() {
		boolean exito = false;
		Sala s = lab.salaInicial();
		if ((s.getId_sala()) == (lab.getSalas().getFirst().getId_sala()))
			exito = true;
		assertTrue(exito);
	}

	@Test
	public void testProcesar() {

		for (int i = 0; i < 15; i++) {
			lab.procesar(i);
		}
		assertNotEquals(new Integer(0), new Integer(lab.getTurno()));
	}

	@Test
	public void testKruskal() {
		String s = "";

		for (int i = 0; i < lab.getDimy(); i++) {
			s = s + " _";
		}

		s = s + "\n";

		int indice_sala = 0;
		Sala sala;
		for (int i = 0; i < lab.getDimx(); i++) {
			for (int j = 0; j < lab.getDimy(); j++) {
				indice_sala = i * lab.getDimy() + j;
				sala = lab.getSalas().get(indice_sala);
				if (!sala.getRobots().isEmpty()) {
					if (sala.getRobots().size() == 1)
						s = s + "|" + sala.getRobots().getFirst().getId();
					else
						s = s + "|" + sala.getRobots().size();
				} else
					s = s + "|_";
			}
			s = s + "|\n";
		}

		String s2 = lab.pintarLaberinto();

		assertFalse((s.equals(s2)));

	}

	@Test
	public void testEspacioVacioNorte() {
		assertFalse(lab.espacioVacioNorte(15));
		assertTrue(lab.espacioVacioNorte(28));

	}

	@Test
	public void testEspacioVacioSur() {
		assertFalse(lab.espacioVacioSur(13));
		assertTrue(lab.espacioVacioSur(1));
	}

	@Test
	public void testEspacioVacioOeste() {
		assertFalse(lab.espacioVacioOeste(13));
		assertTrue(lab.espacioVacioOeste(14));
	}

	@Test
	public void testEspacioVacioEste() {
		assertFalse(lab.espacioVacioEste(14));
		assertTrue(lab.espacioVacioEste(16));
	}

	@Test
	public void testEspacioVacioEsteArriba() {
		assertFalse(lab.espacioVacioEsteArriba(8));
		assertTrue(lab.espacioVacioEsteArriba(26));
	}

	@Test
	public void testEspacioVacioEsteAbajo() {
		assertFalse(lab.espacioVacioEsteAbajo(22));
		assertTrue(lab.espacioVacioEsteAbajo(15));
	}

	@Test
	public void testEspacioVacioOesteArriba() {
		assertFalse(lab.espacioVacioOesteArriba(34));
		assertTrue(lab.espacioVacioOesteArriba(27));
	}

	@Test
	public void testEspacioVacioOesteAbajo() {
		assertFalse(lab.espacioVacioOesteAbajo(3));
		assertTrue(lab.espacioVacioOesteAbajo(16));
	}

	@Test
	public void testEspacioVacioSurIzq() {
		assertFalse(lab.espacioVacioSurIzq(4));
		assertTrue(lab.espacioVacioSurIzq(22));
	}

	@Test
	public void testEspacioVacioSurDer() {
		assertFalse(lab.espacioVacioSurDer(0));
		assertTrue(lab.espacioVacioSurDer(14));
	}

	@Test
	public void testEspacioVacioNorteIzq() {
		assertFalse(lab.espacioVacioNorteIzq(32));
		assertTrue(lab.espacioVacioNorteIzq(28));
	}

	@Test
	public void testEspacioVacioNorteDer() {
		assertFalse(lab.espacioVacioNorteDer(28));
		assertTrue(lab.espacioVacioNorteDer(7));
	}

	@Test
	public void testAtajosNorte() {
		assertTrue(lab.atajosNorte(19));
		assertFalse(lab.atajosNorte(27));
	}

	@Test
	public void testAtajosSur() {
		assertTrue(lab.atajosSur(17));
		assertFalse(lab.atajosSur(5));
	}

	@Test
	public void testAtajosOeste() {
		assertTrue(lab.atajosOeste(3));
		assertFalse(lab.atajosOeste(28));
	}

	@Test
	public void testAtajosEste() {
		assertTrue(lab.atajosEste(25));
		assertFalse(lab.atajosEste(18));
	}

}
