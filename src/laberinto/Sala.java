package laberinto;

import java.util.LinkedList;

import robots.Robot;

import ed.List;

/**
 * Grupo: The Team Crocket
 * 
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nuńez
 * 
 */
public class Sala implements Comparable<Sala> {
	/** Identificador de la sala. */
	int id_sala;
	/** Lista de las llaves de la sala. */
	private List<Llave> llaves_sala;
	/** Lista de robots de la sala. */
	private LinkedList<Robot> robots;
	/** Sala vecina Norte de una sala. */
	private Sala norte;
	/** Sala vecina Sur de una sala. */
	private Sala sur;
	/** Sala vecina Este de una sala. */
	private Sala este;
	/** Sala vecina Oeste de una sala. */
	private Sala oeste;
	/** Lista de los robots ganadores. */
	private LinkedList<Robot> robots_Ganadores;
	/** Marca que identifica la sala. */
	private int marca;
	/** Frecuencia de la sala. */
	private int frecuencia;

	/**
	 * Metodo constructor por defecto de la clase Sala.
	 */
	public Sala() {
		this.id_sala = -1;
		this.llaves_sala = new List<Llave>();
		this.robots = new LinkedList<Robot>();
		this.norte = null;
		this.sur = null;
		this.este = null;
		this.oeste = null;
		this.robots_Ganadores = new LinkedList<Robot>();
		this.marca = -1;
		this.frecuencia = 0;

	}

	/**
	 * Metodo constructor parametrizado de la clase Sala.
	 * 
	 * @param id_sala
	 */
	public Sala(int id_sala) {
		this.id_sala = this.marca = id_sala;
		this.llaves_sala = new List<Llave>();
		this.robots = new LinkedList<Robot>();
		this.norte = null;
		this.sur = null;
		this.este = null;
		this.oeste = null;
		this.robots_Ganadores = new LinkedList<Robot>();
		this.marca = id_sala;
		this.frecuencia = 0;
	}

	/**
	 * Metodo constructor parametrizado de la clase Sala.
	 * 
	 * @param id_sala
	 * @param llaves_sala
	 * @param robots
	 * @param norte
	 * @param sur
	 * @param este
	 * @param oeste
	 */
	public Sala(int id_sala, List<Llave> llaves_sala, LinkedList<Robot> robots,
			Sala norte, Sala sur, Sala este, Sala oeste) {
		this.id_sala = id_sala;
		this.llaves_sala = llaves_sala;
		this.robots = robots;
		this.norte = norte;
		this.sur = sur;
		this.este = este;
		this.oeste = oeste;
		this.robots_Ganadores = new LinkedList<Robot>();
		this.marca = id_sala;
	}

	/**
	 * Devuelve el identificador de la sala.
	 * 
	 * @return Identificador de la sala.
	 */
	public int getId_sala() {
		return id_sala;
	}

	/**
	 * Inserta el identificador a la sala pasado por parámetro.
	 * 
	 * @param id_sala
	 *            Identificador de la sala.
	 */
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	/**
	 * Devuelve la lista de las llaves de la sala.
	 * 
	 * @return lista de llaves.
	 */
	public List<Llave> getLlaves_sala() {
		return llaves_sala;
	}

	/**
	 * Inicializa la lista de las llaves de la sala con la lista pasada por
	 * parámetro.
	 * 
	 * @param llaves_sala
	 *            Lista que contiene las llaves de la sala.
	 */
	public void setLlaves_sala(List<Llave> llaves_sala) {
		this.llaves_sala = llaves_sala;
	}

	/**
	 * Devuelve la lista de los robots de la sala.
	 * 
	 * @return lista de robots.
	 */
	public LinkedList<Robot> getRobots() {
		return robots;
	}

	/**
	 * Inicializa la lista de los robots de la sala con la lista pasada por
	 * parámetro.
	 * 
	 * @param robots
	 *            Lista que contiene los robots de la sala.
	 */
	public void setRobots(LinkedList<Robot> robots) {
		this.robots = robots;
	}

	/**
	 * Devuelve la sala Norte correspondiente de la sala.
	 * 
	 * @return sala Norte
	 */
	public Sala getNorte() {
		return norte;
	}

	/**
	 * Inserta la sala Norte pasada por parámetro.
	 * 
	 * @param norte
	 *            Sala Norte que se va a insertar
	 */
	public void setNorte(Sala norte) {
		this.norte = norte;
	}

	/**
	 * Devuelve la sala Sur correspondiente de la sala.
	 * 
	 * @return sala Sur
	 */
	public Sala getSur() {
		return sur;
	}

	/**
	 * Inserta la sala Sur pasada por parámetro.
	 * 
	 * @param sur
	 *            Sala Sur que se va a insertar
	 */
	public void setSur(Sala sur) {
		this.sur = sur;
	}

	/**
	 * Devuelve la sala Este correspondiente de la sala.
	 * 
	 * @return sala Este
	 */
	public Sala getEste() {
		return este;
	}

	/**
	 * Inserta la sala Este pasada por parámetro.
	 * 
	 * @param este
	 *            Sala Este que se va a insertar
	 */
	public void setEste(Sala este) {
		this.este = este;
	}

	/**
	 * Devuelve la sala Oeste correspondiente de la sala.
	 * 
	 * @return sala Oeste
	 */
	public Sala getOeste() {
		return oeste;
	}

	/**
	 * Inserta la sala Oeste pasada por parámetro.
	 * 
	 * @param oeste
	 *            Sala Oeste que se va a insertar
	 */
	public void setOeste(Sala oeste) {
		this.oeste = oeste;
	}
	
	/**
	 * Devuelve la marca de la sala.
	 * @return Marca de la sala.
	 */
	public int getMarca() {
		return marca;
	}

	/**
	 * Inserta la marca de la sala pasada por parámetro.
	 * @param marca
	 */
	public void setMarca(int marca) {
		this.marca = marca;
	}

	/**
	 * Inserta una llave pasada por parámetro en la lista de llaves de la sala.
	 * 
	 * @param llave
	 *            Llave que se va a insertar.
	 */
	public void insertarLlave(Llave llave) {
		this.llaves_sala.addLast(llave);
	}

	/**
	 * Devuelve la lista de los robots ganadores de la sala.
	 * 
	 * @return lista de robots ganadores.
	 */
	public LinkedList<Robot> getRobots_Ganadores() {
		return robots_Ganadores;
	}

	/**
	 * Inicializa la lista de los robots ganadores de la sala con la lista
	 * pasada por parámetro.
	 * 
	 * @param robots_Ganadores
	 *            Lista que contiene los robots ganadores de la sala.
	 */
	public void setRobots_Ganadores(LinkedList<Robot> robots_Ganadores) {
		this.robots_Ganadores = robots_Ganadores;
	}

	/**
	 * Devuelve la frecuencia de la sala.
	 * @return Frecuencia de la sala.
	 */
	public int getFrecuencia() {
		return frecuencia;
	}

	/**
	 * Inserta la frecuencia de la sala pasada por parámetro.
	 * @param frecuencia
	 */
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * Obtiene la primera llave de la lista de llaves de la sala.
	 * 
	 * @return Primera llave de la lista.
	 */
	public Llave obtenerLlave() {
		if (!this.llaves_sala.estaVacia()) {
			Llave llave = this.llaves_sala.getFirst();
			this.llaves_sala.removeFirst();
			return llave;
		} else {
			return null;
		}
	}

	/**
	 * Devuele la primera llave de la lista de llaves de la sala.
	 * 
	 * @return Primera llave de la lista.
	 */
	public Llave extraerPrimeraLlave() {
		Llave l = null;
		if (!this.llaves_sala.estaVacia()) {
			l = this.llaves_sala.getFirst();
			this.llaves_sala.removeFirst();
		}

		return l;
	}

	/**
	 * Inserta un robot en la lista de robots de la sala.
	 * 
	 * @param r
	 *            Robot que se va a insertar.
	 */
	public void insertarRobot(Robot r) {
		this.robots.addLast(r);
	}

	/**
	 * Devuelve el primer robot de la lista de robots de la sala y lo desencola.
	 * 
	 * @return Primer robot de la lista.
	 */
	public Robot obtenerRobot() {
		if (!this.robots.isEmpty()) {
			return this.robots.poll();
		} else {
			return null;
		}
	}

	/**
	 * Método que ejecuta la simulación de los robots de la sala.
	 * 
	 */
	public void procesarSala() {
		Laberinto lab = Laberinto.getInstance();
		Robot robot_Actual;
		int tam = this.getRobots().size();
		for (int i = 0; i < tam; i++) {

			robot_Actual = this.getRobots().poll();
			robot_Actual.acciones(lab.getSala_Puerta(), lab.getPuerta(),
					lab.getTurno());
			
		}

	}

	/**
	 * Método que incrementa la frecuencia de la sala.
	 */
	public void incrementarFrecuencia() {
		if (this.id_sala != 0
				|| this.id_sala != Laberinto.getInstance().getSala_Puerta())
			this.frecuencia++;
	}

	public int compareTo(Sala s) {
		if (this.frecuencia == s.getFrecuencia()) {
			if (this.id_sala < s.getId_sala())
				return -1;
			else if (this.id_sala > s.getId_sala())
				return 1;
			else
				return 0;
		} else {
			if (this.frecuencia < s.getFrecuencia()) {
				return 1;
			} else {
				return -1;
			}
		}
	}

	@Override
	public String toString() {
		return ("(sala:" + this.id_sala + ": " + this.llaves_sala + ")");
	}

	/**
	 * Método que comprueba si hay robots en la lista de robots ganadores. 
	 * @return TRUE si hay robots en la lista y FALSE en caso contrario.
	 */
	public boolean hayRobotsGanadores() {
		if (this.robots_Ganadores.isEmpty()) return false;
		
		return true;
	}

	/**
	 * Método que inserta un robot en la lista de robots ganadores.
	 * @param robot
	 */
	public void insertarRobotGanador(Robot robot) {
		this.robots_Ganadores.addLast(robot);
	}

}