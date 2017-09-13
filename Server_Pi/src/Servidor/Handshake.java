package Servidor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Handshake {
	
	private static void cadastraMac(String insereMac) {
		escreveTxt(insereMac);
	}
	
	private static boolean buscaMac(String buscaMac) {
		List<String> macs = new ArrayList<String>();
		
		macs = leTxt();
		
		for (String string : macs) {
			if(buscaMac.equals(string)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}
	
	private static void escreveTxt(String mac) {
		
		File caminho = new File("C:\\Users\\matheus.pita\\Desktop\\regMac.txt");
		String texto = mac;
		FileWriter arquivo;
		
		try {
			if(caminho.exists()) {
				arquivo = new FileWriter(caminho);
				arquivo.append(texto);
				arquivo.close();
			}
			else {
				arquivo = new FileWriter(new File("C:\\Users\\matheus.pita\\Desktop\\regMac.txt"));
				arquivo.write(texto);
				arquivo.close();
			}			
			
		} catch (IOException e) {
			System.out.println("ERRO! Erro de leitura");
			
		} catch (Exception e) {
			System.out.println("ERRO!");
		}
}

	private static List leTxt() {
		
		File arquivo = new File("C:\\Users\\matheus.pita\\Desktop\\regMac.txt");
		List<String> enderecos = new ArrayList<String>();
		
		try {
			FileReader leitorArquivo = new FileReader(arquivo); 
			BufferedReader leitor = new BufferedReader(leitorArquivo);
			String texto = null;
		
			while(leitor.readLine() != null){
				enderecos.add(leitor.readLine());
			}
		
			leitorArquivo.close();
			leitor.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("ERRO! Arquivo não encontrado");
		}		
		catch(IOException e) {
			System.out.println("ERRO! Erro de leitura");
		}
		return enderecos;	
	}
}