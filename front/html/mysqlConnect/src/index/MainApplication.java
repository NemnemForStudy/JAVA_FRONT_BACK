package index;

import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.UpdatableResultSet;

import database.DAO.BoardDAO;
import database.Entity.BoardEntity;
import dto.DeleteBoardDTO;
import dto.InsertBoardDTO;
import dto.UpdateBoardDTO;
import service.BoardService;

public class MainApplication {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String path = sc.nextLine();
		
		BoardService boardService = new BoardService();
		
		// 입력 받아오고 POST /board면 입력 추가 받고
		if(path.equals("POST /board")) {
			
			// 사용자로부터 입력을 받음
			System.out.println("boardTitle : ");
			String boardTitle = sc.nextLine();
			System.out.println("boardContent : ");
			String boardContent = sc.nextLine();
			System.out.println("boardWriter : ");
			int boardWriter = sc.nextInt();
			
			// 입력을 검증
			if(boardTitle.isBlank() || boardContent.isBlank() || boardWriter < 0) {
				System.out.println("Invalid Input");
			}
			
			// 인스턴스 생성해서
			// Service Layer로 전송할 DTO생성
			InsertBoardDTO insertBoardDTO = new InsertBoardDTO(boardTitle, boardContent, boardWriter);
			
			// 미리 만들어놓은 DAO에 넘겨줌
			// 실제 비즈니스 로직을 실행하기 위해 Service의 메서드를 호출
			int result = boardService.postBoard(insertBoardDTO);
			
			// 비즈니스 로직의 결과에 따라 결과를 출력
			if(result == 1) System.out.println("Insert Success");
			else System.out.println("Insert Failed");
		} 
		
		// find해서 검색해온 애들을 받아서 for-each문으로 출력한 것.
		// 실제 비즈니스 로직을 실행하기 위해 Service 메서드 호출
		else if(path.equals("GET /boardList")) {
			List<BoardEntity> boardList = boardService.getBoardList();
			
			// 비즈니스 결과에 따라 결과를 출력
			for(BoardEntity board: boardList)
				System.out.println(board.toString());
			
		} else if(path.equals("PATCH /board")){
			// 사용자로부터 입력을 받음.
			System.out.println("boardTitle : ");
			String boardTitle = sc.nextLine();
			System.out.println("boardContent : ");
			String boardContent = sc.nextLine();
			System.out.println("id : ");
			Integer id = sc.nextInt();
			
			// 입력 검증
			if(boardTitle.isBlank() || boardContent.isBlank() || id < 0) {
				System.out.println("Invalid Input");
			}
			
			// UPDATE 실행
			// Service Layer로 전송할 DTO생성
			UpdateBoardDTO updateBoardDTO = new UpdateBoardDTO(id, boardTitle, boardContent);
			
			// 실제 비즈니스 로직을 실행하기 위해 Service 메서드 호출
			int result = boardService.patchBoard(updateBoardDTO);
			
			// 비즈니스 결과에 따라 결과를 출력
			if(result == 1) System.out.println("Update Success");
			else if(result == -1) System.out.println("Does Not Exist Board");
			else System.out.println("Update Fail");
			
		} else if(path.equals("DELETE /board")) {
			// 사용자로부터 입력을 받음
			System.out.println("id : ");
			Integer id = sc.nextInt();
			
			// 입력 검증
			if(id < 0) System.out.println("Invalid Input");
			
			// Service Layer로 전송할 DTO생성
			DeleteBoardDTO deleteBoardDTO = new DeleteBoardDTO(id);
			
			// delete라서 update가 아닌 delete
			// 실제 비즈니스 로직을 실행하기 위해 Service 메서드 호출
			int result = boardService.deleteBoard(deleteBoardDTO);
			
			// 비즈니스 결과에 따라 결과를 출력
			if(result == 1) System.out.println("Delete Success");
			else System.out.println("Delete Fail");
		} else {
			System.out.println("404 Not found");
		}
	}
}
