package controller;

import bean.dao.Book_CopyDAO;
import bean.dto.Book;
import bean.dto.Book_Copy;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class NewWindowController {
	private Book_CopyDAO bookCopyDAO;
	private ObservableList<Book_Copy> bookCopies;
	
	@FXML
	private TextField tfGreatTitle;
	@FXML
	private TextField tfsmallTitle;
	@FXML
	private TextField tfAuthor;
	@FXML
	private TextField tfISBN;
	@FXML
	private TableView<Book_Copy> tableView;
	@FXML
	private TableColumn<Book_Copy, Boolean> checkBoxColumn;
	
	/**
	 * @author 나선주
	 * @param rowData
	 */
	
	public void initData(Book rowData) {
		// TODO Auto-generated method stub
		tfGreatTitle.setText(rowData.getBName());
		tfAuthor.setText(rowData.getAuthor());
		tfsmallTitle.setText(rowData.getBName());
		tfISBN.setText(rowData.getIsbn());
		System.out.println("isbn은 "+ rowData.getIsbn());
//		
//		this.bookCopyDAO.add(rowData);
//		
//		ObservableList<Book_Copy> bookcopys=bookCopyDAO.getBookCopysByISBN(rowData.getIsbn());
//		System.out.println(bookcopys);
		
//		tableView.setItems(bookcopys);
//		checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));
//		checkBoxColumn.setEditable(true);
		
		
	}

	public NewWindowController() {
		super();
	}

}
