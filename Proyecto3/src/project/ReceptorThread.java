package project;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceptorThread extends Thread{
	private Socket socket;
	private DataInputStream entrada;
	
	public ReceptorThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			entrada = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mensaje;
		
		try {
			while(true) {
				
				if((mensaje = entrada.readUTF()) != null) {
					System.out.println("Equipo " + socket.getInetAddress() + ": " + mensaje);
				}
			}
		} catch (IOException e) {
			System.out.println("Equipo " + socket.getInetAddress() + " desconectado.");
		}
	}
}
