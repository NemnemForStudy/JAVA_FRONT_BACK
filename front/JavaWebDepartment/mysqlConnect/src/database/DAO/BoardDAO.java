package database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseConnector;
import database.Entity.BoardEntity;
import dto.DeleteBoardDTO;
import dto.InsertBoardDTO;
import dto.UpdateBoardDTO;

// DAO : Data Access Object
// DB에 접근해서 데이터 검색 및 삽입 등 DB 작업을 담당하는 클래스
public class BoardDAO {
	
	// DB Board테이블에서 입력받은 id에 해당하는 레코드를 모든 컬럼을 선택해서 반환하는 메서드
	// SQL : SELECT * FROM Board WHERE id = ?;
	// 예상 반환 결과 : 0개 or 1개의 레코드
	// 레코드니까 BoardEntity
	// dto를 만들지 않고 만들어보는 방법
	public BoardEntity findByid(Integer id) {
		
		BoardEntity result = null;
		
		final String SQL = "SELECT * FROM Board WHERE id = ?";
		
		Connection connection = null;
		// ? <- 동적인 SQL이 있으니 Statement가 아닌 preparedStatement
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String boardTitle = resultSet.getString(2);
				String boardContent = resultSet.getString(3);
				String boardDateTime = resultSet.getString(4);
				Integer boardLike = resultSet.getInt(5);
				Integer boardWriter = resultSet.getInt(6);
				
				result = new BoardEntity(id, boardTitle, boardContent, boardDateTime, boardLike, boardWriter);
			}
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(connection != null && !connection.isClosed()) connection.close();
				if(preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
				if(resultSet != null && !resultSet.isClosed()) resultSet.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		
		return result;
	}
	
	// DB의 Board테이블에서 모든 컬럼을 선택해서 반환하는 메서드
	// SQL : SELECT * FROM Board;
	// 예상되는 반환 결과는 0개 이상의 복수 레코드
	public List<BoardEntity> find() {
		List<BoardEntity> result = new ArrayList<BoardEntity>();
		
		// 선언하는 부분이라 위에다가 기입
		final String SQL = "SELECT * FROM Board";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnector.createConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL);
			
			while(resultSet.next()) {
				Integer id = resultSet.getInt(1);
				String boardTitle = resultSet.getString(2);
				String boardContent = resultSet.getString(3);
				String boardDateTime = resultSet.getString(4);
				Integer boardLike = resultSet.getInt(5);
				Integer boardWriter = resultSet.getInt(6);
				
				BoardEntity entity = new BoardEntity(id, boardTitle, boardContent, boardDateTime, boardLike, boardWriter);
				result.add(entity);
			}
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(connection != null && !connection.isClosed()) connection.close();
				if(statement != null && !statement.isClosed()) statement.close();
				if(resultSet != null && !resultSet.isClosed()) resultSet.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}
	
	// DB에서 Board 테이블에 레코드 삽입
	// SQL : INSERT INTO Board (boardTitle, boardContent, boardDateTime, boardWriter) VALUES (?, ?, ?, ?)
	// id, boardLike는 알아서 값이 들어가기 때문에 넣지 않아도 된다.
	// 반환되는 값 : 0 or 1
	public int insert(InsertBoardDTO dto) {
		int result = 0;
		
		final String SQL = "INSERT INTO Board (boardTitle, boardContent, boardDateTime, boardWriter) VALUES(?, ?, ?, ?)";
		
		// 먼저 해야할 일은 DB와 연결을 해주는 것이다.
		Connection connection = null;
		// PreparedStatement - 동적으로 SQL문의 값을 지정할 수 있도록 함
		// 정확하게는 ?때문에 사용.
		PreparedStatement preparedstatement = null;
		
		// DateTime지정. yyyy-MM-dd hh:mm:ss <- 이 형태를 잘 알고 있어야 한다.
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		try {
			connection = DatabaseConnector.createConnection();
			// PreparedStatement 객체 생성. SQL이 담겨있는 prepareStatement 만들어줌
			preparedstatement = connection.prepareStatement(SQL);
			// 이제 각각 값을 지정해서 넣어 줄것임. 첫번째 자리에 어떠한 값을 넣겠다. 첫번째 자리는 boardTitle
			preparedstatement.setString(1, dto.getBoardTitle());
			preparedstatement.setString(2, dto.getBoardContent());
			// Date는 자바 유틸에 있는 것임 sql아님.
			preparedstatement.setString(3, simpleDateFormat.format(new Date()));
			preparedstatement.setInt(4, dto.getBoardWriter());
			
			// 결과가 업데이트 되고 출력이 된다.
			result = preparedstatement.executeUpdate();
			
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(preparedstatement != null && !preparedstatement.isClosed()) preparedstatement.close();
				if(connection != null&& !connection.isClosed()) connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}
	
	// DB에서 Board테이블의 레코드 중 입력받은 id에 해당하는 레코드의 title과 content를 입력받은 값으로 수정
	// SQL : Update Board SET boardTitle = ?, boardContent = ? WHERE id = ?;
	// 예상되는 반환 값 : 0 or 1
	// 주의해야 할 것은 Update는 무조건 0 또는 1이 아님
	public int update(UpdateBoardDTO dto) {
		
		int result = 0;
		
		final String SQL = "UPDATE Board SET boardTitle = ?, boardContent = ? WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DatabaseConnector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, dto.getBoardTitle());
			preparedStatement.setString(2, dto.getBoardContent());
			preparedStatement.setInt(3, dto.getId());
			
			result = preparedStatement.executeUpdate();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()) preparedStatement.close();
				if(connection != null && !connection.isClosed()) connection.close();
		} catch(Exception exception) {
			exception.printStackTrace();
		}

			return result;
		}
	}
	
	// DB에서 Board 테이블 중 입력받은 id에 해당하는 레코드를 삭제
	// SQL : DELETE FROM Board WHERE id = ?;
	// 예상 반환 값 : 0 or 1
	
	public int delete(DeleteBoardDTO dto) {
		
		int result = 0;
		
		final String SQL = "DELETE FROM Board WHERE id = ?";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DatabaseConnector.createConnection();
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, dto.getId());
			
			result = preparedStatement.executeUpdate();
			
		} catch(Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if(connection != null && connection.isClosed()) connection.close();
				if(preparedStatement != null && preparedStatement.isClosed()) preparedStatement.close();
			} catch(Exception exception) {
				exception.printStackTrace();
			}
		}
		
		return result;
	}
}