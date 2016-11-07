package pruebas;

import static org.junit.Assert.*;


import laberinto.Laberinto;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import robots.Asimo;

public class AsimoTest {
	
	Asimo a;
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
		a = new Asimo("asimo", 'A',0, lab.salaSurEste());
		
		assertEquals( "[N, O, O, N, E, N, N, E, N, S, O, S, S, O, N, O, N, N, O, O, E, E, S, S, E, S, S, S, O, O, O, E, E, E, E, E]", a.getRuta().toString());
		
	}
	
	

}
