package service;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		String fxmlFile = "root2.fxml";
        System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));

        // 파일이 있는지 확인합니다.
        if (getClass().getClassLoader().getResource(fxmlFile) == null) {
            throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
        }
		
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
//			Parent root = FXMLLoader.load(getClass().getResource("root2.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("AppMain");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
