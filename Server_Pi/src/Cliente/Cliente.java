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
			Socket client = new Socket("pi", 10015);													
			
			//captura input do console
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Insira o texto a ser enviado: ");
			String text = in.readLine();
			
			//Encripta a leitura
			text = Encrypt.encrypt(text, key);
			
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
