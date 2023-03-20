package chapter08.entity;

public class People {
	public String name;
	public String gender;
	int age;
	public String address;
	public String email;
	
	People(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}
	
	void eatBreakfast() {
		System.out.println("7시에 아침을 먹습니다.");
	}
}
