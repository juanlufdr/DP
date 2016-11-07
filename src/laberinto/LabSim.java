package laberinto;

import java.io.FileNotFoundException;
import java.io.IOException;

import cargador.Cargador;
import cargador.FicheroCarga;

public class LabSim {

	/**
	 * Tipo enumerado que representa las 4 posibles direcciones en las que puede
	 * moverse un robot.
	 */
	public enum Dir {
		S, E, N, O
	};

	/**
	 * Programa principal -EC3.
	 * 
	 * @param args Argumentos que recibe el programa principal
	 */
	public static void main(String args[]) {
		int maxTurnos = 50;

		Laberinto lab;

		Cargador cargador = new Cargador();
		try {
			FicheroCarga.procesarFichero("inicio.txt", cargador);
		} catch (FileNotFoundException valor) {
			System.err.println("Excepcion capturada al procesar fichero: "
					+ valor.getMessage());
		} catch (IOException valor) {
			System.err.println("Excepcion capturada al procesar fichero: "
					+ valor.getMessage());
		}

		lab = Laberinto.getInstance();

		Llave[] conjuntoLlaves = new Llave[15];
		int iterador = 0;
		for (int i = 0; i < 30; i++) {
			if (i % 2 != 0) {
				conjuntoLlaves[iterador] = new Llave(i);
				iterador++;
			}
		}

		Llave[] combinacion = new Llave[conjuntoLlaves.length];

		lab.generarCombinacion(conjuntoLlaves, 0, (conjuntoLlaves.length - 1),
				combinacion, 0);

		Puerta p = new Puerta();
		p.setAltura_Apertura(lab.getPuerta().getAltura_Apertura());
		p.configurarPuerta(combinacion);
		p.cerrarPuerta();
		lab.insertarPuerta(p, lab.getSala_Puerta());

		lab.abrirFichero("Registro.log");

		System.out.println(lab.pintarLaberintoSinRobots());
		
		lab.escribirEnFichero(lab.pintarLaberintoSinRobots());
		
		System.out.println("(distribucion llaves)");
		
		lab.escribirEnFichero("(distribucion llaves)\n");

		lab.escribirSalasConLlaves();

		lab.escribirRutaRobots();

		for (int i = 0; i < maxTurnos; i++) {
			if (!lab.getPuerta().estaAbierta()) {
				lab.procesar(i);
				System.out.println(lab);
				lab.escribirEnFichero(lab.toString());
			} else
				i = maxTurnos;
		}
		lab.cerrarFichero();
		
	}
}
