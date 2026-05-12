package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private final int PORT = 5000;
	private int nconexion = 0;
	
	public Server() {
		try {
			this.serverSocket = new ServerSocket(PORT);
			System.out.println("Servidor iniciado en el puerto " + PORT + "\n Esperando conexiones...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void esperaConexion() {
		while(true) {
			try {
				Socket socket = this.serverSocket.accept();
				
				EmisorThread emisor = new EmisorThread(socket);
				ReceptorThread receptor = new ReceptorThread(socket);
				
				emisor.start();
				receptor.start();
				
				nconexion++;
				System.out.println("Numero de conexión: " + nconexion);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
