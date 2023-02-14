package chapter01;

public class Example03 {

	public static void main(String[] args) {
		// 산술 연산자
		// 더하기.
		System.out.println(5 + 2);
		// 빼기
		System.out.println(5 - 2);
		// 곱하기
		System.out.println(5 * 2);
		// 나누기
		System.out.println(5 / 2);
		// 나머지
		System.out.println(5 % 2);
		
		// 증가식(++)
		int n1 = 0;
		System.out.println(n1++);
		System.out.println(n1);
		System.out.println(++n1);
		
		// 감소식
		int m1 = 0;
		System.out.println(m1--);
		System.out.println(m1);
		System.out.println(--m1);
		
		// 대입 연산자
		int number = 8;
//		number = number + 3;
		number += 3;
		System.out.println(number);
//		number = number - 3;
		number -= 3;
		System.out.println(number);
//		number = number * 3;
		number *= 3;
		System.out.println(number);
//		number = number / 3;
		number /= 3;
		System.out.println(number);
//		number = number % 3;
		number %= 3;
		System.out.println(number);
		
		// 비교 연산자
		// 같다 (==)
		System.out.println(8 == 3); // false
		System.out.println(5 == 5); // true
		// 다르다 (!=)
		System.out.println(8 != 3); // true
		// 크다 ( > )
		System.out.println(8 > 3); // true
		// 작다 ( < )
		System.out.println(8 < 3); // false
		// 크거나 같다 ( >= )
		System.out.println(8 >= 3); // true
		// 작거나 같다 ( <= )
		System.out.println(8 <= 3); // false
		
		// 논리 연산자
		// and 연산자 (&&) - 곱하기
		// 모두 참이면 참을 반환 false가 존재하면 무조건 false
		System.out.println("======== and 연산자 =======");
		System.out.println(true && true); // true
		System.out.println(false && true); // false
		
		System.out.println("========= or 연산자 =======");
		// or 연산자 (||)
		// 하나라도 참이면 참을 반환
		System.out.println(false || true);
		System.out.println(false || false); 
		
		// 해당 논리 뒤의 연산은 실행하지 않음
		int a = 0;
		int b = 0;
		System.out.println(false && a++ > 0);
		System.out.println(a);
		
		System.out.println("======== not 연산자 =======");
		// not 연산자 (!) true의 반대가 나옴
		System.out.println(!true);
		

	}

}