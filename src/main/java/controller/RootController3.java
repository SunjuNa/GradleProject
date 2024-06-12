package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.dto.Librarian;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RootController3 implements Initializable{
	
	private Librarian librarian;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    @FXML
    private Button goPage2Button;

    @FXML
    void goPage2(ActionEvent event) {
    	try {
    		String fxmlFile = "root2.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    		Parent secondScene = loader.load();
            Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            RootController2 controller = loader.getController();
            controller.setLibrarian(librarian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void goMyPage(ActionEvent event) {
    	try {
    		String fxmlFile = "MyPage.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    		Parent secondScene = loader.load();            
    		Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            MyPageController controller = loader.getController();
            controller.setLibrarian(librarian);
            controller.initData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setLibrarian(Librarian librarian) {
    	this.librarian = librarian;
    }
}
