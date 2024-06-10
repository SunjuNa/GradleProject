package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import bean.dao.BookDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.DatabaseUtil;

public class D4 {
	@FXML
	private VBox mainVBox;
	@FXML
	private HBox firstHBox;
	@FXML
	private TextField isbnTf;
	@FXML
	private TextField bNameTf;
	@FXML
	private TextField authorTf;
	@FXML
	private TextField pYearTf;
	@FXML
	private MenuButton libraryMenuButton;
	@FXML
	private MenuButton roomMenuButton;
	@FXML
	private MenuButton statusMenuButton;
	@FXML
	private DatePicker datePicker;
	@FXML
	private TextField bCopyTf;
	
	@FXML
	private String libraryType = "도서관";
	@FXML
	private String roomType = "자료실";
	@FXML
	private String statusType = "자료상태";
	
	@FXML
	private BookDAO bookDAO;
	
	private List<HBox> hBoxList = new ArrayList<>();

	@FXML
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		hBoxList.add(firstHBox);
	}
	
	@FXML
	private void handleCloneButton(){
		HBox newHBox = new HBox();
		newHBox.setAlignment(firstHBox.getAlignment());
		newHBox.setPrefHeight(firstHBox.getPrefHeight());
		newHBox.setPrefWidth(firstHBox.getPrefWidth());
		
		// Creating new empty TextFields
		// Creating new empty TextFields, MenuButtons, and DatePicker
		//bookId
        TextField isbnTf = createEmptyTextField();
        TextField bNameTf = createEmptyTextField();
        TextField authorTf = createEmptyTextField();
        TextField pYearTf = createEmptyTextField();
        MenuButton libraryMenuButton = createLibraryMenuButton();
        MenuButton roomMenuButton = createRoomMenuButton();
        MenuButton statusMenuButton = createStatusMenuButton();
        DatePicker datePicker = createDatePicker();
        TextField bCopyTf = createEmptyTextField();

        // Adding a button with the same clone action inside a label
        Button cloneButton = new Button("+");
        cloneButton.setOnAction(event -> handleCloneButton());
        Label buttonLabel = new Label();
        buttonLabel.setGraphic(cloneButton);
        buttonLabel.setAlignment(javafx.geometry.Pos.CENTER);
        buttonLabel.setPrefHeight(41.0);
        buttonLabel.setPrefWidth(59.0);
        buttonLabel.setStyle("-fx-border-color: black;");

        // Adding a delete button inside a label
        Button deleteButton = new Button("-");
        deleteButton.setOnAction(event -> handleDeleteButton(newHBox));
        Label deleteButtonLabel = new Label();
        deleteButtonLabel.setGraphic(deleteButton);
        deleteButtonLabel.setAlignment(javafx.geometry.Pos.CENTER);
        deleteButtonLabel.setPrefHeight(41.0);
        deleteButtonLabel.setPrefWidth(59.0);
        deleteButtonLabel.setStyle("-fx-border-color: black;");
        
        // Adding everything to the new HBox
        newHBox.getChildren().addAll(
            buttonLabel,
            wrapInLabel(isbnTf),
            wrapInLabel(bNameTf),
            wrapInLabel(authorTf),
            wrapInLabel(pYearTf),
            wrapInLabel(libraryMenuButton),
            wrapInLabel(roomMenuButton),
            wrapInLabel(statusMenuButton),
            wrapInLabel(datePicker),
            wrapInLabel(bCopyTf),
            deleteButtonLabel
        );

        mainVBox.getChildren().add(newHBox);
//        Book newBook = new Book(newHBox.getChildren());
//        books.add(null);
	}

	private DatePicker createDatePicker() {
        return new DatePicker();
    }

    private Label wrapInLabel(javafx.scene.Node node) {
        Label label = new Label();
        label.setGraphic(node);
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setPrefHeight(41.0);
        label.setPrefWidth(59.0);
        label.setStyle("-fx-border-color: black;");
        return label;
    }

	private TextField createEmptyTextField() {
		// TODO Auto-generated method stub
		TextField textField = new TextField();
		textField.setPrefHeight(22.0);
		textField.setPrefWidth(59.0);
		return textField;
	}
	
	private MenuButton createLibraryMenuButton() {
        MenuButton menuButton = new MenuButton("도서관");
        menuButton.getItems().addAll(
            new MenuItem("남산4동작은도서관"),
            new MenuItem("동구 해안동 작은도서관"),
            new MenuItem("서구청작은도서관")
        );
        return menuButton;
    }
	
	private MenuButton createRoomMenuButton() {
        MenuButton menuButton = new MenuButton("자료실");
        menuButton.getItems().addAll(
            new MenuItem("[남산4동]일반자료실"),
            new MenuItem("[해안동]일반자료실"),
            new MenuItem("[서구청]자료실")
        );
        return menuButton;
    }
	private MenuButton createStatusMenuButton() {
        MenuButton menuButton = new MenuButton("MenuButton");
        menuButton.getItems().addAll(
            new MenuItem("대출불가능"),
            new MenuItem("대출가능"),
            new MenuItem("파손")
        );
        return menuButton;
    }
	
	@FXML
    private void handleSaveButton() {
		System.out.println("save버튼");
        
  
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
	
	private int getRoomId() {
		int roomId = 0;
		String sql = "Select room_id FROM ROOM Where room_name = ?";
		System.out.println("roomIdMenuButton은 "+roomMenuButton.getText());
		try (Connection conn = DatabaseUtil.getConnection();
	        PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, roomMenuButton.getText());
			pstmt.executeQuery();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return roomId;
	}
	
	private int getStatusId() {
		int statusId = 0;
		String sql = "Select status_id FROM BOOKSTATUS WHERE b_Status = ?";
		System.out.println("StatusIdMenuButton은 "+roomMenuButton.getText());
		try (Connection conn = DatabaseUtil.getConnection();
		     PreparedStatement pstmt = conn.prepareStatement(sql)){
				pstmt.setString(1, statusMenuButton.getText());
				pstmt.executeQuery();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		return statusId;
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
    private void handleDeleteButton(HBox hBox) {
        mainVBox.getChildren().remove(hBox);
    }
	
	
}
