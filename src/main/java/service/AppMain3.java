package service;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain3 extends Application {

	@Override
	public void start(Stage primaryStage) {
		String fxmlFile = "root3.fxml";
        System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));

        // 파일이 있는지 확인합니다.
        if (getClass().getClassLoader().getResource(fxmlFile) == null) {
            throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
        }
		
		try {
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlFile));
//			Parent root = FXMLLoader.load(getClass().getResource("root3.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("통계 화면입니다");
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
