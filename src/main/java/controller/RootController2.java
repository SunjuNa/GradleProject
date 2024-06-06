package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dto.Book;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootController2 implements Initializable{
	private BookDAO bookDAO;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		bookDAO = new BookDAOImpl();
	}

    @FXML
    private MenuItem closeButton;
    @FXML
	private TextField searchTextField;

    @FXML
    private MenuBar menuBar;
    @FXML
    private MenuButton menuButtonAuthor;
    
    @FXML
    private String searchType = "전체";
    
    @FXML
    private String selectedAuthor;
    @FXML
    private TableView<String> resultTable;

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
    
//    @FXML
//    private void handleExit() {
//    	//종료 버튼 처리
//    }
//    
//    @FXML
//    private void selectAuthor() {
//    	selectedAuthor = "Selected Author";
//    	menuButtonTotal.setText(selectedAuthor);
//    }
//    
//    @FXML
//    private void handleSearch() {
//    	String searchtext = searchTextField.getText();
//    	System.out.println("메뉴TextField 값 : "+ searchtext);
//    	if(selectedAuthor!=null && !selectedAuthor.isEmpty() && searchtext!=null && !searchtext.isEmpty()) {
//    		List<String> books = bookDAO.getBooksByAuthor(selectedAuthor);
//    		resultTable.getItems().clear();
//    		ObservableList<String> items = (ObservableList<String>)resultTable.getItems();
//    		items.addAll(books);
//    	}
    	
    	    
//    }
    
     @FXML
     private void handleSearchTypeMenuItem(ActionEvent event) {
    	 MenuItem menuItem = (MenuItem) event.getSource();
    	 searchType = menuItem.getText();
    	 menuButtonAuthor.setText(searchType);
    	 System.out.println("menubutton에서 "+menuButtonAuthor.getText()+"(이/가) 선택되었습니다");
     }
     
     @FXML
     private void handleSearch() {
    	 String searchText = searchTextField.getText();
    	 System.out.println("메뉴TextField 값 : "+ searchText);
    	 if(searchText !=null && !searchText.isEmpty()) {
    		 List<Book> books;
    		 if(searchType.equals("전체")) {
    			 //둘다 해당
    			 books = bookDAO.getBooksByAuthor(searchText);
    		 }else if(searchType.equals("저자")) {
    			 books = bookDAO.getBooksByAuthor(searchText);
    		 }else {
    			 books = bookDAO.getBooksByB_name(searchText);
    		 }
    		 System.out.println("books는 "+books);
    		 resultTable.getItems().clear();
//    		 resultTable.getItems().addAll(books);
    	 }
     }
     
    
}
