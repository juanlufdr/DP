package pruebas;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import laberinto.Laberinto;
import laberinto.Llave;
import laberinto.Sala;
import laberinto.LabSim.Dir;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import robots.Bender;

public class BenderTest {

	Bender b;
	Laberinto lab;

	@Before
	public void setUp() throws Exception {
		lab = Laberinto.getInstance();
		lab.construirLaberinto(35, 6, 6, 4);
	}

	@After
	public void tearDown() throws Exception {
	}

		@Test
	public void testRuta() {
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		
		b = new Bender("bender", 'B', 0, lab.salaInicial());
		
		Set<Integer> visitadas = new LinkedHashSet<Integer>();
		
		b.rutaProfundidad(visitadas, lab.salaInicial().getId_sala(), ruta);
		
		assertEquals("[E, E, S, S, E, S, S, E, E, S]", ruta.toString());
	}

}
