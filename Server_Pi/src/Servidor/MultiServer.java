package Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer extends Thread{
	
	static ServerSocket conexao = null;
	static Socket aceitar = null;
	
	public MultiServer(Socket aux){
		aceitar = aux;
	}
	
	public static void main(String args[]){
		
		try{//tentando criar uma conexao
		
			conexao = new ServerSocket(10010);//Cria um SocketServer com porta 11015
			System.out.println("Servidor- OUVINDO PORTA "+conexao.getLocalPort());
			//System.out.println("TESTE RESPOSTA: "+ Decrypt.decrypt(, key));
			
			while(true){
				
				aceitar = conexao.accept();	
				System.out.println("Servidor- Cliente Conectado");
				//cria uma thread que envia a conexao
				Thread t = new MultiServer(aceitar);
				//inicia a thread t
				t.start();
				 
			}
		}catch(IOException e){
			System.out.println("Servidor- IOException "+e.getMessage());
		}
	}

	public void start(){
		
		String key = "1234567891234567";
		
		try{
			// Cria uma buffer que irá armazenar as informações enviadas pelo cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(aceitar.getInputStream()));
			
            // Cria uma stream de sáida para retorno das informações ao cliente
            DataOutputStream saida = new DataOutputStream(aceitar.getOutputStream());
			
            // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "EscritaCliente"
            String cypher = entrada.readLine();
            int aux = cypher.length();
            cypher = cypher.substring(7, aux);
            System.out.println("Servidor- Decrypt: "+ Decrypt.decrypt(cypher, key));           
			
		}catch(IOException e){
			System.out.println("Servidor- IOException "+e.getMessage());
		}
	}
}





















