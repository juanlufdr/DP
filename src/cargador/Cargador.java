package cargador;

import java.util.List;

import robots.Asimo;
import robots.Bender;
import robots.Sonny;
import robots.Spirit;

import laberinto.LabSim.Dir;
import laberinto.Laberinto;
import laberinto.Sala;

/**
 * Clase creada para ser usada en la utilidad cargador contiene el main del
 * cargador. Se crea una instancia de la clase Estacion, una instancia de la
 * clase Cargador y se procesa el fichero de inicio, es decir, se leen todas las
 * líneas y se van creando todas las instancias de la simulaci�n
 * 
 * @version 1.0 - 02/11/2011
 * @author Profesores DP
 */
public class Cargador {
	/**
	 * n�mero de elementos distintos que tendr� la simulaci�n - lab, Bender,
	 * Sonny, Spirit, Asimo
	 */
	static final int NUMELTOSCONF = 5;
	/**
	 * atributo para almacenar el mapeo de los distintos elementos
	 */
	static private DatoMapeo[] mapeo;
	/**
	 * referencia a la instancia del patr�n Singleton
	 */
	private Laberinto lab;

	/**
	 * constructor parametrizado
	 */
	public Cargador() {
		lab = Laberinto.getInstance();
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0] = new DatoMapeo("LABERINTO", 5);
		mapeo[1] = new DatoMapeo("BENDER", 4);
		mapeo[2] = new DatoMapeo("SONNY", 4);
		mapeo[3] = new DatoMapeo("SPIRIT", 4);
		mapeo[4] = new DatoMapeo("ASIMO", 4);

	}

	/**
	 * busca en mapeo el elemento leído del fichero inicio.txt y devuelve la
	 * posici�n en la que est�
	 * 
	 * @param elto
	 *            elemento a buscar en el array
	 * @return res posici�n en mapeo de dicho elemento
	 */
	private int queElemento(String elto) {
		int res = -1;
		boolean enc = false;

		for (int i = 0; (i < NUMELTOSCONF && !enc); i++) {
			if (mapeo[i].getNombre().equals(elto)) {
				res = i;
				enc = true;
			}
		}
		return res;
	}

	/**
	 * método que crea las distintas instancias de la simulaci�n
	 * 
	 * @param elto
	 *            nombre de la instancia que se pretende crear
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo de la
	 *            instancia
	 */
	public void crear(String elto, int numCampos, List<String> vCampos) {
		int numElto = queElemento(elto);

		if ((numElto != -1) && (mapeo[numElto].getCampos() == numCampos)) {
			switch (queElemento(elto)) {
			case 0:
				crearLab(numCampos, vCampos);
				break;
			case 1:
				crearBender(numCampos, vCampos);
				break;
			case 2:
				crearSonny(numCampos, vCampos);
				break;
			case 3:
				crearSpirit(numCampos, vCampos);
				break;
			case 4:
				crearAsimo(numCampos, vCampos);
				break;
			}
		} else
			System.out
					.println("ERROR Cargador::crear: Datos de configuraci�n incorrectos... "
							+ elto + "," + numCampos + "\n");
	}

	/**
	 * método que crea una instancia de la clase Planta
	 * 
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearLab(int numCampos, List<String> vCampos) {

		int dimY = Integer.parseInt(vCampos.get(1));
		int dimX = Integer.parseInt(vCampos.get(2));
		int salaPuerta = Integer.parseInt(vCampos.get(3));
		int alturaArbol = Integer.parseInt(vCampos.get(4));
		lab.construirLaberinto(salaPuerta, dimX, dimY, alturaArbol);
		System.out.println("Creado lab: " + vCampos.get(1) + "\n");

	}

	/**
	 * método que crea una instancia de la clase Bender
	 * 
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearBender(int numCampos, List<String> vCampos) {
		String nombre = vCampos.get(1);
		char id = vCampos.get(2).charAt(0);
		int turno_Inicial = Integer.parseInt(vCampos.get(3));

		Bender bender = new Bender(nombre, id, turno_Inicial, lab.salaInicial());

		lab.insertarRobot(bender);

		System.out.println("Creado Bender: " + vCampos.get(1) + "\n");

	}

	/**
	 * método que crea una instancia de la clase Sonny
	 * 
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSonny(int numCampos, List<String> vCampos) {
		String nombre = vCampos.get(1);
		char id = vCampos.get(2).charAt(0);
		int turno_Inicial = Integer.parseInt(vCampos.get(3));

		Sonny sonny = new Sonny(nombre, id, turno_Inicial, lab.salaInicial());

		lab.insertarRobot(sonny);

		System.out.println("Creado Sonny: " + vCampos.get(1) + "\n");
	}

	/**
	 * método que crea una instancia de la clase Spirit
	 * 
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearSpirit(int numCampos, List<String> vCampos) {
		String nombre = vCampos.get(1);
		char id = vCampos.get(2).charAt(0);
		int turno_Inicial = Integer.parseInt(vCampos.get(3));

		Spirit spirit = new Spirit(nombre, id, turno_Inicial,
				lab.salaSuroeste());

		lab.insertarRobot(spirit);

		System.out.println("Creado Spirit: " + vCampos.get(1) + "\n");
	}

	/**
	 * método que crea una instancia de la clase Asimo
	 * 
	 * @param numCampos
	 *            n�mero de atributos que tendr� la instancia
	 * @param vCampos
	 *            array que contiene los valores de cada atributo
	 */
	private void crearAsimo(int numCampos, List<String> vCampos) {
		String nombre = vCampos.get(1);
		char id = vCampos.get(2).charAt(0);
		int turno_Inicial = Integer.parseInt(vCampos.get(3));

		Asimo asimo = new Asimo(nombre, id, turno_Inicial, lab.getSalas().getLast());
		
		lab.insertarRobot(asimo);

		System.out.println("Creado Asimo: " + vCampos.get(1) + "\n");
	}
}
