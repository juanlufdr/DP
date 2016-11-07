package ed;
/**
 * Grupo: The Team Crocket
 * @author Juan Luis Fragoso del Rey
 * @author Carlos Rodriguez Nu�ez
 *
 * @param <T> de tipo generico
 */
public class Queue<T> {

	/** Puntero al primer elemento de la Cola	 */
	private Node first;
	/** Puntero al ultimo elemento de la Cola	 */
	private Node last;

	public class Node {
		/** Dato almacenado en cada nodo de la cola  */
		private T Data;
		/** Puntero al siguiente elemento de la cola */
		private Node next;

		// private Integer size;

		public Node(T Data) {
			this.Data = Data;
			this.next = null;
		}
	}

	/**
	 * Metodo constructor por defecto de la clase Queue
	 */
	public Queue() {
		first = last = null;
	}

	/**
	 * Metodo constructor parametrizado de la clase Queue
	 * @param Data 
	 * 			   contiene el valor con el que se inicializa el primer elemento de la cola
	 */
	public Queue(T Data) {
		Node nodoDato = new Node(Data);
		first = last = nodoDato;
	}

	/**
	 * @return el primer elemento de la cola
	 */
	public T getFrente() {
		return first.Data;
	}

	/**
	 * @return true si la cola esta vacia, false en caso contrario
	 */
	public boolean estaVacia() {
		return (first == null);
	}

	/**
	 * @param Data valor a insertar al final de la cola
	 */
	public void encolar(T Data) {
		Node nodoDato = new Node(Data);
		if (first == null) {
			first = last = nodoDato;
		} else {
			last.next = nodoDato;
			last = nodoDato;
		}
	}

	/**
	 * Metodo que elimina el primer elemento de la cola
	 */
	public void desencolar() {
		// Node nodoDato = first;
		if (!estaVacia()) {
			first = first.next;
		} else {
			System.out.println("La cola ya esta vacia");
		}
	}
	
	

}
