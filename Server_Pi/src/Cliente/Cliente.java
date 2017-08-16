package Cliente;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

public class Cliente {

	public static void main(String[] args) {
		
		try{			
			Socket client = new Socket("pi", 10015);													//novo socket client
			
			ObjectInputStream recebida = new ObjectInputStream(client.getInputStream());				//prepara obj para receber
			Date data_atual = (Date)recebida.readObject();												//recebe a data do servidor
			
			JOptionPane.showMessageDialog(null,"Data recebida do servidor:" + data_atual.toString());
		    recebida.close();
		    System.out.println("Conexão encerrada");
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
		
		String msg = "matheus";
		String key = "1234567891234567";
		

		System.out.println(Encrypt.encrypt(msg, key));
	}

}
