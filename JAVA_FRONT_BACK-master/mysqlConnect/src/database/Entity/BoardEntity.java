package database.Entity;

// Entity는 DB의 테이블과 서로 매핑되는 Java Class임.
// 목적은 DB 테이블의 각각 레코드를 인스턴스로 받기 위한 용도.
// 조건은 DB 테이블의 각 컬렴명, 데이터타입과 Entity 클래스의 필드명, 데이터타입이 일치해야함.
public class BoardEntity {
	private Integer id;
	private String boardTitle;
	private String boardContent;
	private String boardDateTime;
	private Integer boardLike;
	private Integer boardWriter;

	public BoardEntity(Integer id, String boardTitle, String boardContent, String boardDateTime, Integer boardLike,
			Integer boardWriter) {
		super();
		this.id = id;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDateTime = boardDateTime;
		this.boardLike = boardLike;
		this.boardWriter = boardWriter;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getBoardDateTime() {
		return boardDateTime;
	}
	public void setBoardDateTime(String boardDateTime) {
		this.boardDateTime = boardDateTime;
	}
	public Integer getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(Integer boardLike) {
		this.boardLike = boardLike;
	}
	public Integer getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(Integer boardWriter) {
		this.boardWriter = boardWriter;
	}
	@Override
	public String toString() {
		return "BoardEntity [id=" + id + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardDateTime=" + boardDateTime + ", boardLike=" + boardLike + ", boardWriter=" + boardWriter
				+ "]";
	}
	
	
}
