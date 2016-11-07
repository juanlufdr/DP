package laberinto;
/**
 * Grupo: The Team Crocket
 * @author Juan Luis Fragoso del Rey	
 * @author Carlos Rodriguez Nu�ez
 *
 */
public class Llave implements Comparable<Llave> {

	/** Identificador de la llave */
	private Integer id_llave;

	/**
	 * Crea una llave con el id pasado por parámetro
	 */
	public Llave(Integer id_llave) {
		this.id_llave = id_llave;
	}

	/**
	 * Devuelve el identificador de la llave
	 * 
	 * @return identificador de la llave
	 */
	public Integer getIdLlave() {
		return id_llave;
	}

	/**
	 * Establece un identificador para una llave
	 * 
	 * @param id_llave
	 */
	public void setIdLlave(Integer id_llave) {
		this.id_llave = id_llave;
	}


	public int compareTo(Llave l) {
		if (this == l) {
			return 0;
		}
		return (this.id_llave.compareTo(l.getIdLlave()));
	}
	

	public String toString() {
		String s;
		s = Integer.toString(id_llave);
		return s;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Llave other = (Llave) obj;
		if (id_llave == null) {
			if (other.id_llave != null)
				return false;
		} else if (!id_llave.equals(other.id_llave))
			return false;
		return true;
	}

}
