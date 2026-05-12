package project;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int PUERTO = 5000;
		DataInputStream entrada;
		DataOutputStream salida;
		
		try {
			ServerSocket s = new ServerSocket(PUERTO);
			System.out.println("Servidor iniciado en el puerto " + PUERTO + "\n Esperando conexiones...");
			
			Socket cliente = s.accept();
			System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostName());
			
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());
			
			Thread recibir = new Thread(() -> {
				String mensaje;
				try {
					while (true) {
						if((mensaje = entrada.readUTF()) != null) {
						System.out.println("Cliente: " + mensaje);
						}
					}
				} catch (IOException e) {
					System.out.println("Cliente desconectado.");
				}
			});
			
			Thread enviar  =  new Thread(() -> {
				Scanner in = new Scanner(System.in);
				while(true) {
					String mensaje = in.nextLine();
					try {
						salida.writeUTF(mensaje);
					} catch (IOException e) {
						System.out.println("Error al enviar mensaje.");
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
