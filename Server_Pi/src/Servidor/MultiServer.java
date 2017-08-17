package Servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	public static void main(String[] args) {
		
		try{
			//Cria o socket do servidor
			ServerSocket server = new ServerSocket();
			
			//Instancia as threads de conex�o
			while(true){
				Socket client = server.accept();
				new Tcliente(client).start();
			
			}
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
