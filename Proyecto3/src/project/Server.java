package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta es la clase donde gestiono toda la infraestructura del servidor.
 * Mi función es abrir el puerto de escucha y administrar cada una de las 
 * conexiones entrantes de forma independiente.
 * 
 * @author Pablo Rubio Prado
 * @version 1.0
 */
public class Server {
	// Defino el socket del servidor, el puerto fijo y un contador de conexiones.
	private ServerSocket serverSocket;
	private final int PORT = 5000;
	private int nconexion = 0;
	
	/**
	 * En el constructor inicializo el socket del servidor.
	 * Aquí es donde intento reservar el puerto 5000 en el sistema para empezar a trabajar.
	 */
	public Server() {
		try {
			// Intento crear el ServerSocket y notifico por consola si tengo éxito.
			this.serverSocket = new ServerSocket(PORT);
			System.out.println("Servidor iniciado en el puerto " + PORT + "\n Esperando conexiones...");
		} catch (IOException e) {
			// Si el puerto está ocupado o hay un error, imprimo el rastro de la excepción.
			e.printStackTrace();
		}
	}
	
	/**
	 * Este método contiene mi bucle principal de escucha.
	 * Me mantengo en un ciclo infinito aceptando clientes y asignándoles sus propios hilos.
	 */
	public void esperaConexion() {
		while(true) {
			try {
				// Me quedo bloqueado aquí hasta que recibo una nueva petición de conexión.
				Socket socket = this.serverSocket.accept();
				
				// Para cada cliente nuevo, preparo un emisor y un receptor independientes.
				EmisorThread emisor = new EmisorThread(socket);
				ReceptorThread receptor = new ReceptorThread(socket);
				
				// Lanzo los hilos para que la comunicación fluya sin bloquear el bucle principal.
				emisor.start();
				receptor.start();
				
				// Incremento mi contador personal y muestro el progreso en la consola.
				nconexion++;
				System.out.println("Numero de conexión: " + nconexion);
				
			} catch (IOException e) {
				// Registro cualquier error que ocurra al intentar aceptar una conexión.
				e.printStackTrace();
			}
		}
	}
}