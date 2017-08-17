package Servidor;

import java.io.ObjectInputStream;
import java.net.Socket;

public class Tcliente {
	
	private Socket client;
	
	public Tcliente(Socket client){
		this.client = client;
	}
	
	public void start(){
		
		try{
			//recebe info do cliente
			ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
			
			String cypher = (String)entrada.readObject();
			
			cypher = Decrypt.decrypt(cypher, "key");
			System.out.println("info: "+cypher);
			
			client.close();
			entrada.close();
			
		}catch(Exception e){
			System.out.println("ERRO! Thread: "+e.getMessage());
			try{
				client.close();
			
			}catch(Exception e1){
				
			}
		}
	}
}
