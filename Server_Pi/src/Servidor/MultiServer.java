package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	public static void main(String[] args) {
		
		try{
			ServerSocket server = new ServerSocket();
			
			while(true){
				Socket client = server.accept();
				new Tcliente(client).start();
			
			}
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
