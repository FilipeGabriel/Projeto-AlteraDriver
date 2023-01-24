//FUNCIONOU
package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<>();
		
		System.out.print("Insira o caminho bilhetes desejado: ");
		String filePath = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
			
			Integer number = 0;			
			String rl = br.readLine();
			
			while(rl != null){
				if(rl.isEmpty()) {
					list.add(rl);
					rl = br.readLine();
				}
				else if(rl.charAt(0) == '['){
	
					if (rl.length() > 11) {
						list.add("[Bilhete" + number + ".Campos]");
					}
					else if(rl.charAt(1) == 'G'){
						list.add(rl);
					}
					else{
						number += 1;
						list.add("[Bilhete" + number + "]");	
					}
					rl = br.readLine();
				}
				else {
					list.add(rl);
					rl = br.readLine();
				}
			}
			
			list.forEach(System.out::println);
			/*
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
			
				
			
			}
			catch (IOException e){
				System.out.println("Erro: " + e);
			}*/
		}
		catch (IOException e){
			System.out.println("Erro: " + e);
		}
		sc.close();
	}

}