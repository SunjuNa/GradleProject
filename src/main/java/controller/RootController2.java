package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dto.Book;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootController2 implements Initializable{
	private BookDAO bookDAO;
	
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
    private TableView<Book> tableView;
    
    @FXML
    private MenuItem newWindowMenuItem;
    
    @FXML
    private TableColumn<Book, Boolean> checkBoxColumn;
    @FXML
    private TableColumn<Book, String> isbnColumn;
    @FXML
    private TableColumn<Book, String> bNameColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, Integer> pYearColumn;
    
    @FXML
    private TextField totalinfotextfield;
    
    @FXML
    private Button goPage3Button;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		bookDAO = new BookDAOImpl();
	}

    @FXML
    void closeWindow(ActionEvent event) {
    	Platform.exit();
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
    		 ObservableList<Book> books;
    		 if(searchType.equals("전체")) {
    			 //둘다 해당
    			 books = bookDAO.getBooksByAuthor(searchText);
    			 books.addAll(bookDAO.getBooksByB_name(searchText));
    		 }else if(searchType.equals("저자")) {
    			 books = bookDAO.getBooksByAuthor(searchText);
    		 }else {
    			 books = bookDAO.getBooksByB_name(searchText);
    		 }
    		 books.forEach(book -> System.out.println(book.toString())); //받아온 값 출력
    		 totalinfotextfield.setText(String.valueOf(books.size())); //total: 건수 표시
			 
			 tableView.setItems(books);
			 checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
			 checkBoxColumn.setEditable(true);
			 isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		     bNameColumn.setCellValueFactory(new PropertyValueFactory<>("bName"));
		     authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		     pYearColumn.setCellValueFactory(new PropertyValueFactory<>("pYear"));
		     
		     //상세페이지
		     tableView.setOnMouseClicked(event -> {
		    	    if (event.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) {
		    	    	Book rowData = tableView.getSelectionModel().getSelectedItem(); // 선택한 행의 데이터 가져오기
		    	        
		    	        // 새 창을 열기 위한 코드 작성
		    	        // 새 창을 열 때 선택한 행의 데이터를 전달할 수 있습니다.
		    	        // 예: 새 창을 생성하는 메서드를 호출하고 선택한 데이터를 전달합니다.
		    	        openNewWindow(rowData);
		    	    }
		    	});
    	 }
    	 else {
    		 System.out.println("검색 내용이 없습니다");
         	 showAlert("검색 내용이 없습니다");
    	 }
     }
     
     //table의 각행을 더블클릭시 나오는 화면
     private void openNewWindow(Book rowData) {
		// TODO Auto-generated method stub
    	// 새 창을 생성하고 선택한 데이터를 전달합니다.
    	    Stage stage = new Stage();
    	    
    	    String fxmlFile = "bookDetailPage.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
    	    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    	    
    	    Parent root;
			try {
				root = loader.load();
				NewWindowController controller = loader.getController();
				controller.initData(rowData); // 선택한 데이터 전달
				stage.setScene(new Scene(root));
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void showAlert(String message) {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Error Dialog");
         alert.setHeaderText(null);
         alert.setContentText(message);
         alert.showAndWait();
     
 		}
    
     @FXML
     private void handleKeyPress() {
    	 searchTextField.setOnKeyPressed(event -> {
    		 if(event.getCode()==KeyCode.ENTER) {
    			 handleSearch();
    		 }
    	 });
     }
}
