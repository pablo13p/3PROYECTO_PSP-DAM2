package project;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Esta clase representa mi hilo especializado en la recepción de datos.
 * Mi función es mantenerme a la escucha de cualquier mensaje entrante
 * desde el socket y mostrarlo por pantalla para que el usuario pueda leerlo.
 * 
 * @author Pablo Rubio Prado
 * @version 1.0
 */
public class ReceptorThread extends Thread {
	private Socket socket;
	private DataInputStream entrada;
	
	/**
	 * Cuando creo este hilo, guardo el socket de la conexión.
	 * De esta manera, sé exactamente de qué flujo debo extraer la información.
	 * 
	 * @param socket El socket de la conexión activa desde el que recibiré datos.
	 */
	public ReceptorThread(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Este es el ciclo de vida de mi hilo de recepción.
	 * Configuro el flujo de entrada y me quedo esperando mensajes en un bucle.
	 */
	@Override
	public void run() {
		try {
			// Intento abrir el canal de entrada de datos desde el socket.
			entrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// Si no puedo acceder al flujo de entrada, imprimo el error.
			e.printStackTrace();
		}
		
		String mensaje;
		
		try {
			// Entro en un bucle infinito para recibir mensajes continuamente.
			while(true) {
				// Me quedo esperando a que llegue un mensaje; si no es nulo, lo proceso.
				if((mensaje = entrada.readUTF()) != null) {
					// Imprimo el mensaje recibido identificando la dirección IP del emisor.
					System.out.println("Equipo " + socket.getInetAddress() + ": " + mensaje);
				}
			}
		} catch (IOException e) {
			// Si salgo del bucle por una excepción, asumo que el equipo remoto se ha desconectado.
			System.out.println("Equipo " + socket.getInetAddress() + " desconectado.");
		}
	}
}