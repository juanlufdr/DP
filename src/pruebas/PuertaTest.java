package pruebas;

import static org.junit.Assert.*;
import laberinto.Llave;
import laberinto.Puerta;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PuertaTest {
	Puerta p = new Puerta();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAbrirPuerta() {
		assertEquals(false, p.abrirPuerta(new Llave(3)));
		p.getCerradura().insertar(new Llave(2));
		p.setAltura_Apertura(1);
		assertEquals(true, p.abrirPuerta(new Llave(2)));
	}

	@Test
	public void testEstaAbierta() {
		assertEquals(false, p.estaAbierta());
		p.getCerradura().insertar(new Llave(2));
		p.setAltura_Apertura(1);
		p.abrirPuerta(new Llave(2));
		assertEquals(true, p.estaAbierta());
	}

	@Test
	public void testCerrarPuerta() {
		p.getCerradura().insertar(new Llave(2));
		p.setAltura_Apertura(1);
		p.abrirPuerta(new Llave(2));
		assertEquals(p.getEstadoPuerta().abierta, p.getEstadoPuerta());
		p.cerrarPuerta();
		assertEquals(p.getEstadoPuerta().cerrada,p.getEstadoPuerta());
	}

	@Test
	public void testConfigurarPuerta() {
		Llave combinacion [] = {new Llave(1),new Llave(2),new Llave(3)};
		p.configurarPuerta(combinacion);
		assertEquals("1 2 3 ", p.getCombinacion().toString());
		assertEquals(p.getEstadoPuerta().abierta, p.getEstadoPuerta());
		
	}

	@Test
	public void testComprobar() {
		p.comprobar();
		p.getCerradura().insertar(new Llave(2));
		p.setAltura_Apertura(1);
		p.abrirPuerta(new Llave(2));
		p.comprobar();
		
	}

}
