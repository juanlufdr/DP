package robots;

import java.util.LinkedList;

import laberinto.Laberinto;
import laberinto.Llave;
import laberinto.Puerta;
import laberinto.Sala;
import laberinto.LabSim.Dir;

public class Robot {
	/** Nombre del robot. */
	private String nombre;
	/** Marca del robot */
	private char id;
	/** Turno inicial con el que empieza el robot. */
	private int turno_Inicial;
	/** Último turno que tiene el robot. */
	private int turno_Actual;
	/** Sala en la que se encuentra el robot. */
	private Sala sala_Actual;
	/** Lista de las direcciones que sigue el robot para moverse. */
	private LinkedList<Dir> ruta;
	/** Lista de las llaves que tiene el robot. */
	protected LinkedList<Llave> llaves_Recogidas;

	private Dir orientacion;

	/**
	 * Método constructor parametrizado de la clase Robot
	 * 
	 * @param nombre
	 * @param id
	 * @param turno_inicial
	 * @param sala_Actual
	 */
	public Robot(String nombre, char id, int turno_inicial, Sala sala_Actual) {
		this.nombre = nombre;
		this.id = id;
		this.turno_Inicial = turno_inicial;
		this.turno_Actual = turno_inicial;
		this.sala_Actual = sala_Actual;
		this.llaves_Recogidas = new LinkedList<Llave>();
		this.ruta = new LinkedList<Dir>();

	}

	/**
	 * Devuelve el nombre del robot.
	 * 
	 * @return Nombre del robot.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Inserta el nombre pasado por parámetro al robot.
	 * 
	 * @param nombre
	 *            Nombre del robot.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la marca del robot.
	 * 
	 * @return Marca del robot.
	 */
	public char getId() {
		return id;
	}

	/**
	 * Inserta la marca pasada por parámetro al robot.
	 * 
	 * @param id
	 *            Marca del robot.
	 */
	public void setId(char id) {
		this.id = id;
	}

	/**
	 * Devuelve el turno inicial del robot.
	 * 
	 * @return Turno inicial del robot.
	 */
	public int getTurno_Inicial() {
		return turno_Inicial;
	}

	/**
	 * Inserta el turno inicial pasado por parámetro al robot.
	 * 
	 * @param turno_Inicial
	 *            Turno inicial del robot.
	 */
	public void setTurno_Inicial(int turno_Inicial) {
		this.turno_Inicial = turno_Inicial;
	}

	/**
	 * Devuelve el turno actual del robot.
	 * 
	 * @return Turno actual del robot.
	 */
	public int getTurno_Actual() {
		return turno_Actual;
	}

	/**
	 * Inserta el turno actual pasado por parámetro al robot.
	 * 
	 * @param turno_Actual
	 *            Turno actual del robot.
	 */
	public void setTurno_Actual(int turno_Actual) {
		this.turno_Actual = turno_Actual;
	}

	/**
	 * Devuelve la lista de direcciones del robot.
	 * 
	 * @return Lista de direcciones del robot.
	 */
	public LinkedList<Dir> getRuta() {
		return ruta;
	}

	/**
	 * Inicializa la lista de las direcciones del robot con la lista pasada por
	 * parámetro.
	 * 
	 * @param ruta
	 *            Lista que contiene las direcciones del robot.
	 */
	public void setRuta(LinkedList<Dir> ruta) {
		this.ruta = ruta;
	}

	/**
	 * Devuelve la sala en la que se encuentra el robot.
	 * 
	 * @return Sala actual del robot.
	 */
	public Sala getSala_Actual() {
		return sala_Actual;
	}

	/**
	 * Inserta la sala en la que se encuentra el robot pasada por parámetro.
	 * 
	 * @param sala_Actual
	 *            Sala actual del robot
	 */
	public void setSala_Actual(Sala sala_Actual) {
		this.sala_Actual = sala_Actual;
	}

	/**
	 * Devuelve la lista de las llaves del robot.
	 * 
	 * @return Lista de llaves del robot.
	 */
	public LinkedList<Llave> getLlaves_Recogidas() {
		return llaves_Recogidas;
	}

	/**
	 * Inicializa la lista de llaves del robot con la lista pasada por
	 * parámetro.
	 * 
	 * @param llaves_Recogidas
	 *            Lista que contiene las llaves del robot.
	 */
	public void setLlaves_Recogidas(LinkedList<Llave> llaves_Recogidas) {
		this.llaves_Recogidas = llaves_Recogidas;
	}

	/**
	 * Devuelve la orientación del robot.
	 * @return
	 * 		orientacion del robot
	 */
	public Dir getOrientacion() {
		return this.orientacion;
	}

	/**
	 * Inserta la orientacion del robot que se pasa por parámetro.
	 * @param orientacion
	 */
	public void setOrientacion(Dir orientacion) {
		this.orientacion = orientacion;
	}

	/**
	 * Método que realiza la acción de abrir la puerta.
	 * 
	 * @param p
	 *            Puerta que se quiere abrir.
	 */
	protected void accionPuerta(Puerta p) {
		if (!this.llaves_Recogidas.isEmpty()) {
			p.abrirPuerta(this.llaves_Recogidas.getLast());
			this.llaves_Recogidas.removeLast();
		}

	}

	/**
	 * Metodo que introduce en la LinkedList ruta las direcciones que contiene
	 * la LinkedList dir
	 * 
	 * @param dir
	 *            LinkedList que contiene los id de las salas de la ruta
	 * @param ruta
	 *            LinkedList donde se guarda las direcciones de la ruta
	 */
	public void incluirRuta(LinkedList<Integer> dir, LinkedList<Dir> ruta) {
		Laberinto lab = Laberinto.getInstance();
		int SalaActual = dir.get(0);
		int nuevaSala;
		dir.addLast(dir.poll());
		while (!dir.isEmpty()) {
			nuevaSala = dir.get(0);
			if ((lab.getSalas().get(SalaActual).getNorte() != null)
					&& (lab.getSalas().get(SalaActual).getNorte().getId_sala() == nuevaSala)) {
				ruta.addLast(Dir.N);
			} else if ((lab.getSalas().get(SalaActual).getSur() != null)
					&& (lab.getSalas().get(SalaActual).getSur().getId_sala() == nuevaSala)) {
				ruta.addLast(Dir.S);
			} else if ((lab.getSalas().get(SalaActual).getEste() != null)
					&& (lab.getSalas().get(SalaActual).getEste().getId_sala() == nuevaSala)) {
				ruta.addLast(Dir.E);
			} else if ((lab.getSalas().get(SalaActual).getOeste() != null)
					&& (lab.getSalas().get(SalaActual).getOeste().getId_sala() == nuevaSala)) {
				ruta.addLast(Dir.O);
			}
			SalaActual = nuevaSala;
			dir.remove(0);

		}
	}

	/**
	 * Movimiento que realizará el robot a la sala correspondiente dependiendo
	 * de la ruta asignada.
	 */
	private void movimiento() {

		if (!this.ruta.isEmpty()) {
			if (this.ruta.getFirst() == Dir.N) {
				if (this.sala_Actual.getNorte() != null) {
					this.sala_Actual = this.sala_Actual.getNorte();
					this.sala_Actual.getRobots().addLast(this);
				}
			}
			if (this.ruta.getFirst() == Dir.S) {
				if (this.sala_Actual.getSur() != null) {
					this.sala_Actual = this.sala_Actual.getSur();
					this.sala_Actual.getRobots().addLast(this);
				}
			}
			if (this.ruta.getFirst() == Dir.E) {
				if (this.sala_Actual.getEste() != null) {
					this.sala_Actual = this.sala_Actual.getEste();
					this.sala_Actual.getRobots().addLast(this);
				}
			}
			if (this.ruta.getFirst() == Dir.O) {
				if (this.sala_Actual.getOeste() != null) {
					this.sala_Actual = this.sala_Actual.getOeste();
					this.sala_Actual.getRobots().addLast(this);
				}
			}
			this.ruta.removeFirst();
		} else
			this.sala_Actual.insertarRobot(this);

	}

	/**
	 * Método que realiza la acción de recoger la primera llave de la sala en la
	 * que se encuentre el robot.
	 */
	protected void accionLlave() {
		if (!this.sala_Actual.getLlaves_sala().estaVacia()) {
			this.llaves_Recogidas.addLast(this.sala_Actual
					.extraerPrimeraLlave());
		}
	}

	/**
	 * Método que realiza todas las acciones del robot (accionPuerta, movimiento
	 * y accionLlave) dependiendo del turno del laberinto y del robot.
	 * 
	 * @param sala_Puerta
	 *            Sala en la que se encuentra la puerta.
	 * @param p
	 *            Puerta sobre la que se realizará la acción.
	 * @param turno
	 *            Turno del laberinto.
	 */
	public void acciones(int sala_Puerta, Puerta p, int turno) {
		if (turno == turno_Actual) {
			
			
			if (this.sala_Actual.getId_sala() == sala_Puerta) {
				if (!Laberinto.getInstance().getPuerta().estaAbierta())
					if (!this.llaves_Recogidas.isEmpty())
						if(p.abrirPuerta(this.llaves_Recogidas.pollLast())){
							Laberinto.getInstance().getsalaGanadores().insertarRobotGanador(this);
						
						}
						else
							this.sala_Actual.insertarRobot(this);
					else
						this.sala_Actual.insertarRobot(this);
				else
					Laberinto.getInstance().getsalaGanadores().insertarRobotGanador(this);
				
			} else {
				this.movimiento();
				if (!this.sala_Actual.getLlaves_sala().estaVacia()) {
					this.accionLlave();
				}
			}
			this.turno_Actual++;
		} else {
			this.sala_Actual.insertarRobot(this);
		}

	}

	/**
	 * Muestra la ruta del robot por pantalla.
	 * @return Ruta del robot.
	 */
	public String mostrarRuta() {
		String s;
		s = "ruta:" + this.id + ": ";
		for (int i = 0; i < this.ruta.size(); i++) {
			if (i == this.ruta.size() - 1)
				s = s + this.ruta.get(i);
			else
				s = s + this.ruta.get(i) + " ";
		}
		return s;
	}

	/**
	 * Muestra las llaves del robot por pantalla.
	 * @return Llaves del robot.
	 */
	public String mostrarLlaves() {
		String s;
		s = "";
		
		for (int i = this.llaves_Recogidas.size()-1; i >= 0; i--) {
		
			s = s + " " + this.llaves_Recogidas.get(i);
		}
		return s;
	}

	public String toString() {
		int turno=turno_Actual;
		if (this.turno_Actual>turno_Inicial)
			turno--;
		return ("(" + this.nombre + ":" + this.id + ":"
				+ this.sala_Actual.getId_sala() + ":" + turno
				+ ":" + this.mostrarLlaves() + ")");
	}

}
