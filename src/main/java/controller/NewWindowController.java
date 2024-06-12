package controller;

import java.io.IOException;
import java.sql.SQLException;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dao.Book_CopyDAO;
import bean.dao.ItemsDetailDAO;
import bean.dao.ItemsDetailDAOImpl;
import bean.dao.ReviewDAO;
import bean.dao.ReviewDAOImpl;
import bean.dao.RoomDAO;
import bean.dto.Book;
import bean.dto.ItemsDetail;
import bean.dto.Review;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
	private VBox container;
	
	@FXML
	private Label tfISBN;
	
	private ItemsDetail jumpData;
	private BookDAO bookDAO;
	private ReviewDAO reviewDAO;
	
	/**
	 * @author 나선주
	 * @param rowData
	 * @throws IOException 
	 * @throws SQLException 
	 */
	
	public void initData(ItemsDetail rowData)  {
		// TODO Auto-generated method stub
		
		//DAO 초기화
		bookDAO = new BookDAOImpl();
//		bookCopyDAO = new Book_CopyDAOImpl();
		itemsDetailDAO = new ItemsDetailDAOImpl();
		reviewDAO = new ReviewDAOImpl();
		
		tfGreatTitle.setText(rowData.getBName());
		tfAuthor.setText(rowData.getAuthor());
		tfsmallTitle.setText(rowData.getBName());
		tfISBN.setText(rowData.getIsbn());
		System.out.println("isbn은 "+ rowData.getIsbn());
		
		jumpData = rowData;
		
		ObservableList<ItemsDetail> itemsDetails;
		itemsDetails = itemsDetailDAO.getItemsDetailByISBN(rowData.getIsbn());
		itemsDetails.forEach(itemsDetail -> System.out.println(itemsDetail.toString())); //받아온 값 출력
		
		ObservableList<Review> reviews;
		System.out.println("isbn은"+rowData.getIsbn());
		reviews = reviewDAO.selectisbn(rowData.getIsbn());
		if(reviews.size()!=0) {
			System.out.println("review의 size는"+reviews.size());
			System.out.println("isbn은 캬캬캬"+reviews.get(0).getIsbn());
		}else {
			System.out.println("review가 없습니다.");
		}
		
		for(Review review : reviews) {
			TextArea tA = new TextArea();
			String reviewText = review.getReviewText();
			tA.setText(String.valueOf(review.getRating())+"점"+"\n"+reviewText);
			tA.setWrapText(true);
			tA.setPrefHeight(100);
			container.getChildren().add(tA);
		}
		
	}
	
	@FXML
    public void initialize() {
        // 여기에 기본 초기화 작업을 수행할 수 있습니다.

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
