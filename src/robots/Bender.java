package robots;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import laberinto.Laberinto;
import laberinto.Puerta;
import laberinto.Sala;
import laberinto.LabSim.Dir;

public class Bender extends Robot {

	/**
	 * Método constructor parametrizado de la clase Bender
	 * 
	 * @param nombre
	 * @param id
	 * @param turno_inicial
	 * @param sala_Actual
	 */
	public Bender(String nombre, char id, int turno_inicial, Sala sala_Actual) {
		super(nombre, id, turno_inicial, sala_Actual);
		this.asignarRuta();

	}

	/**
	 * Método que inserta la ruta del robot usando su algoritmo correspondiente.
	 */
	private void asignarRuta() {
		LinkedList<Dir> ruta = new LinkedList<Dir>();
		Set<Integer> visitadas = new LinkedHashSet<Integer>();
		rutaProfundidad(visitadas, Laberinto.getInstance().salaInicial()
				.getId_sala(), ruta);
		super.setRuta(ruta);
	}

	/**
	 * Método que devuelve un vector con las salas vecinas correspondientes a la sala que se pasa por parámetro.
	 * @param idSala
	 * @return Vector de salas vecinas
	 */
	public int[] calcularVecinas(int idSala) {
		Laberinto lab = Laberinto.getInstance();
		int vectorSalas[] = new int[4];
		for (int i = 0; i < vectorSalas.length; i++) {
			vectorSalas[i] = -1;
		}
		if (lab.getSalas().get(idSala).getNorte() != null)
			vectorSalas[0] = lab.getSalas().get(idSala).getNorte().getId_sala();

		if (lab.getSalas().get(idSala).getEste() != null)
			vectorSalas[1] = lab.getSalas().get(idSala).getEste().getId_sala();

		if (lab.getSalas().get(idSala).getSur() != null)
			vectorSalas[2] = lab.getSalas().get(idSala).getSur().getId_sala();

		if (lab.getSalas().get(idSala).getOeste() != null)
			vectorSalas[3] = lab.getSalas().get(idSala).getOeste().getId_sala();

		return vectorSalas;
	}
	
	/**
	 * Recorrido en profundidad del Robot.
	 * @param visitadas
	 * @param ultimaSala
	 * @param ruta
	 * @return TRUE cuando encuentra el primer camino, FALSE si no encuentra camino.
	 */
	public boolean rutaProfundidad(Set<Integer> visitadas, Integer ultimaSala,
			LinkedList<Dir> ruta) {
		Laberinto lab = Laberinto.getInstance();
		Set<Integer> adyacentes = new LinkedHashSet<Integer>();
		if (ultimaSala.equals(lab.getSala_Puerta())) {
			System.out.println(visitadas);
			return true;
		}
		else{
		lab.getGrafo().adyacentes(ultimaSala, adyacentes);

		for (Integer n: adyacentes) {
			if (!visitadas.contains(n)) {
				visitadas.add(n);
				ruta.addLast(lab.getDireccion(ultimaSala,n));
				if (rutaProfundidad(visitadas, n, ruta)) {
					return true;
				} else {
					visitadas.remove(n);
					ruta.removeLast();
				}
			}
		}
		}
		return false;
	}

}
