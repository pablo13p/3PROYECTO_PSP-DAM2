package project;

import java.net.Socket;

/**
 * Esta es la clase cliente que he diseñado para establecer la comunicación.
 * Mi función aquí es configurar la conexión inicial y lanzar los hilos 
 * que se encargarán de enviar y recibir mensajes.
 * 
 * @author Pablo Rubio Prado
 * @version 1.0
 */
public class Cliente {

	/**
	 * Este es el punto de inicio de mi aplicación cliente.
	 * En este método defino a dónde me quiero conectar y preparo los hilos de ejecución.
	 * 
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		// Defino la dirección IP y el puerto del servidor al que me voy a conectar.
		final String HOST = "192.168.1.131";
		final int PUERTO = 5000;
		
		try {
			// Intento establecer la conexión con el servidor usando los datos definidos.
			Socket s = new Socket(HOST, PUERTO);
			System.out.println("Conectado al servidor");
			
			// Instancio mis dos hilos personalizados: uno para emitir y otro para recibir.
			EmisorThread emisor = new EmisorThread(s);
			ReceptorThread receptor = new ReceptorThread(s);
			
			// Arranco ambos hilos para que trabajen de forma simultánea.
			emisor.start();
			receptor.start();
			
		} catch(Exception e) {
			// Si algo falla durante la conexión, imprimo el rastro del error.
			e.printStackTrace();
		}
		
	}

}