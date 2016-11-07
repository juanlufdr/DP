package pruebas;

import static org.junit.Assert.*;
import laberinto.Llave;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LlaveTest {
	Llave l;

	@Before
	public void setUp() throws Exception {
		l = new Llave(5);
	}
	
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetIdLlave() {
		assertEquals(new Integer(5), l.getIdLlave());
	}

	@Test
	public void testSetIdLlave() {
		l.setIdLlave(0);
		assertEquals(new Integer(0), l.getIdLlave());
	}

	@Test
	public void testCompareTo() {
		Llave aux = new Llave(l.getIdLlave());
		Llave aux2 = new Llave(0);
		assertNotEquals(l.compareTo(aux), l.compareTo(aux2));
	}

	@Test
	public void testToString() {
		String s;
		Llave aux = new Llave(l.getIdLlave());
		s = Integer.toString(aux.getIdLlave());
		assertEquals(l.toString(), s);
	}

	@Test
	public void testEqualsObject() {
		Llave aux = new Llave(l.getIdLlave());
		assertEquals(l,aux);
	}

}
