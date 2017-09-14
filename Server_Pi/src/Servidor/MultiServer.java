package Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
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
			
            /* Faz a leitura das informações enviadas pelo cliente as amazenam na variável "cypher"
             * e decifra a informação, exibindo no console
            */
            String cypher = entrada.readLine();
            String values[] = tratarMac(cypher);
            
            if(handshake(values[1])==true) {
            	cypher = values[0];
            
            	int aux = cypher.length();
            	cypher = cypher.substring(7, aux);
            	System.out.println("Servidor- Informação original: "+ cypher);
            	System.out.println("Servidor- Decrypt: "+ Decrypt.decrypt(cypher, key)); 
            	
            	// Cria uma stream de sáida para retorno das informações ao cliente
            	ObjectOutputStream saida = new ObjectOutputStream(aceitar.getOutputStream());
            	((ObjectOutput) aceitar).flush();
            	saida.writeObject("transmissão ok");
            }
            else {
            	// Cria uma stream de sáida para retorno das informações ao cliente
            	ObjectOutputStream saida = new ObjectOutputStream(aceitar.getOutputStream());
            	((ObjectOutput) aceitar).flush();
            	saida.writeObject("ERRO! Falha de segurança");
            }     
            
		}catch(IOException e){
			System.out.println("Servidor- IOException "+e.getMessage());
		}
	}

	private boolean handshake(String mac) {		
		return Handshake.buscaMac(mac);
	}

	private String[] tratarMac(String cypher) {
		String arrayReturn[] = new String [2];
		
		String[] arrayCorte = cypher.split(",");
        String mac = arrayCorte[1].trim();
        int aux = mac.length();
        mac = mac.substring(0, aux-1);
        arrayReturn[1] = mac;
        
        String cifra = "";
        cifra = arrayCorte[0].trim();
        aux = cifra.length();
        cifra = cifra.substring(1, aux);
        arrayReturn[0] = cifra;
        
		return arrayReturn;
	}
}