package chapter13;

abstract class SuperClass {
	abstract void superMethod();
}

class SubClass extends SuperClass {

	@Override
	void superMethod() {
		
	}
}

public class Example02 {
	
	// 열거형
	enum EXAMPLE_ENUM {
		FIRST, SECOND, THIRD;
	};
	static final int FIRST = 0;
	static final int SECOND = 1;
	static final int THIRD = 2;

	public static void main(String[] args) {
		System.out.println(EXAMPLE_ENUM.FIRST);

	}

}
