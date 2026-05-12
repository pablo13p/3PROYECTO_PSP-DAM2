package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String HOST = "192.168.1.131";
		final int PUERTO = 5000;
		
		try {
			Socket s = new Socket(HOST, PUERTO);
			System.out.println("Conectado al servidor");
			
			DataInputStream entrada = new DataInputStream(s.getInputStream());
			DataOutputStream salida = new DataOutputStream(s.getOutputStream());
			
			Thread recibir = new Thread(() -> {
				try {
					String mensaje;
					
					while(true) {
						mensaje = entrada.readUTF();
						System.out.println("Servidor: " + mensaje);
					}
					
				}catch(IOException ioe) {
					System.out.println("Conexión cerrada.");
				}
			});
			
			Thread enviar = new Thread(() -> {
				Scanner teclado = new Scanner(System.in);
				
				while(true) {
					String mensaje = teclado.nextLine();
					try {
						salida.writeUTF(mensaje);
					} catch (IOException e) {
						System.out.println("Error al enviar el mensaje.");
					}
				}
			});
			
			recibir.start();
			enviar.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
