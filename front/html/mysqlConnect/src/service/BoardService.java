package service;

import java.util.List;

import database.DAO.BoardDAO;
import database.Entity.BoardEntity;
import dto.DeleteBoardDTO;
import dto.InsertBoardDTO;
import dto.UpdateBoardDTO;

// 실제 비즈니스 로직을 실행하는 Layer
public class BoardService {
	
	private BoardDAO boardDao;
	
	public BoardService() {
		this.boardDao = new BoardDAO();
	}
	
	public int postBoard(InsertBoardDTO insertBoardDto) {
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
