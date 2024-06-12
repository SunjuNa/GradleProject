package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import bean.dto.Librarian;
import bean.dao.BorrowRecordDAO;
import bean.dao.BorrowRecordDAOImpl;
import bean.dto.BorrowRecordDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
	
	@FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    private BorrowRecordDAO borrowRecordDAO;
    
    @FXML
    private LineChart<String, Number> lineChart;

	private Librarian librarian;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borrowRecordDAO = new BorrowRecordDAOImpl();
        xAxis.setTickLabelRotation(45); // X축 레이블 회전
//        xAxis.setTickLabelGap(1); // 레이블 간의 간격을 최소로 설정
//        xAxis.setTickMarkVisible(true); // Tick Mark를 항상 보이도록 설정
//        xAxis.setAutoRanging(false); // Auto Ranging을 비활성화하여 사용자 정의 범위를 설정
//        xAxis.setTickLength(10); // Tick Mark의 길이 설정
//        xAxis.setTickLabelFont(javafx.scene.text.Font.font(10)); // 레이블 폰트 크기 설정
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
            
            updateLineChart(localDate);
            
        } else {
            resultLabel.setText("날짜를 선택하세요.");
        }
    }
    
    
    // 그래프 - ListChart를 이용해서 최근 일주일간 각 반납된 도서 및 대출된 도서의 현황을 일자별로 보여줌
    private void updateLineChart(LocalDate endDate) {
        LocalDate startDate = endDate.minusDays(6);
        Date sqlStartDate = Date.valueOf(startDate);
        Date sqlEndDate = Date.valueOf(endDate);

        List<BorrowRecordDTO> weeklyReturns = borrowRecordDAO.getWeeklyReturns(sqlStartDate, sqlEndDate);
        List<BorrowRecordDTO> weeklyBorrows = borrowRecordDAO.getWeeklyBorrows(sqlStartDate, sqlEndDate);

        XYChart.Series<String, Number> returnSeries = new XYChart.Series<>();
        returnSeries.setName("반납된 도서");

        XYChart.Series<String, Number> borrowSeries = new XYChart.Series<>();
        borrowSeries.setName("대출된 도서");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        
        ObservableList<String> categories = FXCollections.observableArrayList();
        for (int i = 0; i < 7; i++) {
            LocalDate date = startDate.plusDays(i);
            String formattedDate = date.format(formatter);
            categories.add(formattedDate);

            int returnCount = weeklyReturns.stream()
                .filter(record -> record.getDate().toLocalDate().equals(date))
                .mapToInt(BorrowRecordDTO::getCount)
                .findFirst()
                .orElse(0);

            int borrowCount = weeklyBorrows.stream()
                .filter(record -> record.getDate().toLocalDate().equals(date))
                .mapToInt(BorrowRecordDTO::getCount)
                .findFirst()
                .orElse(0);

            returnSeries.getData().add(new XYChart.Data<>(formattedDate, returnCount));
            borrowSeries.getData().add(new XYChart.Data<>(formattedDate, borrowCount));
        }
        xAxis.setCategories(categories); // X축 레이블 설정
        
        lineChart.getData().clear();
        // 카테고리를 만들 xLables 배열 변수 
        ObservableList<XYChart.Series<String, Number>> chartData = FXCollections.observableArrayList(returnSeries, borrowSeries);
        lineChart.setData(chartData);
    }

    @FXML
    void goPage2(ActionEvent event) {
       try {
          String fxmlFile = "root2.fxml";
          System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
          // 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    		Parent secondScene = loader.load();

            Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            RootController2 controller = loader.getController();
            controller.setLibrarian(librarian);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    void goMyPage(ActionEvent event) {
    	try {
    		String fxmlFile = "MyPage.fxml";
    		System.out.println("Loading FXML from: " + getClass().getClassLoader().getResource(fxmlFile));
    		// 파일이 있는지 확인합니다.
            if (getClass().getClassLoader().getResource(fxmlFile) == null) {
                throw new RuntimeException("Cannot find FXML file: " + fxmlFile);
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));
    		Parent secondScene = loader.load();            
    		Scene scene = new Scene(secondScene);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            
            MyPageController controller = loader.getController();
            controller.setLibrarian(librarian);
            controller.initData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setLibrarian(Librarian librarian) {
    	this.librarian = librarian;
    }
}
