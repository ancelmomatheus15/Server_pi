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
		
			conexao = new ServerSocket(12345);//Cria um SocketServer com porta 11015
			
			while(true){
				
				System.out.println("OUVINDO PORTA "+conexao.getLocalPort());
				aceitar = conexao.accept();				
				//cria uma thread que envia a conexao
				Thread t = new MultiServer(aceitar);
				//inicia a thread t
				t.start();
				 
			}
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}

	public void run(){
		try{
			// Cria uma buffer que irá armazenar as informações enviadas pelo cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(aceitar.getInputStream()));
			
            // Cria uma stream de sáida para retorno das informações ao cliente
            DataOutputStream saida = new DataOutputStream(aceitar.getOutputStream());
			
            // Faz a leitura das informações enviadas pelo cliente as amazenam na variável "EscritaCliente"
            String cypher = entrada.readLine();
			
			
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}
}






























//package Servidor;
//
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class MultiServer extends Thread {
//
//	public static void main(String[] args) {
//		
//		int portNumber = 10015;
//		
//		try{
//			//Cria o socket do servidor
//			ServerSocket server = new ServerSocket(portNumber);
//			
//			//Instancia as threads de conexão
//			while(true){
//				Socket client = server.accept();
//				new Tcliente(client).start();
//			
//			}
//			
//		}catch(Exception e){
//			System.out.println("Erro: " + e.getMessage());
//		}
//	}
//
//}
