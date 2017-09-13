package Cliente;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Hand_Shake_Test {
	public boolean HandShake(){
		// ip necessario para verificar ip e mac
		InetAddress ip;
		//tentará alcançar o ip e o mac da maquina
		try {
			//pega o ip e mostra no console
			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			//cria a interface de rede para conexão com a maquina a partir do ip adquirido
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			//pega o endereço mac byte por byte
			byte[] mac = network.getHardwareAddress();
			//formata o mac para o padrão de escrita
			System.out.print("Current MAC address : ");
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.print(sb.toString());
			boolean verificacao = false;
			//verifica se o mac adquirido faz parte da lista de macs pertecentes aquela rede
			verificacao = listaMac(sb.toString());
			//caso retorne verdadeiro, o sistema envia para o cliente que por sua vez conseguira ter acesso ao servidor
			System.out.println(verificacao);
			return verificacao;

		} catch (UnknownHostException e) {

			e.printStackTrace();
			return false;

		}
		catch (SocketException e){

			e.printStackTrace();
			return false;

		}

		
		
		
	}

	private static boolean listaMac(String TesteMac) {
		//cria uma lista de macs para verificar o mac que esta tentando entrar no servidor
		boolean check = false;
		String MacLocal = "78-2B-CB-BD-9E-50";
		ArrayList Macs =new ArrayList();
		Macs.add(MacLocal);
		for(int i=0;i<Macs.size();i++){
			if(TesteMac.equals(Macs.get(i))){
				check = true;
			}
		}
		return check;
	}
	
	}

