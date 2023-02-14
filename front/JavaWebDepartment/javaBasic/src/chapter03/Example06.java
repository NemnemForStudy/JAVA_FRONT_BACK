package chapter03;

public class Example06 {

	public static void main(String[] args) {
		// 제어문 (반복문) for
		// i가 첫번째에 없어도 됨. 필수가 아님.
		for(int i = 0; i < 10; i++) {
			System.out.println(i);
		}
	
		int[] numbers = new int[5];
		for(int i = 0; i < numbers.length; i++) {
			numbers[i] = i + 1;
		}
		
		System.out.println("=============================");
		
		for(int x = 0; x <= 4; x++) {
			System.out.println(numbers[x]);
		}
		
		System.out.println("=============================");
		
		for(int number : numbers) {
			System.out.println(number);
		}
		
//		System.out.println(numbers[0]);
//		System.out.println(numbers[1]);
//		System.out.println(numbers[2]);
//		System.out.println(numbers[3]);
//		System.out.println(numbers[4]);
	}

}