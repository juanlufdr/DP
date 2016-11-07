package robots;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import laberinto.LabSim.Dir;
import laberinto.Laberinto;
import laberinto.Sala;

public class Spirit extends Robot {
	/**Vector de direcciones correspondiente a la orientación Norte */
	private Dir orientacionNorte[] = { Dir.O, Dir.N, Dir.E, Dir.S };
	/**Vector de direcciones correspondiente a la orientación Este */
	private Dir orientacionEste[] = { Dir.N, Dir.E, Dir.S, Dir.O };
	/**Vector de direcciones correspondiente a la orientación Sur */
	private Dir orientacionSur[] = { Dir.E, Dir.S, Dir.O, Dir.N };
	/**Vector de direcciones correspondiente a la orientación Oeste */
	private Dir orientacionOeste[] = { Dir.S, Dir.O, Dir.N, Dir.E };


	/**
	 * Método constructor parametrizado de la clase Spirit.
	 * 
	 * @param nombre
	 * @param id
	 * @param turno_inicial
	 * @param sala_Actual
	 */
	public Spirit(String nombre, char id, int turno_inicial, Sala sala_Actual) {
		super(nombre, id, turno_inicial, sala_Actual);
		this.asignarRuta();
	}
	
	/**
	 * Recorrido segun el algorimo de la Mano Izquierda
	 * @param ruta
	 */
	public void rutaManoIzquierda(LinkedList<Dir> ruta) {
		Laberinto lab = Laberinto.getInstance();

		int salaActual = lab.salaSuroeste().getId_sala();
		int objetivo = lab.getSala_Puerta();

		super.setOrientacion(Dir.N);

		boolean enc;
		int sala = 0, aux;

		while (salaActual != objetivo) {
			enc = false;
			sala = 0;
			switch (getOrientacion()) {
			case N:
				while (!enc) {
					aux = lab.puedoDir(salaActual, orientacionNorte[sala]);
					if (aux != salaActual) {
						salaActual = aux;
						ruta.add(orientacionNorte[sala]);
						setOrientacion(orientacionNorte[sala]);
						enc = true;
					}

					sala++;
				}
				break;
			case E:
				while (!enc) {
					aux = lab.puedoDir(salaActual, orientacionEste[sala]);
					if (aux != salaActual) {
						salaActual = aux;
						ruta.add(orientacionEste[sala]);
						setOrientacion(orientacionEste[sala]);
						enc = true;
					}
					sala++;
				}

				break;
			case S:
				while (!enc) {
					aux = lab.puedoDir(salaActual, orientacionSur[sala]);
					if (aux != salaActual) {
						salaActual = aux;
						ruta.add(orientacionSur[sala]);
						setOrientacion(orientacionSur[sala]);
						enc = true;
					}
					sala++;
				}
				break;
			case O:
				while (!enc) {
					aux = lab.puedoDir(salaActual, orientacionOeste[sala]);
					if (aux != salaActual) {
						salaActual = aux;
						ruta.add(orientacionOeste[sala]);
						setOrientacion(orientacionOeste[sala]);
						enc = true;
					}
					sala++;
				}

				break;
			}

		}

	}

	/**
	 * Método que le asigna la ruta correspondiente al robot.
	 */
	private void asignarRuta() {
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		this.rutaManoIzquierda(ruta);
		super.setRuta(ruta);
		

	};


}
