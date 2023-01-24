package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
			
			Integer nameDriverNumber = 0;
			
			File instantiatePath = new File(filePath);
			
			String saveModifiedFile = instantiatePath.getParent() + "\\newgltPabx.txt";
			
			Path getFilePath = Paths.get(saveModifiedFile);
			
			boolean exist = Files.exists(getFilePath);
					
				for(int i=0; exist == true; i++) {
					nameDriverNumber = i;
					saveModifiedFile = instantiatePath.getParent() + "\\newgltPabx" + nameDriverNumber + ".txt";
				
					getFilePath = Paths.get(saveModifiedFile);
					exist = Files.exists(getFilePath);
				}		
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(saveModifiedFile))){			
				for(String line : list) {
					bw.write(line);
					bw.newLine();
				}	
			}
			catch (IOException e){
				System.out.println("Erro: " + e);
			}
		}
		catch (IOException e){
			System.out.println("Erro: " + e);
		}
		sc.close();
	}

}
