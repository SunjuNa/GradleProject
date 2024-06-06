package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // URL 형식은 사용하는 Oracle 설정에 따라 다릅니다.
    private static final String USER = "oraman"; // DB 사용자 이름
    private static final String PASSWORD = "oracle"; // DB 비밀번호

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
