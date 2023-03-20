package database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.DatabaseConnector;
import database.Entity.UserEntity;

public class UserDAO {
	
	// DB에서 User 테이블 중 id가 입력받은 값에 해당하는 레코드의 모든 컬럼을 선택 반환
	// SQL : SELECT * FROM User WHERE id = ?;
	// 예상되는 반환 값은 레코드 자체가 반환되기 때문에 0개 or 1개의 레코드가 반환됨(인스턴스)
	
	// 레코드는 DB내에서 테이블 기준으로 생성됨. 자바에서는 Entity 테이블에 해당한다.
	// 그래서 반환되는 값은 Entity이다.
	
	// find~~ : 결과의 인스턴스를 반환하는 메서드
	// exists~~ : 결과의 존재여부를 boolean형태로 반환하는 메서드
	public UserEntity findByid(Integer id) {
		UserEntity result = null;
		
		final String SQL = "SELECT * FROM User WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String password = resultSet.getString(2);
				String name = resultSet.getString(3);
				String phoneNumber = resultSet.getString(4);
				
				result = new UserEntity(id, password, name, phoneNumber);
			}
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(connection != null && !connection.isClosed()) connection.close();
				if(preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
				if(resultSet != null && !resultSet.isClosed()) resultSet.close();
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}
	

}
