package pruebas;

import static org.junit.Assert.*;

import laberinto.Llave;
import laberinto.Sala;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ed.List;

import robots.Bender;
import robots.Sonny;

public class SalaTest {

	Sala s = new Sala();
	Bender b = new Bender("BBRodríguez", 'B', 1, new Sala (0));
	Sonny so = new Sonny("YoRobot", 'S', 1, new Sala(2));
	
	@Before
	public void setUp() throws Exception {
		s = new Sala(10);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsertarLlave() {
		s.insertarLlave(new Llave(2));
		s.insertarLlave(new Llave (5));
		assertEquals(new Llave(2), s.getLlaves_sala().getFirst());
		assertEquals(new Llave (5), s.getLlaves_sala().getLast());
	
	}

	@Test
	public void testObtenerLlave() {
		s.insertarLlave(new Llave(4));
		s.insertarLlave(new Llave (7));
		assertEquals(new Llave (4), s.obtenerLlave());
		assertEquals(new Llave (7), s.obtenerLlave());
	}

	@Test
	public void testExtraerPrimeraLlave() {
		s.insertarLlave(new Llave(1));
		s.insertarLlave(new Llave (9));
		assertEquals(new Llave (1), s.extraerPrimeraLlave());
		assertEquals(new Llave (9), s.extraerPrimeraLlave());
	}

	@Test
	public void testInsertarRobot() {
		s.insertarRobot(b);
		s.insertarRobot (so);
		assertEquals(b, s.getRobots().getFirst());
		assertEquals (so,s.getRobots().getLast());
		
		
	}

	@Test
	public void testObtenerRobot() {
		s.insertarRobot(b);
		s.insertarRobot(so);
		assertEquals(b, s.obtenerRobot());
		assertEquals(so, s.obtenerRobot());
		assertEquals(true, s.getRobots().isEmpty());
	}
	
	@Test
	public void testCompareTo() {
		Sala aux = new Sala(s.getId_sala());
		Sala aux2 = new Sala (2);
		assertNotSame(s.compareTo(aux), s.compareTo(aux2));
	}
	
	@Test
	public void testToString() {
		String st;
		Sala aux = new Sala(s.getId_sala());
		List <Llave> lista = new List<Llave>();
		lista = s.getLlaves_sala();
		st = "(sala: " + Integer.toString(aux.getId_sala())+" : " + lista + ")";
		assertEquals(s.toString(), st);
	
	}
	

}
