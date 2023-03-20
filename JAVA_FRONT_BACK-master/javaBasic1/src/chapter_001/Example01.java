package chapter_001;

public class Example01 {

	public static void main(String[] args) {
		// 변수.
		// 데이터를 저장하는 메모리 공간(변경가능)
		// 데이터타입 변수명;
		int number;
		number = 10;
		System.out.println(number);
		
		char character = 'A';
		System.out.println(character);
		
		// 상수
		// 데이터를 저장하는 메모리 공간(변경 불가)
		// final 데이터타입 상수명; (상수 선언)
		// 상수의 용도 
		// 바꾸지 않을 것인데 왜 이렇게 하나? 
		// 동일한 숫자를 쓰는 곳이 많아질텐데 지정해놓은 상수를 불러서 사용하기 위해 사용
		// 그게 아니라면 모든 값들을 찾아야 하기 때문
		final double PI = 3.14;
//		PI = 11;
		
		final int NUMBER;
		NUMBER = 14;
//		NUMBER = 12;
		
		System.out.println(PI);
		System.out.println(NUMBER);
	}

}
