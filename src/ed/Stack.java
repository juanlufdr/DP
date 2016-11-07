package ed;

/**
 * Implementacion de los metodos de la clase Stack.
 *
 * @version 2.0
 * @author <b> Profesores DP </b><br>
 *         Asignatura Desarrollo de Programas<br/>
 *         Curso 14/15
 */
public class Stack<T extends Comparable<T>> {

	/** Puntero a la cima de la pila */
	private Node top;

	private class Node {
		/** Dato almacenado en cada nodo */
		private T data;
		/** Enlace al siguiente elemento */
		private Node next;

		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}// class Nodo

	/**
	 * Metodo constructor por defecto de la clase Pila
	 */
	public Stack() {
		top = null;
	}

	/**
	 * Metodo constructor parametrizado de la clase Stack
	 *
	 * @param data
	 *            es el nuevo elemento en la pila
	 */
	public Stack(T data) {
		Node node = new Node(data);
		node.next = top;
		top = node;
	}

	/**
	 * Metodo que devuelve el elemento en la cima de la pila
	 *
	 * @return la cima de la pila
	 */
	public T getTop() {
		return top.data;
	}

	/**
	 * Metodo para comprobar si la pila est� vacia o no
	 *
	 * @return true si est� vacia o false en caso contrario
	 */
	public boolean isEmpty() {
		return (top == null);
	}

	/**
	 * Metodo que permite insertar un dato
	 *
	 * @param data
	 *            valor que se va a insertar
	 */
	public void addData(T data) {
		Node node = new Node(data);
		node.next = top;
		top = node;
	}

	/**
	 * Elimina un dato de la pila. Se elimina el dato que est� en la top
	 */
	public void removeData() {
		if (!isEmpty()) {
			top = top.next;
		}
	}
}
