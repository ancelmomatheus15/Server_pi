package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		
		String key = "1234567891234567";
		
		try{	
			//captura input do console
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Insira o texto a ser enviado: ");
			String text = "matheus";//in.readLine();
			
			//Encripta a leitura
			text = Encrypt.encrypt(text, key);
			System.out.println("CYPHER: "+text);
			
			//Cria o socket com nome de servidor e porta
			Socket client = new Socket("localhost", 10010);	 
			
			//Envia a leitura para o servidor
			ObjectOutputStream envio = new ObjectOutputStream(client.getOutputStream());
			envio.writeObject(text);
			
			//Encerra o processo
		    envio.close();
		    System.out.println("Conexão encerrada");
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
