package controller;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dao.Book_CopyDAO;
import bean.dao.ItemsDetailDAO;
import bean.dao.ItemsDetailDAOImpl;
import bean.dao.RoomDAO;
import bean.dto.Book;
import bean.dto.ItemsDetail;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class NewWindowController {
	private Book_CopyDAO bookCopyDAO;
	private ObservableList<ItemsDetail> itemsDetails;
	private RoomDAO roomDAO;
	private ItemsDetailDAO itemsDetailDAO;
	
	@FXML
	private TextField tfGreatTitle;
	@FXML
	private TextField tfsmallTitle;
	@FXML
	private TextField tfAuthor;
	@FXML
	private Label tfISBN;
	@FXML
	private TableView<ItemsDetail> tableView;
	@FXML
	private TableColumn<ItemsDetail, Boolean> checkBoxColumn;
	@FXML
	private TableColumn<ItemsDetail, Integer> bookIDColumn;
	@FXML
	private TableColumn<ItemsDetail, String> isbnColumn;
	@FXML
	private TableColumn<ItemsDetail, String> bNameColumn;
	@FXML
	private TableColumn<ItemsDetail, String> authorColumn;
	@FXML
	private TableColumn<ItemsDetail, Integer> pYearColumn;
	@FXML
	private TableColumn<ItemsDetail, String> libraryNameColumn;
	@FXML
	private TableColumn<ItemsDetail, String> roomNameColumn;
	@FXML
	private TableColumn<ItemsDetail, String> bStatusColumn;
	
	private ItemsDetail jumpData;
	private BookDAO bookDAO;
	
	/**
	 * @author 나선주
	 * @param rowData
	 */
	
	public void initData(ItemsDetail rowData) {
		// TODO Auto-generated method stub
		
		//DAO 초기화
		bookDAO = new BookDAOImpl();
//		bookCopyDAO = new Book_CopyDAOImpl();
		itemsDetailDAO = new ItemsDetailDAOImpl();
		
		tfGreatTitle.setText(rowData.getBName());
		tfAuthor.setText(rowData.getAuthor());
		tfsmallTitle.setText(rowData.getBName());
		tfISBN.setText(rowData.getIsbn());
		System.out.println("isbn은 "+ rowData.getIsbn());
		
		jumpData = rowData;
		
		ObservableList<ItemsDetail> itemsDetails;
		itemsDetails = itemsDetailDAO.getItemsDetailByISBN(rowData.getIsbn());
		itemsDetails.forEach(itemsDetail -> System.out.println(itemsDetail.toString())); //받아온 값 출력
		
		tableView.setItems(itemsDetails);
	}
	
	@FXML
    public void initialize() {
        // 여기에 기본 초기화 작업을 수행할 수 있습니다.
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
        checkBoxColumn.setEditable(true);

        bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        bNameColumn.setCellValueFactory(new PropertyValueFactory<>("bName"));   
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author")); 
        pYearColumn.setCellValueFactory(new PropertyValueFactory<>("pYear"));  
        libraryNameColumn.setCellValueFactory(new PropertyValueFactory<>("libraryName"));
        roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        bStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bStatus"));
        
        //TextField 더블클릭 이벤트 설정
        setTextFieldDoubleClick(tfGreatTitle);
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
		Book newBook = new Book(tfISBN.getText(), tfGreatTitle.getText(), tfAuthor.getText(), jumpData.getPYear());
		bookDAO.updateBooks(newBook);
		System.out.println("수정하기 버튼이 눌렸습니다");
	}
	
	@FXML
	private void closeWindow() {
		Stage stage = (Stage)tfGreatTitle.getScene().getWindow();
		stage.close();
	}
}
