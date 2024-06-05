package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootController2 implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

    @FXML
    private MenuItem closeButton;
    

    @FXML
    void closeWindow(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    private Button goPage3Button;

    @FXML
    void goPage3(ActionEvent event) {
    	try {
    		Parent secondScene = FXMLLoader.load(getClass().getResource("/resources/service/root3.fxml"));
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
    private MenuItem newWindowMenuItem;

    @FXML
    private void openNewWindow(ActionEvent event) {
        Stage primaryStage = (Stage) newWindowMenuItem.getParentPopup().getOwnerWindow();
        Stage newStage = new Stage();
        newStage.setTitle("새 창");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("root2.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Popup Window");
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
