package Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer extends Thread{
	
	static ServerSocket server = null;
	static Socket conexao;
	
	public MultiServer(Socket s) {
		conexao = s; 
	}

	public static void main(String args[]){
		
		try{//tentando criar uma conexao
		
			server = new ServerSocket(11015);//Cria um SocketServer com porta 40000
			
			while(true){
	           /* Cria um objeto Socket, mas passando informa��es caracter�sticas de um servidor,
	            *no qual somente abre uma porta e aguarda a conex�o de um cliente 
	            */
				conexao = server.accept();
				//cria uma thread que envia a conexao
				Thread t = new MultiServer(conexao);
				//inicia a thread t
				t.start();
			}
		}catch(IOException e){
			System.out.println("IOException "+e);
		}
	}

	public void run(){
		try{
			// Cria uma buffer que ir� armazenar as informa��es enviadas pelo cliente
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
			
            // Cria uma stream de s�ida para retorno das informa��es ao cliente
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
			
            // Faz a leitura das informa��es enviadas pelo cliente as amazenam na vari�vel "EscritaCliente"
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
//			//Instancia as threads de conex�o
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
