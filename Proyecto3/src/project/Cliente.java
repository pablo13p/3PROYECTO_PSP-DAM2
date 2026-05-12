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
			
			EmisorThread emisor = new EmisorThread(s);
			ReceptorThread receptor = new ReceptorThread(s);
			
			emisor.start();
			receptor.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
