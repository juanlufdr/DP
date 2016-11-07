package laberinto;

/**
 * Grupo: The Team Crocket
 * @author Juan Luis Fragoso del Rey	
 * @author Carlos Rodriguez Nu�ez
 *
 */
public class Pared {
	/** Almacena la posición de origen de la pared*/
	private int origen;
	/** Almacena la posición final de la pared */
	private int fin;
	
	
	/**
	 * Constructor parametrizado de la clase Pared
	 * @param origen
	 * @param fin
	 */
	public Pared (int origen, int fin) {
		this.origen = origen;
		this.fin = fin;
		
	}

	/**
	 * Devuelve la posición de origen de la pared.
	 * @return Posición de origen de la pared.
	 */
	public int getOrigen() {
		return origen;
	}

	/**
	 * Inserta la posición de origen de la pared pasada por parámetro
	 * @param origen
	 * 			Punto de origen de la pared.
	 */
	public void setOrigen(int origen) {
		this.origen = origen;
	}

	/**
	 * Devuelve la posición final de la pared.
	 * @return Posición de origen de la pared.
	 */
	public int getFin() {
		return fin;
	}

	/**
	 * Inserta la posición de final de la pared pasada por parámetro
	 * @param fin
	 * 			Punto de origen de la pared.
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	
	
	
	public String toString() {
		return ("Pared: " + "(" + this.origen+ " , " + this.fin+ ")");
	}

	
	
}
