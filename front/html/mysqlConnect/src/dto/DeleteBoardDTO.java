package dto;

public class DeleteBoardDTO {

	private int id;

	public DeleteBoardDTO(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "DeleteBoardDTO [id=" + id + "]";
	}
}
