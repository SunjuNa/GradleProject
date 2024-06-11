package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.dao.LibrarianDAO;
import bean.dao.LibrarianDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyPageController implements Initializable{
	private LibrarianDAO librarianDAO;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		librarianDAO = new LibrarianDAOImpl();
//		librarianDAO.getLibrarianById(0)
		
	}

	
	@FXML
    void goPage2(ActionEvent event) {
    	try {
    		String fxmlFile = "root2.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
    		Parent secondScene = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
            Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@FXML
    void goPage3(ActionEvent event) {
    	try {
    		String fxmlFile = "root3.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
    		Parent secondScene = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
            Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
