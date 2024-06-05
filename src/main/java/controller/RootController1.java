package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class RootController1 implements Initializable{

	@FXML
    private Button button1;
	
	@FXML
	private TextField textField;
	
    @FXML
    void handleButtonClick() {
    	String text = textField.getText();
    	System.out.println("TextField 값 : "+ text);
    	int text_number = Integer.parseInt(text); 
    	
    	boolean flag = false;
    	
    	String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"; // URL 형식은 사용하는 Oracle 설정에 따라 다릅니다.
        String username = "oraman"; // DB 사용자 이름
        String password = "oracle"; // DB 비밀번호

        String query = "SELECT * FROM librarian where librarian_id = ?"; 

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement pstmt = conn.prepareStatement(query)){
        	 pstmt.setInt(1, text_number);
          	 ResultSet rs = pstmt.executeQuery();
          	 if(rs.next()) flag=true;
          	 else flag=false;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        
        if (flag) {
        	try {
        		Stage stage = (Stage)textField.getScene().getWindow();
        		String fxmlFile = "root2.fxml";
                System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));

                // 파일이 있는지 확인합니다.
                if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                    throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
                }
        		Parent secondScene = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
                Scene scene = new Scene(secondScene);
                stage.setScene(scene);
    		} catch (IOException ioException) {
    			// TODO Auto-generated catch block
    			ioException.printStackTrace();
    		}
        }else {
        	System.out.println("잘못된 사용자 이름입니다");
        	showAlert("잘못 입력하셨습니다");
        }
    	
    }
    
    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
        	handleButtonClick();
        }
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	
	}
	
	private void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    
		}
	 
	
}
