package Cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		
		String key = "1234567891234567";
		
		try{	
			//Cria o socket com nome de servidor e porta
			Socket client = new Socket("localhost", 10010);	
			System.out.println("Cliente- CONECTADO");
			
			//captura input do console
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Insira o texto a ser enviado: ");
			String text = "Gladys Canete";//in.readLine();
			
			//Encripta a leitura
			text = Encrypt.encrypt(text, key);
			System.out.println("Cliente- CYPHER: "+text);			 
			
			//Envia a leitura para o servidor
			ObjectOutputStream envio = new ObjectOutputStream(client.getOutputStream());
			envio.writeObject(text);
			
			//Encerra o processo
		    envio.close();
		    System.out.println("Cliente- Conexão encerrada");
			
		}catch(Exception e){
			System.out.println("Cliente- Erro: " + e.getMessage());
		}
	}
}
