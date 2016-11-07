package robots;

import java.util.LinkedList;
import java.util.Set;

import laberinto.LabSim.Dir;
import laberinto.Laberinto;
import laberinto.Llave;
import laberinto.Puerta;
import laberinto.Sala;

public class Asimo extends Robot {

	/**
	 * Método constructor parametrizado de la clase Asimo.
	 * 
	 * @param nombre
	 * @param id
	 * @param turno_inicial
	 * @param sala_Actual
	 */
	public Asimo(String nombre, char id, int turno_inicial, Sala sala_Actual) {

		super(nombre, id, turno_inicial, sala_Actual);

		Llave[] conjuntoLlaves = new Llave[15];
		int iterador = 0;
		for (int i = 0; i < 30; i++) {
			if (i % 2 != 0) {
				conjuntoLlaves[iterador] = new Llave(i);
				iterador++;
			}
		}

		for (int i = 0; i < conjuntoLlaves.length; i++) {
			Llave dato = conjuntoLlaves[i];
			llaves_Recogidas.addLast(dato);

		}
		
		this.asignarRuta();
	}

	/**
	 * Método que realiza todas las acciones del robot Asimo (accionPuerta,
	 * movimiento y accionLlave) dependiendo del turno del laberinto y del
	 * robot.
	 * 
	 * @param sala_Puerta
	 *            Sala en la que se encuentra la puerta.
	 * @param p
	 *            Puerta sobre la que se realizará la acción.
	 * @param turno
	 *            Turno del laberinto.
	 */
	public void acciones(int sala_Puerta, Puerta p, int turno) {
		if (turno == super.getTurno_Actual()) {

			if (super.getSala_Actual().getId_sala() == sala_Puerta) {
				this.accionPuerta(p);
				this.movimiento();
			} else {
				this.movimiento();
				this.accionLlave();

			}
			super.setTurno_Actual(super.getTurno_Actual() + 1);
		} else
			super.getSala_Actual().insertarRobot(this);

	}

	/**
	 * Método que realiza la acción de cerrar la puerta.
	 * 
	 * @param p
	 *            Puerta que se quiere cerrar.
	 */
	protected void accionPuerta(Puerta p) {
		if (p.estaAbierta()) {
			p.cerrarPuerta();
		}
	}

	/**
	 * Método que realiza la acción de soltar la última llave de la lista de
	 * llaves de Asimo en salas pares.
	 */
	protected void accionLlave() {
		if ((this.getSala_Actual().getId_sala() % 2) == 0) {
			this.getSala_Actual().getLlaves_sala()
					.addOrder(this.getLlaves_Recogidas().getLast());
			this.getLlaves_Recogidas().removeLast();

		}

	}
	
	/**
	 * Movimiento que realizará el robot a la sala correspondiente dependiendo de la ruta asignada.
	 */
	private void movimiento() {
		
		if (!super.getRuta().isEmpty()) {
			if (super.getRuta().getFirst() == Dir.N) {
				if (super.getSala_Actual().getNorte() != null) {
					super.setSala_Actual(super.getSala_Actual().getNorte());
					super.getSala_Actual().getRobots().addLast(this);
				}
			}
			if (super.getRuta().getFirst() == Dir.S) {
				if (super.getSala_Actual().getSur() != null) {
					super.setSala_Actual(super.getSala_Actual().getSur());
					super.getSala_Actual().getRobots().addLast(this);
				}
			}
			if (super.getRuta().getFirst() == Dir.E) {
				if (super.getSala_Actual().getEste() != null) {
					super.setSala_Actual(super.getSala_Actual().getEste());
					super.getSala_Actual().getRobots().addLast(this);
				}
			}
			if (super.getRuta().getFirst() == Dir.O) {
				if (super.getSala_Actual().getOeste() != null) {
					super.setSala_Actual(super.getSala_Actual().getOeste());
					super.getSala_Actual().getRobots().addLast(this);
				}
			}
			super.getRuta().addLast(super.getRuta().poll());
		} else
			super.getSala_Actual().insertarRobot(this);
		

	}

	/**
	 * Metodo que averigua la ruta por camino minimo de asimo
	 * @param ruta
	 * 			LinkedList donde se guarda la ruta
	 */
	private void asignarRuta() {
		Laberinto lab = Laberinto.getInstance();
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		LinkedList<Integer> ruta_id = new LinkedList<Integer>();
		
		int salaSureste = lab.salaSurEste().getId_sala();
		int salaNoreste = lab.salaNorEste().getId_sala();
		int salaNoroeste = lab.salaNorOeste().getId_sala();
		int salaSuroeste = lab.salaSuroeste().getId_sala();
		
		caminoMinimo(salaSureste, salaNoreste, ruta_id);
		caminoMinimo(salaNoreste, salaNoroeste, ruta_id);
		caminoMinimo(salaNoroeste, salaSuroeste, ruta_id);
		caminoMinimo(salaSuroeste, salaSureste, ruta_id);

		super.incluirRuta(ruta_id, ruta);
		super.setRuta(ruta);

	}

	/**
	 * Metodo que averigua el camino minimo entre el id de dos salas
	 * @param origen
	 * 			id de la sala origen
	 * @param destino
	 * 			id de la sala destino
	 * @param camino
	 * 			Linkedlist que contiene el camino minimo
	 */
	private void caminoMinimo(int origen, int destino,
			LinkedList<Integer> camino) {
		Laberinto lab = Laberinto.getInstance();
		while (origen != destino) {
			camino.addLast(origen);
			origen = lab.getGrafo().siguiente(origen, destino);
		}

	}
}
