package Servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Tcliente {
	
	private Socket client;
	
	public Tcliente(Socket client){
		this.client = client;
	}
	
	public void start(){
		
		try{
			//Recebe info do cliente
			ObjectInputStream entrada = new ObjectInputStream(client.getInputStream());
			
			//Coloca a informa��o numa String
			String cypher = (String)entrada.readObject();
			
			//Desencripta a informa��o
			cypher = Decrypt.decrypt(cypher, "key");
			System.out.println("info: "+cypher);
			
			//Encerra a conex�o
			client.close();
			entrada.close();
			
		}catch(Exception e){
			//Informa qual conex�o deu erro
			System.out.println("ERRO! Thread: "+e.getMessage());
			try{
				client.close();
			
			}catch(Exception e1){
				
			}
		}
	}
}
