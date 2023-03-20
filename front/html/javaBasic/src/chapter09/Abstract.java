package chapter09;

// 추상 클래스
// 클래스를 추상적으로만 표현해둔 클래스이다.
// abstract 제어자를 포함

abstract class Animal2 {
	String eyes;
	String ears;
	String legs;
	
	// 추상메서드
	// 해당 메서드에 대해서 선언만 해둔 메서드
	abstract void eat();
}

// Animal2를 상속받았는데 에러가 뜬다. 상속했는데 eat을 구현 안해서 에러가 뜸.
class Dog extends Animal2{
	String tail;
	void eat() {
		System.out.println("사료를 먹는다.");
	}
}

public class Abstract {

	public static void main(String[] args) {

		// eat을 Animal2도 가지고 있기 때문에 사용이 가능하다.
		Animal2 dog = new Dog();
		dog.eat();

	}

}
