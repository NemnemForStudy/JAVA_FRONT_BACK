package service;

import java.util.List;

import database.DAO.BoardDAO;
import database.DAO.UserDAO;
import database.Entity.BoardEntity;
import database.Entity.UserEntity;
import dto.DeleteBoardDTO;
import dto.InsertBoardDTO;
import dto.UpdateBoardDTO;

// 실제 비즈니스 로직을 실행하는 Layer
public class BoardService {
	
	private BoardDAO boardDao;
	private UserDAO userDao;
	
	public BoardService() {
		this.boardDao = new BoardDAO();
		this.userDao = new UserDAO();
	}
	
	public int postBoard(InsertBoardDTO insertBoardDto) {
		
		// findByid 뒤 괄호 자리에는 InsertBoardDTO에 있는 writer를 넣어준다
		UserEntity userEntity = userDao.findByid(insertBoardDto.getBoardWriter());
		// 존재하지 않는다면 return 0을 해준다.
		if(userEntity == null) return 0;
		
		return boardDao.insert(insertBoardDto);
	}
	
	public List<BoardEntity> getBoardList() {
		return boardDao.find();
	}
	
	public int patchBoard(UpdateBoardDTO updateBoardDto) {
		Integer id = updateBoardDto.getId();
		
		BoardEntity boardEntity = boardDao.findByid(id);
		if(boardEntity == null) return -1;
		
		return boardDao.update(updateBoardDto);
	}
	
	public int deleteBoard(DeleteBoardDTO deleteBoardDto) {
		return boardDao.delete(deleteBoardDto);
	}
}
