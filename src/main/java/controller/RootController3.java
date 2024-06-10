package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import bean.dao.BorrowRecordDAO;
import bean.dao.BorrowRecordDAOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
/**
 * @author ps202203
 */
public class RootController3 implements Initializable {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label resultLabel;

    @FXML
    private Button goPage2Button;

    @FXML
    private Label returnCountLabel;

    @FXML
    private Label borrowCountLabel;
    
	@FXML
	private Label popularAuthorLabel;

	@FXML
	private Label popularBookLabel;

    private BorrowRecordDAO borrowRecordDAO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowRecordDAO = new BorrowRecordDAOImpl();
    }

    //DatePicker를 이용해서 선택된 특정 날짜의 반납된 도서, 대출현황, 인기작가, 인기도서 확인
    @FXML
    private void handleCountReturns(ActionEvent event) {
        LocalDate localDate = datePicker.getValue();
        if (localDate != null) {
            Date date = Date.valueOf(localDate);
            int returnCount = borrowRecordDAO.countReturnsByDate(date);
            int borrowCount = borrowRecordDAO.countBorrowsByDate(date);
            String popularAuthor = borrowRecordDAO.getPopularAuthor(date);
            String popularBook = borrowRecordDAO.getPopularBook(date);
            returnCountLabel.setText(String.valueOf(returnCount));
            borrowCountLabel.setText(String.valueOf(borrowCount));
            popularAuthorLabel.setText(popularAuthor);
            popularBookLabel.setText(popularBook);
        } else {
            resultLabel.setText("날짜를 선택하세요.");
        }
    }

    @FXML
    private void goPage2(ActionEvent event) {
        try {
            Parent page2 = FXMLLoader.load(getClass().getResource("/view/root2.fxml"));
            Scene scene = new Scene(page2);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
