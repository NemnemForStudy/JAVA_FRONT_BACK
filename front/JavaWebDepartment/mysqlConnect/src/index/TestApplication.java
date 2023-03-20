package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseConnector;

public class TestApplication {
	// mySQL Connector를 사용할 Connection 객체를 선언
//		private static Connection connection = null;
		// MySQL 쿼리를 작성할 Statement 객체 선언
		private static Statement statement = null;
		// MySQL 쿼리 결과를 담을 ResultSet 객체 선언
		private static ResultSet resultSet = null;
		
		public static void main(String[] args) {
			
		Connection connection = null;
		
		try {
//				1. MySQL connector Driver 로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 2. MySQL Driver로 Connection 객체 생성
			// DB URL, DB 사용자 이름(ID), DB 사용자 패스워드
			final String DB_URL = "jdbc:mysql://127.0.0.1:3306/peed";
			final String USER_NAME = "root";
			final String USER_PASSWORD = "root";
			
			connection = DriverManager.getConnection(DB_URL, USER_NAME, USER_PASSWORD);

			System.out.println("Database Connection Success!");
			
			
			// Connection 객체를 이용해서 Statement 객체 생성
			// 인터페이스여서 new를 사용 X
			statement = connection.createStatement();
			// statement 객체에 사용할 SQL문 작성
			final String SQL = "SELECT * FROM Board";
			// SQL 문을 Statement 객체에 담아서 실행
			// 실행을 하고 실행 결과 반환해줌. 받아줄 객체가 필요하므로 만들어주자(ResultSet)
			// 객체를 resultSet에 저장.
			resultSet = statement.executeQuery(SQL);
			
			// 있을 때 까지 가져오고 없으면 벗어나기
			// next() 다음값이 존재한다면 실행
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String boardTitle = resultSet.getString(2);
				String boardContent = resultSet.getString(3);
				System.out.println("id : " + id + " / " + "boardTitle : " + boardTitle + " / " + "boardContent : " + boardContent);
			}
			
		} catch(Exception exeception) {
			exeception.printStackTrace();
			System.out.println("Database Connection Fail!");
		} finally {
			// 3. 사용한 Connection 객체를 Close
			try {
				if(connection != null && !connection.isClosed())
					connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

	}
}
