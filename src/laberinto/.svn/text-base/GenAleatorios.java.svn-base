package laberinto;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
* Generador de n�meros aleatorios - proyecto11_12
* Implementacion de la clase que permite generar n�meros aleatorios
* @version 1.0
* @author
* <b> Profesores DP </b><br>
* Asignatura Desarrollo de Programas<br/>
* Curso 14/14
*/
public class GenAleatorios {
	
	/** Instancia de la clase Random que permite generar los n�meros aleatorios. */
	private static Random r;
	
	/** Constante con la semilla para inicializar la generaci�n de n�meros aleatorios. ��No cambiar!! */
	private static final int SEMILLA=1987;		 
	
	/** Contador de n�meros aleatorios generados */
	private static int numGenerados;			
	
	/** Instancia de la propia clase (s�lo habr� una en el sistema). Ver PATR�N DE DISE�O SINGLETON */
	private static GenAleatorios instancia=null;	
	
	/**
	* Constructor por defecto. Inicializa un objeto de tipo GenAleatorio
	* @return Devuelve un objeto de tipo GenAleatorio inicializado
	*/
	private GenAleatorios(){
    	// Inicializaci�n de la semilla para generar los n�meros aleatorios
    	r = new Random(GenAleatorios.SEMILLA);
    	// Inicializaci�n del atributo que cuenta cu�ntos n�meros aleatorios se han generado
    	numGenerados = 0;
    	}
	
	/**
	 * Metodo generarNumero. Genera un n�mero aleatorio entre 0 y (limiteRango-1)
	 * @param limiteRango El l�mite del rango en el que generar los aleatorios
	 * @return Devuelve el n�mero aleatorio generado
	 */
	public static int generarNumero(int limiteRango){
		if (instancia == null) 
			instancia = new GenAleatorios();
		numGenerados++;
		return r.nextInt(limiteRango);
	}
	
	/**
	 * Devuelve el n�mero de aleatorios generados
	 * @return N�mero de aleatorios generados
	 */
	public static int getNumGenerados(){
		return numGenerados;
	}
	
}
