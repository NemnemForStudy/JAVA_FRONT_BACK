package chapter05;

public class TryCatchEx3 {

	public static void main(String[] args) {
		try {
			String data1 = args[0];
			String data2 = args[1];
			int value1 = Integer.parseInt(data1);
			int value2 = Integer.parseInt(data2);
			int result = value1 + value2;
			System.out.println(data1 + " + " + data2 + " = " + result);
			
			result = value1 / value2;
			System.out.println(data1 + " / " + data2 + " = " + result);
			
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");
			System.out.println("[실행 방법]");
			System.out.println("java TryCatch3 num1 num2");
		} catch(NumberFormatException e){
			System.out.println("숫자로 변환할 수 없습니다.");
		} catch(ArithmeticException e){
			System.out.println("숫자를 0으로 나눌 수 없습니다.");
		} finally {
			System.out.println("다시 실행하세요");
		}
		System.out.println("\n ===== 프로그램 정상 실행 완료 ===== \n");
	}

}