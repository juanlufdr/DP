package pruebas;


import static org.junit.Assert.assertEquals;

import java.util.LinkedList;

import laberinto.Laberinto;
import laberinto.LabSim.Dir;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import robots.Sonny;
import robots.Spirit;

public class SonnyTest {
	Laberinto lab = Laberinto.getInstance();
	
	@Before
	public void setUp() throws Exception {
		lab.construirLaberinto(35, 6, 6, 5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRutaManoDerecha() {
		Sonny s = new Sonny("Sonny", 's', 0, lab.salaInicial());
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		s.rutaManoDerecha(ruta);
		System.out.println(ruta);
		assertEquals("[E, E, S, O, O, E, S, O, E, N, E, S, E, S, O, O, O, S, E, O, N, E, E, S, N, E, S, S, O, O, O, E, E, E, E, E]", ruta.toString());
		
	}
}
