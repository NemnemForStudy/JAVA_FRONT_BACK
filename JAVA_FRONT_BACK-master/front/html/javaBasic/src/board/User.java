package board;


// 이렇게 만들면 불편한 문제가 있음.
// 인스턴스 생성할 때 하나하나씩 다 기입해줘야함.
// 그래서 생성자를 만들어서 최대한 간결하게
public class User {
	
	// 아이디
	// 이 아이디로 User를 구분지을 수 있음.
	private int id;
	// 비밀번호
	private String password;
	// 이름
	private String name;
	// 전화번호
	private String phoneNumber;
	
	// 생성자
	// 어디서든 쓰게 public
	// 클래스와 이름이 같아야하고 반환타입도 없고 지정도 X

	public User(int id, String password, String name, String phoneNumber) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
	// private을 다른 클래스에서 사용할 수 있게 해주는 Getter, Setter메서드
	// 받아올 것은 없으니 id를 return해주자
	public int getId() {
		return this.id;
	}
	
	// setter 기능은 해당 인스턴스있는 멤버변수의 값을 넣어줌.
	// 반환해줄 값이 없다 -> void
	// 외부에서 값을 받아와서 그 값을 넣어줘야하니 ()안에 넣어주자
	// 파라미터 id를 들고와야함.
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

	// 10번 들어가기 전
	// generate로 toString에서 field만 채워주자
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
	}
}
