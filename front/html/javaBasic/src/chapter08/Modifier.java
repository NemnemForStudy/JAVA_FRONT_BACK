package chapter08;

// public : 클래스, 메서드 주로 사용
// private : 멤버 변수로 주로 사용

public class Modifier {
	
	public String name;
	private String address;
	private String telNumber;
	
	// private으로 선언된 멤버변수를 초기화하기 위한 
	// 방법 1 - 생성자 이용
	// 생성자를 이용한 방법은 인스턴스를 생성할 때만 멤버변수를 초기화 할 수 있음
	
	public Modifier(String name, String address, String telNumber) {
		this.name = name;
		this.address = address;
		this.telNumber = telNumber;
	}
	
	// 방법 2 - Setter 메서드 이용
	// private를 사용할 때 무조건 만들어 줘야함.
	// 반환값 존재 X
	public void setName(String name) {
		this.name = name;
	}
	
	// 외부에서 멤버변수에 접근하기 위한 방법
	// Getter 메서드 이용
	// 반환값 존재 O, 파라미터 받지 않음.
	public String getName() {
		return this.name;
	}
}
