package gui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ViewController implements Initializable {

	private List<String> list = new ArrayList<>();
	
	@FXML
	private TextField textFieldPath;

	@FXML
	private Button modfyButton;

	@FXML
	public void onTextFieldPathAction() {
		textFieldPath.getText();
	}

	@FXML
	public void onModfyButtonAction() throws FileNotFoundException, IOException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(textFieldPath.getText()))){
			
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
			
			File instantiatePath = new File(textFieldPath.getText());
			
			String saveModifiedFile = instantiatePath.getParent() + "\\newgltPabx.txt";
			
			Path getFilePath = Paths.get(saveModifiedFile);
			
			boolean exist = Files.exists(getFilePath);
					
				for(int i=2; exist == true; i++) {
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
				Alerts.showAlert("IO Exception", "Error to modify the Driver", e.getMessage(), AlertType.ERROR);
			}
			
			Alerts.showConfirmation("Driver modification", AlertType.CONFIRMATION);
			
			list.clear();
			
		}
		catch (IOException e){
			Alerts.showAlert("IO Exception", "Error to reade the file", e.getMessage(), AlertType.ERROR);
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

	}

}
