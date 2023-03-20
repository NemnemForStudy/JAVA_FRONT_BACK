package chapter03;

public class Example07 {

	public static void main(String[] args) {
		// 제어문 (반복) while
		// 특정한 조건 특정한 논리에만 반복하겠다
		boolean flag = true;
		int number = 0;
		
		// true인 경우에만 실행
		while (flag) {
			System.out.println("Loop" + number);
			
			number += 1;
//			if(number == 10) {
//				flag = false;
//			}
			// 위의 코드를 아래처럼 바꿀 수 있음. 그냥 간결하게 쓰기 위해
			flag = number != 10;
		}

		// 제어문 (반복) do-while
		// 현재 flag는 false
		System.out.println("flag : " + flag);
		
		while (flag) {
			System.out.println("while Loop!");
		}
		
		do {
			System.out.println("Do while Loop!");
		} while (flag);
		
		System.out.println("=============================");
		
		number = 0;
		while (true) {
			if(number % 3 == 0) {
				number++;
				System.out.println("Continue!");
				continue;
			}
			if(number > 10) {
				System.out.println("Break");
				break;
			}
			
			System.out.println("number : " + number++);
		}
		System.out.println("Out of Loop!");
	}

}