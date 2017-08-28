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
			System.out.println("OUVINDO PORTA "+conexao.getLocalPort());
			
			while(true){
				
				aceitar = conexao.accept();	
				System.out.println("Cliente Conectado");
				//cria uma thread que envia a conexao
				Thread t = new MultiServer(aceitar);
				//inicia a thread t
				t.start();
				 
			}
		}catch(IOException e){
			System.out.println("IOException "+e);
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
            
            System.out.println("chegou no teste");
            if(cypher.length()%16 == 0){
            	System.out.println("passou no teste");
            	cypher = Decrypt.decrypt(cypher, key);
            }else{
            	System.out.println("reprovou no teste e vai pra função");
            	System.out.println("Chamou a função");
            	fixer(cypher);
            	System.out.println("TAMANHO DO INPUT FINAL :"+cypher.length());
            	cypher = Decrypt.decrypt(cypher, key);
            } 
            
            System.out.println("SERVIDOR: "+cypher);
			
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
	
	public String fixer(String aux){
		System.out.println("chegou na função");
		do{
			aux = aux+"a";
			System.out.println(aux);
			System.out.println("tamanho: "+aux.length());
			
		}while(aux.length()%16 != 0);		
		return aux;
	}
}





















