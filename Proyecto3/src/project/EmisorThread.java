package project;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Esta clase representa mi hilo especializado en el envío de datos.
 * Mi cometido aquí es capturar lo que el usuario escribe por consola 
 * y enviarlo a través del socket hacia el otro extremo de la conexión.
 * 
 * @author Pablo Rubio Prado
 * @version 1.0
 */
public class EmisorThread extends Thread {
	
	private Socket socket;
	private DataOutputStream salida;
	
	/**
	 * Al construir el hilo, recibo el socket que ya está conectado.
	 * Guardo esta referencia para saber por dónde debo transmitir la información más tarde.
	 * 
	 * @param socket El socket de la conexión activa.
	 */
	public EmisorThread(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Este es el ciclo de vida de mi hilo de emisión.
	 * Configuro el flujo de salida y me quedo escuchando el teclado permanentemente.
	 */
	@Override
	public void run() {
		try {
			// Inicializo mi canal de salida de datos a partir del flujo del socket.
			salida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// Si no puedo abrir el canal, registro el error.
			e.printStackTrace();
		}

		// Preparo un escáner para leer lo que yo escriba en la consola.
		Scanner in = new Scanner(System.in);
		
		// Entro en un bucle infinito para enviar mensajes en cualquier momento.
		while(true) {
			String mensaje = in.nextLine();
			try {
				// Escribo el mensaje en el flujo de salida usando el formato UTF.
				salida.writeUTF(mensaje);
			} catch (IOException e) {
				// Si la conexión se rompe o hay un fallo al enviar, muestro el error.
				e.printStackTrace();
			}
		}
	}
}