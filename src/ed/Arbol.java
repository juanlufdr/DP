package ed;

/**
 * Grupo: The Team Crocket
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nu�ez
 *
 * @param <T> de tipo generico
 */
public class Arbol<T extends Comparable<T>> {

	/** Dato almacenado en cada nodo del arbol. */
	private T datoRaiz;

	/** Atributo que indica si el arbol esta vacio. */
	boolean esVacio;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;

	/**
	 * Constructor por defecto de la clase. Inicializa un arbol vacio.
	 */
	public Arbol() {
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	/**
	 * Crea un nuevo arbol a partir de los datos pasados por parametro.
	 *
	 * @param hIzq
	 *            El hijo izquierdo del arbol que se esta creando
	 * @param datoRaiz
	 *            raiz del arbol que se esta creando
	 * @param hDer
	 *            El hijo derecho del arbol que se esta creando
	 */
	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}

	/**
	 * Devuelve el hijo izquierdo del arbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol<T> getHijoIzq() {
		return hIzq;
	}

	/**
	 * Devuelve el hijo derecho del arbol
	 *
	 * @return Hijo derecho del arbol
	 */
	public Arbol<T> getHijoDer() {
		return hDer;
	}

	/**
	 * Devuelve la raiz del arbol
	 *
	 * @return La raiz del arbol
	 */
	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el arbol esta vacio.
	 * 
	 * @return verdadero si el arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el arbol.
	 *
	 * @param dato
	 *            El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso
	 *         contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if (vacio()) {
			datoRaiz = dato;
			esVacio = false;
		} else {
			if (!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
				if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
					if ((aux = getHijoIzq()) == null)
						hIzq = aux = new Arbol<T>();
				} else { // dato > datoRaiz
					if ((aux = getHijoDer()) == null)
						hDer = aux = new Arbol<T>();
				}
				resultado = aux.insertar(dato);
			} else
				resultado = false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el arbol
	 *
	 * @param dato
	 *            El dato a buscar
	 * @return verdadero si el dato se encuentra en el arbol, falso en caso
	 *         contrario
	 */
	public boolean pertenece(T dato) {
		Arbol<T> aux = null;
		boolean izq=false,der=false;
		if (!vacio()) {
			

			if (this.datoRaiz.equals(dato)) return true;
			
			if ((aux = getHijoIzq()) != null) {
				izq=aux.pertenece(dato);
			}

			
			if ((aux = getHijoDer()) != null) {
				der=aux.pertenece(dato);
			}
			
			if ((izq==true)||(der==true)) return true;
		}
		return false;
	}

	/**
	 * Borrar un dato del arbol.
	 *
	 * @param dato
	 *            El dato que se quiere borrar
	 */
	public void borrar(T dato) {
		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				hDer = hDer.borrarOrden(dato);
			} else // En este caso el dato es datoRaiz
			{
				if (hIzq == null && hDer == null) {
					esVacio = true;
				} else
					borrarOrden(dato);
			}
		}
	}

	/**
	 * Borrar un dato. Este método es utilizado por el método borrar anterior.
	 *
	 * @param dato
	 *            El dato a borrar
	 * @return Devuelve el arbol resultante después de haber realizado el
	 *         borrado
	 */
	private Arbol<T> borrarOrden(T dato) {
		T datoaux;
		Arbol<T> retorno = this;
		Arbol<T> aborrar, candidato, antecesor;

		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);
			} else {
				aborrar = this;
				if ((hDer == null) && (hIzq == null)) { /* si es hoja */
					retorno = null;
				} else {
					if (hDer == null) { /* Solo hijo izquierdo */
						aborrar = hIzq;
						datoaux = this.datoRaiz;
						datoRaiz = hIzq.getRaiz();
						hIzq.datoRaiz = datoaux;
						hIzq = hIzq.getHijoIzq();
						hDer = aborrar.getHijoDer();

						retorno = this;
					} else if (hIzq == null) { /* Solo hijo derecho */
						aborrar = hDer;
						datoaux = datoRaiz;
						datoRaiz = hDer.getRaiz();
						hDer.datoRaiz = datoaux;
						hDer = hDer.getHijoDer();
						hIzq = aborrar.getHijoIzq();

						retorno = this;
					} else { /* Tiene dos hijos */
						candidato = this.getHijoIzq();
						antecesor = this;
						while (candidato.getHijoDer() != null) {
							antecesor = candidato;
							candidato = candidato.getHijoDer();
						}

						/* Intercambio de datos de candidato */
						datoaux = datoRaiz;
						datoRaiz = candidato.getRaiz();
						candidato.datoRaiz = datoaux;
						aborrar = candidato;
						if (antecesor == this)
							hIzq = candidato.getHijoIzq();
						else
							antecesor.hDer = candidato.getHijoIzq();
					} // Eliminar solo ese nodo, no todo el subarbol
					aborrar.hIzq = null;
					aborrar.hDer = null;
				}
			}
		}
		return retorno;
	}

	

	/**
	 * Metodo que indica la profundidad(o altura) del arbol
	 * @return la profundidad del arbol
	 */
	public Integer profundidad() {
		Integer cont_izq = 0;
		Integer cont_der = 0;
		if (!vacio()) {
			cont_izq = cont_der = 1;

			if ((getHijoIzq()) != null) {
				cont_izq = getHijoIzq().profundidad() + 1;
			}
			if ((getHijoDer()) != null)
				cont_der = getHijoDer().profundidad() + 1;
		}

		if (cont_izq.compareTo(cont_der) > 0)
			return cont_izq;
		else
			return cont_der;
	}

	/**
	 * @param dato 
	 * 				elemento a buscar entre las hojas del arbol
	 * @return true si dato es un elemento hoja del arbol, false en caso contrario
	 */
	public boolean esHoja(T dato) {
		Arbol<T> hIzq, hDer;
		T datoraiz;
		if (!vacio() && pertenece(dato)) {
			datoraiz = getRaiz();
			hIzq = getHijoIzq();
			hDer = getHijoDer();
			if (datoraiz.equals(dato)) {
				if (hIzq == null && hDer == null) {
					return true;
				}
			} else {
				if ((getRaiz()).compareTo(dato) > 0) {
					return hIzq.esHoja(dato);
				} else {
					return hDer.esHoja(dato);
				}
			}

		}
		return false;
	}

	/**
	 * M�todo privado utilizado para contar las hojas del arbol
	 * @param cont 
	 * 			   contador que indica el numero de hojas que tiene el arbol
	 * @return el numero de hojas del arbol
	 */
	private Integer contarHojas(Integer cont) {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((getHijoDer()) == null & (getHijoIzq()) == null) {
				cont++;
			}
			if ((aux = getHijoIzq()) != null) {
				cont = aux.contarHojas(cont);
			}

			if ((aux = getHijoDer()) != null) {
				cont = aux.contarHojas(cont);
			}
		} else {
			cont = 0;
		}
		return cont;

	}

	/**
	 * Metodo que cuenta el numero de hojas de un arbol.
	 * @return el numero de hojas que contiene el arbol
	 */
	public Integer numeroHojas() {

		Integer cont = 0;

		return contarHojas(cont);
	}

	/**
	 * Metodo privado que cuenta el numero de nodos totales, utilizado para contar el numero de nodos internos
	 * @param cont
	 * 			  Cuenta el numero de nodos que tiene el arbol
	 * @return el numero nodos totales del arbol
	 */
	private Integer contarNodosInternos(Integer cont) {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				cont = aux.contarNodosInternos(cont);
			}

			cont++;

			if ((aux = getHijoDer()) != null) {
				cont = aux.contarNodosInternos(cont);
			}
		}
		return cont;
	}

	/**
	 * Metodo que devuelve el numero de nodos internos
	 * @return (nodos totales) - (nodos hoja)
	 */
	public Integer numeroNodosInternos() {
		Integer cont1, cont2;
		cont1 = cont2 = 0;
		return (contarNodosInternos(cont1) - contarHojas(cont2));
	}

	/**
	 * Recorrido inOrden del arbol.
	 */
	public void inOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			System.out.println(this.datoRaiz);

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		}
	}

	/**
	 * Recorrido preOrden del arbol.
	 */
	public void preOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			System.out.println(this.datoRaiz);

			if ((aux = getHijoIzq()) != null)
				aux.preOrden();

			if ((aux = getHijoDer()) != null)
				aux.preOrden();

		}
	}
	
	
	/**
	 * Recorrido postOrden del arbol.
	 */
	public void postOrden() {
		Arbol<T> aux = null;

		if (!vacio()) {

			if ((aux = getHijoIzq()) != null)
				aux.postOrden();

			if ((aux = getHijoDer()) != null)
				aux.postOrden();

			System.out.println(this.datoRaiz);

		}

	}

	public String toString() {
		Arbol<T> aux = null;
		String s = new String();
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				s = s + aux.toString();
			}

			s = s + " "+ datoRaiz;

			if ((aux = getHijoDer()) != null) {
				s = s + aux.toString();
			}
		}

		return s;
	}
}

