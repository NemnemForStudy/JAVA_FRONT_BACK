package board;

import java.util.Date;

// 데이터베이스 테이블로부터
// 추출해 온 Class는 Entity라고 명명함.

public class BoardEntity {
	// Entity Class는 멤버 변수가 테이블의 필드명과 완전 일치해야함.
	private int boradNumber;
	private String boardTitle;
	private String boardContent;
	private Date boardDate;
	private int boardWriter;
	
	// 위처럼 되어있으면 getter, setter, 생성자가 필요하다
	// source로 만들어주자.
	
	public int getBoradNumber() {
		return boradNumber;
	}
	public void setBoradNumber(int boradNumber) {
		this.boradNumber = boradNumber;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(int boardWriter) {
		this.boardWriter = boardWriter;
	}
	public BoardEntity(int boradNumber, String boardTitle, String boardContent, Date boardDate, int boardWriter) {
		this.boradNumber = boradNumber;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardWriter = boardWriter;
	}
	
	@Override // 재정의
	public String toString() {
		return "BoardEntity [boradNumber=" + boradNumber + ", boardTitle=" + boardTitle + ", boardContent="
				+ boardContent + ", boardDate=" + boardDate + ", boardWriter=" + boardWriter + "]";
	}
	
	
}
