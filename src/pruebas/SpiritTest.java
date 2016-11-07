package pruebas;

import static org.junit.Assert.*;

import java.util.LinkedList;

import laberinto.LabSim.Dir;
import laberinto.Laberinto;
import laberinto.Sala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import robots.Spirit;

public class SpiritTest {
	Laberinto lab = Laberinto.getInstance();

	@Before
	public void setUp() throws Exception {
		lab.construirLaberinto(35, 6, 6, 5);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRutaManoIzquierda() {
		Spirit s = new Spirit("Spirit", 's', 0, lab.salaSuroeste());
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		s.rutaManoIzquierda(ruta);
		assertEquals("[E, E, E, N, N, O, S, N, O, O, S, E, O, N, E, E, E, N, O, N, O, S, O, E, N, O, E, E, N, O, O, E, E, S, S, E, S, E, N, N, O, E, E, N, O, O, E, E, S, S, N, O, S, S, O, S, E, E, N, S, S]", ruta.toString());
		
	}

}
