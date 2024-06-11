package controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import bean.dao.BookDAO;
import bean.dao.BookDAOImpl;
import bean.dao.Book_CopyDAO;
import bean.dao.Book_CopyDAOImpl;
import bean.dao.ItemsDetailDAO;
import bean.dao.ItemsDetailDAOImpl;
import bean.dto.Book_Copy;
import bean.dto.ItemsDetail;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RootController2 implements Initializable{
	private BookDAO bookDAO;
	private ItemsDetailDAO itemsDetailDAO;
	
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
    private MenuItem newWindowMenuItem;
    
    @FXML
    private TreeView<String> treeView;
    
    @FXML
	private TableView<ItemsDetail> tableView;
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
	
    @FXML
    private TextField totalinfotextfield;
    
    @FXML
    private Button goPage3Button;
    
    @FXML
    private TreeItem treeItem;
    
    @FXML
    private Book_CopyDAO book_CopyDAO;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//DAO초기화
		bookDAO = new BookDAOImpl();
		book_CopyDAO = new Book_CopyDAOImpl();
		itemsDetailDAO = new ItemsDetailDAOImpl();
		
		TreeItem<String> rootItem = new TreeItem<>("도서자료");
		rootItem.setExpanded(true);
		
		TreeItem<String> branchItem1  = new TreeItem<>("대출이력");
		TreeItem<String> branchItem2  = new TreeItem<>("단행본자료");
		TreeItem<String> branchItem3  = new TreeItem<>("통계자료");
		
		TreeItem<String> leafItem1 = new TreeItem<>("대출이력_01");
		TreeItem<String> leafItem2 = new TreeItem<>("대출이력_02");
		TreeItem<String> leafItem3 = new TreeItem<>("단행자료_001");
		TreeItem<String> leafItem4 = new TreeItem<>("단행자료_002");
		TreeItem<String> leafItem5 = new TreeItem<>("통계자료_001");
		
		branchItem1.getChildren().addAll(leafItem1, leafItem2);
		branchItem2.getChildren().addAll(leafItem3, leafItem4);
		branchItem3.getChildren().addAll(leafItem5);
		
		rootItem.getChildren().addAll(branchItem1, branchItem2, branchItem3); 
		 // 더블 클릭 이벤트 핸들러 설정
		
		Button button1 = new Button("Action1");
		leafItem1.setGraphic(button1); //setting  the button as the graphic of the TreeItem
		
		treeView.setRoot(rootItem);
		
		button1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String filePath = "C:\\2024_sjNa\\05_협회프로젝트\\1차프로젝트\\데이터\\원래자료\\대구광역시_공공도서관 단행자료 대출 이력_20230906\\대출이력_036_0827_0901.csv";
				File file = new File(filePath);
				if(file.exists()) {
				    try {
						Desktop.getDesktop().open(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
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
     private void handleOpenAddPage(ActionEvent event) {
    	 System.out.println("추가 페이지 버튼이 눌렸습니다");
    	 String fxmlFile = "tableViewTest.fxml";
         System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
         
         // 파일이 있는지 확인합니다.
         if (getClass().getClassLoader().getResource(fxmlFile) == null) {
             throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
         }
         
         try {
             // FXML 파일 로드
             FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
             Parent root = loader.load();

             // 새 창(Stage) 생성
             Stage stage = new Stage();
             stage.setTitle("추가 페이지");
             stage.setScene(new Scene(root));
             stage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     
     @FXML
     private void handleSearch() {
    	 String searchText = searchTextField.getText();
    	 System.out.println("메뉴TextField 값 : "+ searchText);
    	 if(searchText !=null && !searchText.isEmpty()) {
    		 ObservableList<ItemsDetail> itemsDetails;
    		 if(searchType.equals("전체")) {
    			 //둘다 해당
    			 itemsDetails=itemsDetailDAO.getItemsDetailByB_nameOrAuthor(searchText);
    		 }else if(searchType.equals("저자")) {
    			 itemsDetails =itemsDetailDAO.getItemsDetailByAuthor(searchText);
    		 }else {
    			 itemsDetails = itemsDetailDAO.getItemsDetailByB_name(searchText);
    		 }
    		 itemsDetails.forEach(itemsDetail -> System.out.println(itemsDetail.toString())); //받아온 값 출력
    		 totalinfotextfield.setText(String.valueOf(itemsDetails.size())); //total: 건수 표시
			 
			 tableView.setItems(itemsDetails);
//			 checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
//			 checkBoxColumn.setEditable(true);
			 
			 TableColumn actionCol = new TableColumn("Action");
			 tableView.getColumns().add(0, actionCol);
			 actionCol.setStyle("-fx-alignment: CENTER;");
			 actionCol.setCellValueFactory(
					 new PropertyValueFactory<ItemsDetail, String>("checkbox")
					 );
			 bookIDColumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
		     isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		     bNameColumn.setCellValueFactory(new PropertyValueFactory<>("bName"));   
		     authorColumn.setCellValueFactory(new PropertyValueFactory<>("author")); 
		     pYearColumn.setCellValueFactory(new PropertyValueFactory<>("pYear"));  
		     libraryNameColumn.setCellValueFactory(new PropertyValueFactory<>("libraryName"));
		     roomNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomName"));
		     bStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bStatus"));
		     
		     //상세페이지
		     tableView.setOnMouseClicked(event -> {
		    	    if (event.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) {
		    	    	ItemsDetail rowData = tableView.getSelectionModel().getSelectedItem(); // 선택한 행의 데이터 가져오기
		    	        
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
     
     //table의 각행을 더블클릭시 나오는 화면event
     private void openNewWindow(ItemsDetail rowData) {
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
     
     @FXML
     private void deleteSelectedRows(ActionEvent event) {
         ObservableList<ItemsDetail> data = tableView.getItems();
         ObservableList<ItemsDetail> dataListRemove = FXCollections.observableArrayList();
         List<Book_Copy> bookCopys = new ArrayList<>();
         
         System.out.println("삭제버튼이 실행되고 있습니다");
         
         if(data.size()==0) {
             // 입력되었음을 알리는 Alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("정보");
                alert.setHeaderText(null);
                alert.setContentText("삭제할 데이터가 체크되지 않았습니다");
                alert.showAndWait();
         }
         
         for(ItemsDetail bean : data)
         {
            if(bean.getCheckbox().isSelected())
            {
              showConfirmationDialog();
              dataListRemove.add(bean);
              bookCopys.add(new Book_Copy(String.valueOf(bean.getBookID())));
            }
         }
         data.removeAll(dataListRemove);
//         book_CopyDAO.deleteBookCopys(bookCopys);
         
         System.out.println("삭제 버튼이 실행됏습니다");
     }


	private void showConfirmationDialog() {
		// TODO Auto-generated method stub
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("정말 삭제하시겠습니까?");

        ButtonType buttonTypeYes = new ButtonType("Yes");
        ButtonType buttonTypeNo = new ButtonType("No");

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            // User clicked "Yes", proceed with the action
        	 Alert alert2 = new Alert(AlertType.INFORMATION);
             alert2.setTitle("정보");
             alert2.setHeaderText(null);
             alert2.setContentText("삭제되었습니다");
             alert2.showAndWait();
        } else {
            // User clicked "No" or closed the dialog, just close the alert
            alert.close();
        }
	}
     
}
