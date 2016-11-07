package laberinto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import laberinto.LabSim.Dir;

import robots.Robot;

import ed.Grafo;
import ed.List;

/**
 * Grupo: The Team Crocket
 * 
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nuñez
 * 
 */
public class Laberinto {
	/** Dimensión X del laberinto. */
	private int dimx;
	/** Dimensión Y del laberinto. */
	private int dimy;
	/** Puerta de salida del laberinto. */
	private Puerta puerta;
	/** Lista de las salas que forman el laberinto. */
	private List<Sala> salas;
	/** Sala donde se encuentra la puerta de salida. */
	private int sala_Puerta;
	/** Turno global del laberinto. */
	private int turno;
	
	/**
	 * Sala donde se encuentran los robots ganadores (aquellos que han abierto
	 * la puerta o han salido del laberinto).
	 */
	private Sala salaGanadores;
	/**
	 * Método estatico de laberinto utilizado para el patrón de diseño Singleton
	 */
	private static Laberinto INSTANCE = null;
	/**
	 * Atributo utilizado para escribir en un fichero los resultados de la
	 * simulacion
	 */
	private PrintWriter pw;
	/**
	 * Atributo utilizado para escribir en un fichero los resultados de la
	 * simulacion
	 */
	private FileWriter fichero;
	/** Grafo donde se guarda información sobre las paredes del laberinto */
	private Grafo grafo;

	/**
	 * Metodo constructor por defecto de la clase Laberinto.
	 */
	private Laberinto() {
		this.dimx = this.dimy = this.sala_Puerta = -1;
		this.puerta = new Puerta();
		this.salas = new List<Sala>();
		this.salaGanadores = new Sala(1111);
		this.grafo = new Grafo();

	}

	/**
	 * Metodo constructor parametrizado de la clase Laberinto.
	 * 
	 * @param salaPuerta
	 * @param dimX
	 * @param dimY
	 * @param alturaArbol
	 */
	public void construirLaberinto(int salaPuerta, int dimX, int dimY,
			int alturaArbol) {
		this.dimx = dimX;
		this.dimy = dimY;
		this.puerta = new Puerta();
		this.puerta.setAltura_Apertura(alturaArbol);
		this.fichero = null;
		this.pw = null;
		this.salas = new List<Sala>();
		for (int i = 0; i < ((this.dimx * this.dimy)); i++) {
			this.salas.addOrder(new Sala(i));
		}
		this.ponerVecinas();
		this.sala_Puerta = salaPuerta;
		this.salaGanadores = new Sala(1111);

		Llave[] conjuntoLlaves = new Llave[15];
		int iterador = 0;
		for (int i = 0; i < 30; i++) {
			if (i % 2 != 0) {
				conjuntoLlaves[iterador] = new Llave(i);
				iterador++;
			}
		}

		Llave[] combinacion = new Llave[conjuntoLlaves.length];

		this.generarCombinacion(conjuntoLlaves, 0, (conjuntoLlaves.length - 1),
				combinacion, 0);

		Puerta p = new Puerta();
		p.setAltura_Apertura(alturaArbol);
		p.configurarPuerta(combinacion);
		p.cerrarPuerta();
		insertarPuerta(p, salaPuerta);

		for (int i = 0; i < ((this.dimx * this.dimy)); i++) {
			grafo.nuevoNodo(i);
		}

		this.kruskal();
		this.grafo.warshall();
		this.grafo.floyd();
		this.atajos();
		this.grafo.warshall();
		this.grafo.floyd();

		Set<Integer> visitadas = new LinkedHashSet<Integer>();
		this.calcularFrecuencia(visitadas, new Integer(0));
		this.insertarLlaves();
	}

	/**
	 * Metodo constructor parametrizado de la clase Laberinto.
	 * 
	 * @param dimx
	 * @param dimy
	 */
	public void ponerDimensiones(int dimx, int dimy) {
		this.dimx = dimx;
		this.dimy = dimy;
		this.salas = new List<Sala>();
		this.salaGanadores = new Sala(1111);
		for (int i = 0; i < ((this.dimx * this.dimy)); i++) {
			salas.addOrder(new Sala(i));
		}
		this.ponerVecinas();
		this.sala_Puerta = (this.dimx * this.dimy) - 1;
	}

	/**
	 * Inserta las salas vecinas (Norte, Sur, Este y Oeste) correspondientes a
	 * cada sala del laberinto.
	 * 
	 */
	public void ponerVecinas() {
		int tam = dimx * dimy;
		for (int i = 0; i < this.salas.size(); i++) {
			if (i - dimy >= 0) {
				this.salas.get(i).setNorte(this.salas.get(i - dimy));
			}
			if (i + dimy < tam) {
				this.salas.get(i).setSur(this.salas.get(i + dimy));
			}
			if ((i - 1) > -1 && (i % dimy) != 0) {
				this.salas.get(i).setOeste(this.salas.get(i - 1));
			}
			if ((i + 1) < tam && ((i + 1) % dimy) != 0) {
				this.salas.get(i).setEste(this.salas.get(i + 1));

			}

		}
	}

	/**
	 * Método utilizado para obtener la instancia de la clase Laberinto (Patron
	 * de diseño Singleto)
	 * 
	 * @return Instancia de la clase Laberinto
	 */
	public static Laberinto getInstance() {
		if (INSTANCE == null)
			INSTANCE = new Laberinto();
		return INSTANCE;
	}

	/**
	 * Devuelve la lista de las salas del laberinto.
	 * 
	 * @return lista de salas.
	 */
	public List<Sala> getSalas() {
		return salas;
	}

	/**
	 * Inicializa la lista de las salas del laberinto con la lista pasada por
	 * parámetro.
	 * 
	 * @param salas
	 *            Lista que contiene las salas del laberinto.
	 */
	public void setSalas(List<Sala> salas) {
		this.salas = salas;
	}

	/**
	 * Devuelve la dimensión X del laberinto.
	 * 
	 * @return Dimensión X del laberinto.
	 */
	public int getDimx() {
		return dimx;
	}

	/**
	 * Inserta la dimensión X del laberinto pasada por parámetro.
	 * 
	 * @param dimx
	 *            Dimensión X del laberinto.
	 */
	public void setDimx(int dimx) {
		this.dimx = dimx;
	}

	/**
	 * Devuelve la dimensión Y del laberinto.
	 * 
	 * @return Dimensión Y del laberinto.
	 */
	public int getDimy() {
		return dimy;
	}

	/**
	 * Inserta la dimensión Y del laberinto pasada por parámetro.
	 * 
	 * @param dimy
	 *            Dimensión Y del laberinto.
	 */
	public void setDimy(int dimy) {
		this.dimy = dimy;
	}

	/**
	 * Devuelve una puerta del laberinto.
	 * 
	 * @return Puerta del laberinto.
	 */
	public Puerta getPuerta() {
		return puerta;
	}

	/**
	 * Inserta una puerta pasada por parámetro.
	 * 
	 * @param puerta
	 *            Puerta del laberinto.
	 */
	public void setPuerta(Puerta puerta) {
		this.puerta = puerta;
	}

	/**
	 * Devuelve la sala donde se encuentra una puerta del laberinto.
	 * 
	 * @return Sala de la puerta de salida del laberinto.
	 */
	public int getSala_Puerta() {
		return sala_Puerta;
	}

	/**
	 * Inserta una sala donde se encuentra una puerta del laberinto.
	 * 
	 * @param sala_Puerta
	 *            Sala donde se encuentra una puerta del laberinto.
	 */
	public void setSala_Puerta(int sala_Puerta) {
		this.sala_Puerta = sala_Puerta;
	}

	/**
	 * Devuelve el turno global del laberinto.
	 * 
	 * @return Turno del laberinto.
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Inserta el turno del laberinto pasado por parámetro.
	 * 
	 * @param turno
	 *            Turno del laberinto.
	 */
	public void setTurno(int turno) {
		this.turno = turno;
	}

	/**
	 * Devuelve la sala donde se encuentran los robots ganadores.
	 * 
	 * @return Sala de los robots ganadores.
	 */
	public Sala getsalaGanadores() {
		return salaGanadores;
	}

	/**
	 * Inserta la sala de los robots ganadores pasada por parámetro.
	 * 
	 * @param salaGanadores
	 *            Sala de los robots ganadores.
	 */
	public void setSalaGanadores(Sala salaGanadores) {
		this.salaGanadores = salaGanadores;
	}

	/**
	 * Método que devuelve el grafo que contiene la informacion sobre las
	 * paredes del laberinto
	 * 
	 * @return Grafo del laberinto
	 */
	public Grafo getGrafo() {
		return grafo;
	}

	/**
	 * Inserta en orden una sala pasada por parámetro en la lista de salas del
	 * laberinto.
	 * 
	 * @param s
	 *            Sala que se va a insertar.
	 */
	public void insertarSala(Sala s) {
		this.salas.addOrder(s);
	}

	/**
	 * Devuelve la sala con identificador 0 del laberinto.
	 * 
	 * @return Sala con identificador 0.
	 */
	public Sala salaInicial() {
		return this.salas.get(0);
	}

	/**
	 * Devuelve la sala suroeste (esquina inferior izquierda) del laberinto.
	 * 
	 * @return Sala suroeste del laberinto.
	 */
	public Sala salaSuroeste() {
		return this.salas.get(((dimx * dimy) - dimy));
	}

	/**
	 * Devuelve la sala sureste (esquina inferior derecha) del laberinto.
	 * 
	 * @return Sala sureste del laberinto.
	 */
	public Sala salaSurEste() {
		return this.salas.get(dimx * dimy - 1);
	}

	/**
	 * Devuelve la sala noroeste (esquina superior izquierda) del laberinto.
	 * 
	 * @return Sala noroeste del laberinto.
	 */
	public Sala salaNorOeste() {
		return this.salas.get(0);
	}

	/**
	 * Devuelve la sala noreeste (esquina superior derecha) del laberinto.
	 * 
	 * @return Sala noreste del laberinto.
	 */
	public Sala salaNorEste() {
		return this.salas.get(dimy - 1);
	}

	/**
	 * Devuelve la sala de salida (sala con mayor identificador) del laberinto.
	 * 
	 * @return Sala de salida del laberinto.
	 */
	public Sala salaSalida() {
		return this.salas.get((dimx * dimy) - 1);
	}

	/**
	 * Muestra las salas del laberinto y sus correspondientes salas vecinas por
	 * pantalla.
	 * 
	 */
	public void mostrar() {
		for (int i = 0; i < this.salas.size(); i++) {
			System.out.println("Sala " + i);
			System.out.println("Norte : " + this.salas.get(i).getNorte());
			System.out.println("Sur : " + this.salas.get(i).getSur());
			System.out.println("Este : " + this.salas.get(i).getEste());
			System.out.println("Oeste : " + this.salas.get(i).getOeste());

			System.out.println("----------");

		}
	}

	/**
	 * Inserta una puerta en el laberinto
	 * 
	 * @param p
	 *            Puerta que se insertará en el laberinto
	 * 
	 * @param salaPuerta
	 *            Sala en la que se insertará la puerta
	 */
	public void insertarPuerta(Puerta p, int salaPuerta) {
		this.setPuerta(p);
		this.setSala_Puerta(salaPuerta);
	}

	/**
	 * Genera una combinacion para la cerradura de la puerta
	 * 
	 * @param conjuntoLlaves
	 *            array con un conjunto de llaves que formaran la cerradura
	 * @param minimo
	 *            capacidad minima del array
	 * @param maximo
	 *            numero maximo de llaves del array
	 * @param combinacion
	 *            array que contendra la combinacion final de la cerradura
	 * @param i
	 *            contador utilizado para insertar en el array i
	 * @return numero de elementos totales
	 */
	public int generarCombinacion(Llave[] conjuntoLlaves, int minimo,
			int maximo, Llave[] combinacion, int i) {
		int mitad = (maximo - minimo) / 2 + minimo;
		combinacion[i] = conjuntoLlaves[mitad];
		i++;
		int auxder = i, auxizq = i;
		if (mitad > minimo) {
			auxizq = generarCombinacion(conjuntoLlaves, minimo, mitad - 1,
					combinacion, i);
			auxder = generarCombinacion(conjuntoLlaves, mitad + 1, maximo,
					combinacion, auxizq);
		}
		return auxder;
	}

	/**
	 * Inserta un robot pasado por parámetro en la sala actual del laberinto.
	 * 
	 * @param r
	 *            Robot que se va a insertar
	 */
	public void insertarRobot(Robot r) {
		r.getSala_Actual().insertarRobot(r);

	}

	/**
	 * Método que pinta el laberinto en 2D incluyendo la marca del robot
	 * correspondiente en cada sala.
	 * 
	 * @return Laberinto en 2D.
	 */
	public String pintarLaberinto() {
		String s = "";

		for (int i = 0; i < this.dimy; i++) {
			s = s + " _";
		}

		s = s + "\n";

		int indice_sala = 0;
		Sala sala;
		for (int i = 0; i < this.dimx; i++) {
			for (int j = 0; j < this.dimy; j++) {
				indice_sala = i * this.dimy + j;
				sala = salas.get(indice_sala);
				if (!sala.getRobots().isEmpty()) {
					if (sala.getRobots().size() == 1) {
						if ((sala.getOeste() != null)
								&& (this.grafo.adyacente(indice_sala, sala
										.getOeste().getId_sala()))) {
							s = s + " " + sala.getRobots().getFirst().getId();
						} else {
							s = s + "|" + sala.getRobots().getFirst().getId();
						}
					} else {
						if ((sala.getOeste() != null)
								&& (this.grafo.adyacente(indice_sala, sala
										.getOeste().getId_sala()))) {
							s = s + " " + sala.getRobots().size();
						} else {
							s = s + "|" + sala.getRobots().size();
						}
					}

				} else {
					if ((sala.getOeste() != null)
							&& (this.grafo.adyacente(indice_sala, sala
									.getOeste().getId_sala()))) {
						if ((sala.getSur() != null)
								&& (this.grafo.adyacente(indice_sala, sala
										.getSur().getId_sala())))
							s = s + "  ";
						else
							s = s + " _";
					} else {
						if ((sala.getSur() != null)
								&& (grafo.adyacente(indice_sala, sala.getSur()
										.getId_sala())))
							s = s + "| ";
						else
							s = s + "|_";
					}

				}
			}
			s = s + "|\n";
		}
		return s;
	}

	/**
	 * Método que pinta el laberinto en 2D sin incluir la marca de los robots.
	 * @return Laberinto en 2D
	 */
	public String pintarLaberintoSinRobots() {
		String s = "";

		for (int i = 0; i < this.dimy; i++) {
			s = s + " _";
		}

		s = s + "\n";

		int indice_sala = 0;
		Sala sala;
		for (int i = 0; i < this.dimx; i++) {
			for (int j = 0; j < this.dimy; j++) {
				indice_sala = i * this.dimy + j;
				sala = salas.get(indice_sala);
				if ((sala.getOeste() != null)
						&& (this.grafo.adyacente(indice_sala, sala.getOeste()
								.getId_sala()))) {
					if ((sala.getSur() != null)
							&& (this.grafo.adyacente(indice_sala, sala.getSur()
									.getId_sala())))
						s = s + "  ";
					else
						s = s + " _";
				} else {
					if ((sala.getSur() != null)
							&& (grafo.adyacente(indice_sala, sala.getSur()
									.getId_sala())))
						s = s + "| ";
					else
						s = s + "|_";
				}

			}
			s = s + "|\n";
		}

		return s;
	}

	/**
	 * Método que escribe en fichero las salas con llaves.
	 * 
	 */
	public void escribirSalasConLlaves() {
		for (int i = 0; i < this.salas.size(); i++) {
			if (!this.salas.get(i).getLlaves_sala().estaVacia()) {
				System.out.println(this.salas.get(i));
				this.escribirEnFichero(this.salas.get(i).toString()+"\n");
			}
		}
	}

	/**
	 * Método que escribe en fichero las rutas de los robots.
	 * 
	 */
	public void escribirRutaRobots() {
		LinkedList<Robot> aux = new LinkedList<Robot>();
		Robot robot_aux;
		for (int i = 0; i < this.salas.size(); i++) {
			if (!this.salas.get(i).getRobots().isEmpty()) {
				aux.addAll(this.salas.get(i).getRobots());
				while (!aux.isEmpty()) {
					robot_aux = aux.poll();
					System.out.println("("+robot_aux.mostrarRuta()+")");
					this.escribirEnFichero("("+robot_aux.mostrarRuta()+ ")\n");
					
				}
			}
		}
		System.out.println();
		this.escribirEnFichero("\n");
	}

	/**
	 * Método que ejecuta la simulación del laberinto con el turno pasado por
	 * parámetro.
	 * 
	 * @param turno_Laberinto
	 *            Turno del laberinto en el que se ejecuta la simulación.
	 */
	public void procesar(int turno_Laberinto) {
		Sala sala;
		this.turno = turno_Laberinto;
		for (int i = 0; i < this.salas.size(); i++) {
			sala = this.salas.get(i);
			sala.procesarSala();
		}

	}

	/**
	 * Método que abre el fichero de log
	 * 
	 * @param nombre
	 *            Nombre del fichero de log
	 */
	public void abrirFichero(String nombre) {
		try {
			fichero = new FileWriter(nombre);
			pw = new PrintWriter(fichero);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que cierra el fichero de log
	 */
	public void cerrarFichero() {
		try {
			pw.close();
			fichero.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que escribe en el fichero de log
	 * 
	 * @param s
	 *            string que contiene el texto a escribir en el fichero de log
	 */
	public void escribirEnFichero(String s) {
		if (pw != null)
			this.pw.print(s);
	}

	/**
	 * Método que devuelve las paredes existentes en el laberinto
	 * 
	 * @return LinkedList<Pared> con las paredes que existen en el laberinto
	 */
	public LinkedList<Pared> generarParedes() {
		LinkedList<Pared> paredes = new LinkedList<Pared>();

		for (int i = 0; i < ((this.dimx * this.dimy)); i++) {
			if (salas.get(i).getNorte() != null) {
				paredes.addLast(new Pared(i, i - this.dimy));
			}
			if (salas.get(i).getEste() != null) {
				paredes.addLast(new Pared(i, i + 1));
			}
			if (salas.get(i).getSur() != null) {
				paredes.addLast(new Pared(i, i + this.dimy));
			}
			if (salas.get(i).getOeste() != null) {
				paredes.addLast(new Pared(i, i - 1));
			}
		}

		return paredes;
	}

	/**
	 * Método que elimina paredes del laberinto hasta que la marca de todas las
	 * salas sea la misma
	 * 
	 */
	public void kruskal() {
		LinkedList<Pared> paredes = this.generarParedes();
		Pared pared;
		int random;
		int marca_origen, marca_fin;
		while (!paredes.isEmpty()) {
			random = GenAleatorios.generarNumero(paredes.size());
			pared = paredes.remove(random);
			marca_origen = salas.get(pared.getOrigen()).getMarca();
			marca_fin = salas.get(pared.getFin()).getMarca();
			if (marca_origen != marca_fin) {
				for (int i = 0; i < salas.size(); i++) {
					if (salas.get(i).getMarca() == marca_fin) {
						salas.get(i).setMarca(marca_origen);
					}
				}
				this.grafo.nuevoArco(pared.getOrigen(), pared.getFin(), 1);
				this.grafo.nuevoArco(pared.getFin(), pared.getOrigen(), 1);

			}

		}

	}

	/**
	 * Método que elimina un 5% de las paredes existentes en el laberinto
	 */
	public void atajos() {
		int cont = 0;
		int salaRandom;
		int numParedes = (this.dimx * this.dimy) * 5;
		numParedes = numParedes / 100;
		while (cont < numParedes) {
			salaRandom = GenAleatorios.generarNumero((this.dimx * this.dimy));
			if (salas.get(salaRandom).getNorte() != null
					&& atajosNorte(salaRandom)) {
				cont++;
			} else if (salas.get(salaRandom).getSur() != null
					&& atajosSur(salaRandom)) {
				cont++;
			} else if (salas.get(salaRandom).getOeste() != null
					&& atajosOeste(salaRandom)) {
				cont++;
			} else if (salas.get(salaRandom).getEste() != null
					&& atajosEste(salaRandom)) {
				cont++;
			}
		}
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared al norte de la sala acual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioNorte(int salaRandom) {
		return (espacioVacioNorteIzq(salaRandom) || espacioVacioNorteDer(salaRandom));
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared al sur de la sala acual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioSur(int salaRandom) {
		return (espacioVacioSurIzq(salaRandom) || espacioVacioSurDer(salaRandom));
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared al oeste de la sala acual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioOeste(int salaRandom) {
		return (espacioVacioOesteArriba(salaRandom) || espacioVacioOesteAbajo(salaRandom));
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared al este de la sala acual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioEste(int salaRandom) {
		return (espacioVacioEsteArriba(salaRandom) || espacioVacioEsteAbajo(salaRandom));
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte superior del este de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioEsteArriba(int salaRandom) {
		if (salas.get(salaRandom).getNorte() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaNorte = salas.get(salaRandom).getNorte().getId_sala();
			int salaEste = salas.get(salaRandom).getEste().getId_sala();
			int salaNoreste = salas.get(salaRandom).getNorte().getEste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaNorte)
					&& grafo.adyacente(salaNorte, salaNoreste) && grafo
						.adyacente(salaEste, salaNoreste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte inferior del este de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioEsteAbajo(int salaRandom) {
		if (salas.get(salaRandom).getSur() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaSur = salas.get(salaRandom).getSur().getId_sala();
			int salaEste = salas.get(salaRandom).getEste().getId_sala();
			int salaSureste = salas.get(salaRandom).getSur().getEste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaSur)
					&& grafo.adyacente(salaSur, salaSureste) && grafo
						.adyacente(salaEste, salaSureste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte superior del oeste de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioOesteArriba(int salaRandom) {
		if (salas.get(salaRandom).getNorte() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaNorte = salas.get(salaRandom).getNorte().getId_sala();
			int salaOeste = salas.get(salaRandom).getOeste().getId_sala();
			int salaNoroeste = salas.get(salaRandom).getNorte().getOeste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaNorte)
					&& grafo.adyacente(salaNorte, salaNoroeste) && grafo
						.adyacente(salaOeste, salaNoroeste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte inferior del oeste de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioOesteAbajo(int salaRandom) {
		if (salas.get(salaRandom).getSur() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaSur = salas.get(salaRandom).getSur().getId_sala();
			int salaOeste = salas.get(salaRandom).getOeste().getId_sala();
			int salaSuroeste = salas.get(salaRandom).getSur().getOeste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaSur)
					&& grafo.adyacente(salaSur, salaSuroeste) && grafo
						.adyacente(salaOeste, salaSuroeste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte izquierda del sur de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioSurIzq(int salaRandom) {
		if (salas.get(salaRandom).getOeste() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaSur = salas.get(salaRandom).getSur().getId_sala();
			int salaOeste = salas.get(salaRandom).getOeste().getId_sala();
			int salaSuroeste = salas.get(salaRandom).getSur().getOeste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaOeste)
					&& grafo.adyacente(salaOeste, salaSuroeste) && grafo
						.adyacente(salaSur, salaSuroeste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte derecha del sur de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioSurDer(int salaRandom) {
		if (salas.get(salaRandom).getEste() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaSur = salas.get(salaRandom).getSur().getId_sala();
			int salaEste = salas.get(salaRandom).getEste().getId_sala();
			int salaSureste = salas.get(salaRandom).getSur().getEste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaEste)
					&& grafo.adyacente(salaEste, salaSureste) && grafo
						.adyacente(salaSur, salaSureste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte izquierda del norte de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioNorteIzq(int salaRandom) {
		if (salas.get(salaRandom).getOeste() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaNorte = salas.get(salaRandom).getNorte().getId_sala();
			int salaOeste = salas.get(salaRandom).getOeste().getId_sala();
			int salaNoroeste = salas.get(salaRandom).getNorte().getOeste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaOeste)
					&& grafo.adyacente(salaOeste, salaNoroeste) && grafo
						.adyacente(salaNorte, salaNoroeste));
		}
		return false;
	}

	/**
	 * Método que comprueba si al eliminar una pared se crea un espacio de 4
	 * salas sin pared en la parte derecha del norte de la sala actual
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si hay espacio vacio, false en caso contrario
	 */
	public boolean espacioVacioNorteDer(int salaRandom) {
		if (salas.get(salaRandom).getEste() != null) {
			int salaActual = salas.get(salaRandom).getId_sala();
			int salaNorte = salas.get(salaRandom).getNorte().getId_sala();
			int salaEste = salas.get(salaRandom).getEste().getId_sala();
			int salaNoreste = salas.get(salaRandom).getNorte().getEste()
					.getId_sala();
			return (grafo.adyacente(salaActual, salaEste)
					&& grafo.adyacente(salaEste, salaNoreste) && grafo
						.adyacente(salaNorte, salaNoreste));
		}
		return false;
	}

	/**
	 * Método que comprueba si se puede eliminar una pared al norte de la sala
	 * actual sin crear un espacio vacio de 4 salas sin pared
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si se puede eliminar la pared, false en caso contrario
	 */
	public boolean atajosNorte(int salaRandom) {
		boolean aux = false;
		if (!grafo.adyacente(salas.get(salaRandom).getId_sala(),
				salas.get(salaRandom).getNorte().getId_sala())) {
			if (!espacioVacioNorte(salaRandom)) {
				grafo.nuevoArco(salas.get(salaRandom).getId_sala(),
						salas.get(salaRandom).getNorte().getId_sala(), 1);
				grafo.nuevoArco(salas.get(salaRandom).getNorte().getId_sala(),
						salas.get(salaRandom).getId_sala(), 1);
				aux = true;
			}
		}
		return aux;
	}

	/**
	 * Método que comprueba si se puede eliminar una pared al sur de la sala
	 * actual sin crear un espacio vacio de 4 salas sin pared
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si se puede eliminar la pared, false en caso contrario
	 */
	public boolean atajosSur(int salaRandom) {
		boolean aux = false;
		if (!grafo.adyacente(salas.get(salaRandom).getId_sala(),
				salas.get(salaRandom).getSur().getId_sala())) {
			if (!espacioVacioSur(salaRandom)) {
				grafo.nuevoArco(salas.get(salaRandom).getId_sala(),
						salas.get(salaRandom).getSur().getId_sala(), 1);
				grafo.nuevoArco(salas.get(salaRandom).getSur().getId_sala(),
						salas.get(salaRandom).getId_sala(), 1);
				aux = true;
			}
		}
		return aux;
	}

	/**
	 * Método que comprueba si se puede eliminar una pared al oeste de la sala
	 * actual sin crear un espacio vacio de 4 salas sin pared
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si se puede eliminar la pared, false en caso contrario
	 */
	public boolean atajosOeste(int salaRandom) {
		boolean aux = false;
		if (!grafo.adyacente(salas.get(salaRandom).getId_sala(),
				salas.get(salaRandom).getOeste().getId_sala())) {
			if (!espacioVacioOeste(salaRandom)) {
				grafo.nuevoArco(salas.get(salaRandom).getId_sala(),
						salas.get(salaRandom).getOeste().getId_sala(), 1);
				grafo.nuevoArco(salas.get(salaRandom).getOeste().getId_sala(),
						salas.get(salaRandom).getId_sala(), 1);
				aux = true;
			}
		}
		return aux;
	}

	/**
	 * Método que comprueba si se puede eliminar una pared al este de la sala
	 * actual sin crear un espacio vacio de 4 salas sin pared
	 * 
	 * @param salaRandom
	 *            id de la sala a comprobar
	 * @return True si se puede eliminar la pared, false en caso contrario
	 */
	public boolean atajosEste(int salaRandom) {
		boolean aux = false;
		if (!grafo.adyacente(salas.get(salaRandom).getId_sala(),
				salas.get(salaRandom).getEste().getId_sala())) {
			if (!espacioVacioEste(salaRandom)) {
				grafo.nuevoArco(salas.get(salaRandom).getId_sala(),
						salas.get(salaRandom).getEste().getId_sala(), 1);
				grafo.nuevoArco(salas.get(salaRandom).getEste().getId_sala(),
						salas.get(salaRandom).getId_sala(), 1);
				aux = true;
			}
		}
		return aux;
	}

	/**
	 * Método que calcula las salas con mayor frecuencia de paso.
	 * 
	 * @param visitadas
	 * @param ultimaSala
	 * @return Devuelve TRUE cuando encuentra las salas con mayor frecuencia y FALSE en caso contrario.
	 */
	public boolean calcularFrecuencia(Set<Integer> visitadas, Integer ultimaSala) {
		Set<Integer> adyacentes = new LinkedHashSet<Integer>();
		if (ultimaSala.equals(this.sala_Puerta)) {
			aumentarFrecuencia(visitadas);
			return true;
		}
		this.grafo.adyacentes(ultimaSala, adyacentes);
		for (Integer n : adyacentes) {
			if (!visitadas.contains(n)) {
				visitadas.add(n);
				calcularFrecuencia(visitadas, n);
				visitadas.remove(n);
			}
		}

		return false;
	}

	/**
	 * Método que incrementa la frecuencia de las salas visitadas.
	 * @param visitadas
	 */
	public void aumentarFrecuencia(Set<Integer> visitadas) {
		for (Integer n : visitadas) {
			if (n != 0 && n != this.sala_Puerta) {
				this.salas.get(n).incrementarFrecuencia();
			}
		}
	}

	/**
	 * Inserta el conjunto de llaves del laberinto en las salas con mayor frecuencia.
	 */
	public void insertarLlaves() {
		LinkedList<Llave> conjuntoLlaves = new LinkedList<Llave>();
		for (int i = 0; i < 30; i++) {
			conjuntoLlaves.addLast(new Llave(i));
			if (i % 2 != 0) {
				conjuntoLlaves.addLast(new Llave(i));
			}
		}

		List<Sala> salas_frecuencia = new List<Sala>();

		for (int i = 0; i < this.salas.size(); i++) {
			salas_frecuencia.addOrder(this.salas.get(i));
		}

		int cont = 0;
		Sala s;
		while (cont < 9) {
			s = salas_frecuencia.getFirst();
			for (int i = 0; i < 5; i++) {
				this.salas.get(s.getId_sala()).insertarLlave(
						conjuntoLlaves.poll());
			}
			salas_frecuencia.removeFirst();
			cont++;
		}

	}
	
	/**
	 * Método que devuelve la sala correspondiente dependiendo de la sala actual y de la dirección a la que se quiera ir.
	 * @param salaActual
	 * @param direccion
	 * @return Id de la sala correspondiente.
	 */
	public int puedoDir(int salaActual, Dir direccion) {
		switch (direccion) {
		case N:
			if (grafo.adyacente(salaActual, salaActual - dimy))
				return salaActual - dimy;
			break;
		case E:

			if (grafo.adyacente(salaActual, salaActual + 1))
				return salaActual + 1;
			break;
		case S:

			if (grafo.adyacente(salaActual, salaActual + dimy))
				return salaActual + dimy;
			break;
		case O:

			if (grafo.adyacente(salaActual, salaActual - 1))
				return salaActual - 1;
			break;

		}

		return salaActual;
	}
	
	/**
	 * Método que devuelve la dirección correspondiente entre dos salas.
	 * @param origen
	 * @param destino
	 * @return Dirección entre la sala origen y la sala destino.
	 */
	public Dir getDireccion (int origen, int destino) {
	
		if (salas.get(origen).getNorte() != null && salas.get(origen).getNorte().getId_sala() == destino) {
			return Dir.N;
		}
		if (salas.get(origen).getEste() != null && salas.get(origen).getEste().getId_sala() == destino){
			return Dir.E;
		}
		if (salas.get(origen).getOeste() != null && salas.get(origen).getOeste().getId_sala() == destino){
			return Dir.O;
		}
		if (salas.get(origen).getSur() != null && salas.get(origen).getSur().getId_sala() == destino){
			return Dir.S;
		}
		
		return null;
		
	}

	public String toString() {
		String s;
		s = "(turno:" + this.turno + ")\n";
		s = s + "(laberinto:" + ((dimx * dimy) - 1) + ")\n";
		s = s + this.puerta + "\n";
		s = s + pintarLaberinto();

		
		for (int i = 0; i < this.salas.size(); i++) {
			if (!this.salas.get(i).getLlaves_sala().estaVacia()) {
				s = s + this.salas.get(i) + "\n";
			}
		}

		
		Sala sala;
		for (int i = 0; i < this.salas.size(); i++) {
			sala = this.salas.get(i);
			for (int j = 0; j < sala.getRobots().size(); j++) {
				s = s + sala.getRobots().get(j) + "\n";
			}

		}



		if (salaGanadores.hayRobotsGanadores()) {
			s = s + "(robotsganadores)\n";
			Robot aux = salaGanadores.getRobots_Ganadores().getFirst();
			s = s + "("+aux.getNombre() + ":ganador:"
					+ aux.getId() + ":" + salaGanadores.getId_sala() + ":" + (aux.getTurno_Actual()-1) + ":"
					+ aux.mostrarLlaves() + ")\n";
			for(int i=1; i < salaGanadores.getRobots_Ganadores().size();i++) {
				aux = salaGanadores.getRobots_Ganadores().get(i);
				s = s +"("+ aux.getNombre() + ":" + salaGanadores.getId_sala()
						+ ":" + aux.getId() + ":" + aux.getLlaves_Recogidas()
						+ ")\n";
				i++;
			}
		}

		return s;

	}

}
