import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {

		int portNumber = 10015;
		
		try{
			ServerSocket server = new ServerSocket(portNumber);									//instancia um novo servidor
			System.out.println("servidor escutando a porta " + portNumber);
			
			while(true){
				Socket conectado = server.accept();
				System.out.println("Cliente: " + conectado.getInetAddress().getHostAddress()); //identifica quem conectou no servidor
				
				ObjectOutputStream com = new ObjectOutputStream(conectado.getOutputStream()); //recebe do cliente
				com.flush();
				
				com.writeObject(new Date());												 //envia de volta pro cliente
				com.close();
				
				conectado.close();
			}
			
			
		}catch(Exception e){
			System.out.println("Erro: " + e.getMessage());
		}
	}
}
