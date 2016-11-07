package pruebas;

import static org.junit.Assert.*;
import laberinto.Pared;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ParedTest {
	Pared p;

	@Before
	public void setUp() throws Exception {
		p = new Pared(0, 1);
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetOrigen() {
		assertTrue((new Integer(0).equals(p.getOrigen())));
	}

	@Test
	public void testSetOrigen() {
		p.setOrigen(2);
		assertTrue((new Integer(2).equals(p.getOrigen())));
	}

	@Test
	public void testGetFin() {
		assertTrue((new Integer(1).equals(p.getFin())));

	}

	@Test
	public void testSetFin() {
		p.setFin(3);
		assertTrue((new Integer(3).equals(p.getFin())));
	}

	@Test
	public void testToString() {
		String s = ("Pared: " + "(" + p.getOrigen() + " , " + p.getFin() + ")");
		assertTrue(p.toString().equals(s));
	}

}
