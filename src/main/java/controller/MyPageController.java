package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.dao.LibrarianDAO;
import bean.dao.LibrarianDAOImpl;
import bean.dao.ReviewDAO;
import bean.dao.ReviewDAOImpl;
import bean.dto.Librarian;
import bean.dto.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyPageController implements Initializable{
	private LibrarianDAO librarianDAO;
	private Librarian librarian;
	private Review review;
	private ReviewDAO reviewDAO;
	
	@FXML
	private Label nameTf;
	@FXML
	private Label librarianIdTf;
	@FXML
	private TextField emailTf;
	@FXML
	private TextField phoneTf;
	@FXML
	private TextField reviewTf;
	@FXML
	private VBox container;
	
	public void initData() {
		librarianDAO = new LibrarianDAOImpl();
		reviewDAO = new ReviewDAOImpl();
		
		Librarian newone = librarianDAO.getLibrarianById(librarian.getLibrarian_id());
		nameTf.setText(librarian.getL_name());
		librarianIdTf.setText(String.valueOf(librarian.getLibrarian_id()));
		emailTf.setText(librarian.getL_email());
		phoneTf.setText(librarian.getL_phone());
		
		ObservableList<Review> reviews = reviewDAO.selectById(librarian.getLibrarian_id());
		reviewTf.setText(String.valueOf(reviews.size()));
		for(Review review : reviews) {
			TextArea tA = new TextArea();
			String reviewText = review.getReviewText();
			tA.setText(String.valueOf(review.getRating())+"점"+"\n"+"isbn : "+review.getIsbn()+"\n"+reviewText);
			tA.setWrapText(true);
			tA.setPrefHeight(80);
			container.getChildren().add(tA);
		}
	}
	
	private void setTextFieldDoubleClick(TextField textField) {
		// TODO Auto-generated method stub
		textField.setEditable(false);
		textField.setOnMouseClicked(event -> {
			if(event.getClickCount() == 2) {
				textField.setEditable(true);
				textField.requestFocus();
			}
		});
	}
	
	@FXML
	private void pushUpdateButton(ActionEvent event) {
		Librarian librarian = new Librarian(Integer.parseInt(librarianIdTf.getText()),nameTf.getText(), emailTf.getText(), phoneTf.getText());
//		bookDAO.updateBooks(newBook);
		librarianDAO.updateLibrarian(librarian);
		System.out.println("수정하기 버튼이 눌렸습니다");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		
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
    void goPage3(ActionEvent event) {
    	try {
    		String fxmlFile = "root3.fxml";
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
            
            RootController3 controller = loader.getController();
            controller.setLibrarian(librarian);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
		if(librarian!=null) {
			System.out.println(librarian.getL_name());
		}else System.out.println("librarian이 없습니다.");
	}
	
}
