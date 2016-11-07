package robots;

import java.util.LinkedList;

import laberinto.LabSim.Dir;
import laberinto.Laberinto;
import laberinto.Sala;

public class Sonny extends Robot {
	/**Vector de direcciones correspondiente a la orientación Norte */ 
	private Dir orientacionNorte[] = { Dir.E, Dir.N, Dir.O, Dir.S };
	/**Vector de direcciones correspondiente a la orientación Este */
	private Dir orientacionEste[] = { Dir.S, Dir.E, Dir.N, Dir.O };
	/**Vector de direcciones correspondiente a la orientación Sur */
	private Dir orientacionSur[] = { Dir.O, Dir.S, Dir.E, Dir.N };
	/**Vector de direcciones correspondiente a la orientación Oeste */
	private Dir orientacionOeste[] = { Dir.N, Dir.O, Dir.S, Dir.E };

	/**
	 * Método constructor parametrizado de la clase Sonny
	 * 
	 * @param nombre
	 * @param id
	 * @param turno_inicial
	 * @param sala_Actual
	 */
	public Sonny(String nombre, char id, int turno_inicial, Sala sala_Actual) {
		super(nombre, id, turno_inicial, sala_Actual);
		this.asignarRuta();
	}

	/**
	 * Recorrido según el algoritmo de la Mano Derecha.
	 * @param ruta
	 */
	public void rutaManoDerecha(LinkedList<Dir> ruta) {
		Laberinto lab = Laberinto.getInstance();

		int salaActual = lab.salaInicial().getId_sala();
		int objetivo = lab.getSala_Puerta();

		super.setOrientacion(Dir.E);

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
		this.rutaManoDerecha(ruta);
		super.setRuta(ruta);
		
	}
}
