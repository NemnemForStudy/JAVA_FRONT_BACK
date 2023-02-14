package board;

// DTO : Data, Transfer, Object
// 데이터 전송 객체
// 서로 다른 레이어 간에 데이터를 송수신할 때 사용되는 객체

// 11. 새로 만들어 주자.
public class UpdateUserDTO {

	private int id;
	private String password;
	private String name;
	private String phoneNumber;

	// 생성자
	// 생성자 명은 클래스 명과 똑같이
	public UpdateUserDTO(int id, String password, String name, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// 접근을 하기위해 getter, setter, 생성자를 사용할 수 있다.
	// getter
	// 파라미터 없고 반환값이 없으므로 int
	public int getId() {
		return this.id;
	}
	
	// 반환값이 있으므로 void
	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}