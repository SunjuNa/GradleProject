package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import bean.dto.BCF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TVTestController implements Initializable{
    @FXML
    private TextField bookIDTf;
    @FXML
    private TextField isbnTf;
    @FXML
    private TextField bNameField;
    @FXML
    private TextField authorTf;
    @FXML
    private TextField pYearTf;
    @FXML
    private TextField bCopyTf;
    @FXML
    private DatePicker datePk;
	@FXML
	private TableView tableView;
	
	@FXML
	private String libraryType = "도서관";
	@FXML
	private MenuButton libraryMenuButton;
	@FXML
	private String roomType = "자료실";
	@FXML
	private MenuButton roomMenuButton;
	@FXML
	private String statusType = "자료상태";
	@FXML
	private MenuButton statusMenuButton;
	
    
	ObservableList<BCF> data;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//DAO 초기화
		
		TableColumn bookIdCol = new TableColumn("BookId");
        TableColumn isbnCol = new TableColumn("isbn");
        TableColumn bNameCol = new TableColumn("bName"); 
        TableColumn authorCol = new TableColumn("author"); 
        TableColumn pYearCol = new TableColumn("pYear");
        TableColumn bCopyCol = new TableColumn("bCopy");
        TableColumn pDateCol = new TableColumn("pDate");
        TableColumn libraryNameCol = new TableColumn("libraryName");
        TableColumn roomNameCol = new TableColumn("roomName");
        TableColumn bStatusCol = new TableColumn("bStatus");
		
        tableView.getColumns().addAll(bookIdCol, isbnCol, bNameCol, authorCol, pYearCol, bCopyCol, pDateCol
        		, libraryNameCol, roomNameCol, bStatusCol);
        
		 bookIdCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("bookId")
				 );
		 isbnCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("isbn")
				 );
		 bNameCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("bName")
				 );
		 authorCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("author")
				 );
		 pYearCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, Integer>("pYear")
				 );
		 bCopyCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, Integer>("bCopy")
				 );
		 pDateCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, LocalDate>("pDate")
				 );
		 libraryNameCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("libraryName")
				 );
		 roomNameCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("roomName")
				 );
		 bStatusCol.setCellValueFactory(
				 new PropertyValueFactory<BCF, String>("bStatus")
				 );
	}
	
	@FXML
	private void handleInput() {
		String bookIDTfText = bookIDTf.getText();
		String isbnTfText = isbnTf.getText();
		String bNameFieldText = bNameField.getText();
		String authorTfText = authorTf.getText();
		int pYearTfText = Integer.parseInt(pYearTf.getText());
		int bCopyTfText = Integer.parseInt(bCopyTf.getText());
		String pDateTfText = String.valueOf(datePk.getValue());
		String libraryNameText = libraryMenuButton.getText();
		String roomMenuText = roomMenuButton.getText();
		String bStatusText = statusMenuButton.getText();
		System.out.println("받아온결과는 "+bookIDTfText+isbnTfText+bNameFieldText+authorTfText+pYearTfText
				+bCopyTfText+pDateTfText+libraryNameText+roomMenuText+bStatusText);
		data = FXCollections.observableArrayList(
				new BCF(bookIDTfText, isbnTfText, bNameFieldText, authorTfText, pYearTfText, bCopyTfText, pDateTfText
						, libraryNameText, roomMenuText, bStatusText)
				);
		tableView.setItems(data);
	}
	
	@FXML
	private void handleLibraryMenuItem(ActionEvent event) {
		MenuItem menuItem = (MenuItem) event.getSource();
		libraryType = menuItem.getText();
		libraryMenuButton.setText(libraryType);
   	 	System.out.println("libraryMenuButton에서 "+libraryMenuButton.getText()+"(이/가) 선택되었습니다");
	}
	
	@FXML
	private void handleRoomMenuItem(ActionEvent event) {
		MenuItem menuItem = (MenuItem) event.getSource();
		roomType = menuItem.getText();
		roomMenuButton.setText(roomType);
   	 	System.out.println("roomMenuButton에서 "+roomMenuButton.getText()+"(이/가) 선택되었습니다");
	}
	@FXML
	private void handleStatusMenuItem(ActionEvent event) {
		MenuItem menuItem = (MenuItem) event.getSource();
		statusType = menuItem.getText();
		statusMenuButton.setText(statusType);
   	 	System.out.println("statusMenuButton에서 "+statusMenuButton.getText()+"(이/가) 선택되었습니다");
	}
}
