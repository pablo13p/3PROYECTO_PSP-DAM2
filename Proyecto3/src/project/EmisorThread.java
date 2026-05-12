package project;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EmisorThread extends Thread{
	
	private Socket socket;
	private DataOutputStream salida;
	
	public EmisorThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			salida = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scanner in = new Scanner(System.in);
		while(true) {
			String mensaje = in.nextLine();
			try {
				salida.writeUTF(mensaje);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
