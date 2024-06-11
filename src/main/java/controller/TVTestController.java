package controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dao.Book_CopyDAO;
import bean.dao.Book_CopyDAOImpl;
import bean.dto.BCF;
import bean.dto.Book;
import bean.dto.Book_Copy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DatabaseUtil;

public class TVTestController implements Initializable{
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
	
	private BookDAO bookDAO;
	private Book_CopyDAO bookCopyDAO;
    
	ObservableList<BCF> data;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		//DAO 초기화
		bookDAO = new BookDAOImpl();
		bookCopyDAO = new Book_CopyDAOImpl();
		
		TableColumn actionCol = new TableColumn("Action");
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
		
        tableView.getColumns().addAll(actionCol, bookIdCol, isbnCol, bNameCol, authorCol, pYearCol, bCopyCol, pDateCol
        		, libraryNameCol, roomNameCol, bStatusCol);
        
        actionCol.setCellValueFactory(
        		new PropertyValueFactory<BCF, String>("checkbox")
        		);
        
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
		int bookIDTfText = getNextBookID();
		String isbnTfText = isbnTf.getText();
		String bNameFieldText = bNameField.getText();
		String authorTfText = authorTf.getText();
		int pYearTfText = Integer.parseInt(pYearTf.getText());
		int bCopyTfText = Integer.parseInt(bCopyTf.getText());
		
		String pDateTfText = String.valueOf(datePk.getValue());
		
		
		
		LocalDate localDate = datePk.getValue();
		String dateString = String.valueOf(localDate);
		// 날짜 형식을 변환하기 위한 SimpleDateFormat 객체 생성
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yy/MM/dd");
        String formattedDate = null;
        try {
            // String을 java.util.Date로 변환
            Date utilDate = originalFormat.parse(dateString);
            
            // java.util.Date를 java.sql.Date로 변환
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            // java.util.Date를 원하는 형식의 문자열로 변환
            formattedDate = targetFormat.format(utilDate);
            
            // 결과 출력
            System.out.println("Formatted Date: " + formattedDate);
            System.out.println("SQL Date: " + sqlDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
		String libraryNameText = libraryMenuButton.getText();
		String roomMenuText = roomMenuButton.getText();
		String bStatusText = statusMenuButton.getText();
		System.out.println("받아온결과는 "+bookIDTfText+isbnTfText+bNameFieldText+authorTfText+pYearTfText
				+bCopyTfText+pDateTfText+libraryNameText+roomMenuText+bStatusText);
//		data = FXCollections.observableArrayList(
//				new BCF(bookIDTfText, isbnTfText, bNameFieldText, authorTfText, pYearTfText, bCopyTfText, pDateTfText
//						, libraryNameText, roomMenuText, bStatusText)
//				);
//		tableView.setItems(data);
		
		for(int i=0; i<bCopyTfText; i++) {
			tableView.getItems().add(new BCF(String.valueOf(bookIDTfText+i), isbnTfText, bNameFieldText, authorTfText, pYearTfText, bCopyTfText, formattedDate
					, libraryNameText, roomMenuText, bStatusText)
					);
		}
		
		isbnTf.clear();
		bNameField.clear();
		authorTf.clear();
		pYearTf.clear();
		bCopyTf.clear();
		datePk.setValue(null);
		libraryMenuButton.setText("도서관");
        roomMenuButton.setText("자료실");
        statusMenuButton.setText("자료상태");
		
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
	
	@FXML
    private void deleteSelectedRows(ActionEvent event) {
        ObservableList<BCF> data = tableView.getItems();
        ObservableList<BCF> dataListRemove = FXCollections.observableArrayList();

        for(BCF bean : data)
        {
           if(bean.getCheckbox().isSelected())
           {
             dataListRemove.add(bean);
           }
        }
        data.removeAll(dataListRemove);
    }
    
	@FXML
    private void saveSelectedRows(ActionEvent event) throws ParseException {
        ObservableList<BCF> data = tableView.getItems();
        ObservableList<BCF> saveList = FXCollections.observableArrayList();
        List<Book> bookList = new ArrayList<>();
        List<Book_Copy> bookCopyList = new ArrayList<>();
        int roomid;
        int statusid;
        for(BCF bean : data)
        {
           if(bean.getCheckbox().isSelected())
           {
              bookList.add(new Book(bean.getIsbn(), bean.getBName(), bean.getAuthor(), bean.getPYear()));
              if(bean.getRoomName()=="[남산4동]일반자료실") roomid=1;
              else if(bean.getRoomName()=="[해안동]일반자료실") roomid=2;
              else  roomid=3;
              
              if(bean.getBStatus()=="대출불가능") statusid=0;
              else if(bean.getBStatus()=="대출가능") statusid=1;
              else  statusid=2;
              
              bookCopyList.add(new Book_Copy(bean.getBookId(), bean.getIsbn(), bean.getBCopy(), bean.getPDate(), roomid, statusid));
           }
        }
        bookDAO.insertBooks(bookList);
        bookCopyDAO.insertBookCopys(bookCopyList);
        System.out.println("save버튼을 눌렀고, 관련 메소드를 실행했어요");
        data.clear();
        
     // 입력되었음을 알리는 Alert
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("정보");
        alert.setHeaderText(null);
        alert.setContentText("입력되었습니다");
        alert.showAndWait();
        
    }
	
	
	
	private int getNextBookID() {
        int nextBookID = 0;
        String sql = "SELECT MAX(to_number(bookID)) FROM book_Copy";
        try (Connection conn = DatabaseUtil.getConnection();
        	PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                nextBookID = resultSet.getInt(1)+1;
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextBookID;
    }
}
