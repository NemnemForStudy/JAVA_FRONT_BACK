package chapter08.entity;

// 같은 패키지에 있으면 import할 필요 없음.
public class Developer extends People{
	public String languageSkill;
	public String company;
	String developePosition;
	
	public Developer(String name, String gender, String company){
		// super() : 슈퍼 클래스의 생성자
		super(name, gender);
		this.company = company;
	}
}
