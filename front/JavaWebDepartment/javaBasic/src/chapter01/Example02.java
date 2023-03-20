package chapter01;

public class Example02 {

	public static void main(String[] args) {
		// 배열.
		// 변수가 나열된 형태
		int[] numbers;
		numbers = new int[10];
		System.out.println(numbers);
		
		char[] characters;
		characters = new char[10];
		System.out.println(characters);
		
		// 배열 초기화
		int[] arr = new int[] {1, 2, 3};
		System.out.println(arr);
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		System.out.println(arr.length);
		
		char[] string = {'h', 'e', 'l', 'l', 'o'};
		System.out.println(string);
		System.out.println(string.length);
		string[0] = 'k';
		System.out.println(string);
		
		// 다차원 배열
		// 배열의 요소가 배열의 형태
		int[][] arr2 = {
				{1, 2, 3},
				{4, 5, 6}
		};
		
		System.out.println(arr2[0][0]);
		
		// 문자열
		// 단어 혹은 문장을 쉽게 표기하기 위한 Java object class
		String str1 = new String(); // 초기화 및 생성
		str1 = "apple";
		System.out.println(str1);
		
		String str2 = "banana";
		System.out.println(str2);
		
	}

}