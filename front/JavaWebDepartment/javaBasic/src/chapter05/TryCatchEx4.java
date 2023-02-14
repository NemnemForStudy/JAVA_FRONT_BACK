package chapter05;

public class TryCatchEx4 {

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
		} // 다중 catch문 사용 시 Exception을 가장 먼저 사용하면 Exception이
		// 최상위 예외 클래스 이므로 모든 예외를 처리할 수 있기 때문에 아래에 입력한
		// 다른 예외를 처리할 수 없음.
		  catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("실행 매개값의 수가 부족합니다.");
			System.out.println("[실행 방법]");
			System.out.println("java TryCatch3 num1 num2");
		} catch(NumberFormatException e){
			System.out.println("숫자로 변환할 수 없습니다.");
		} catch(ArithmeticException e) {
			System.out.println("숫자를 0으로 나눌 수 없습니다.");
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다.");
		} finally {
			System.out.println("다시 실행하세요");
		}
		System.out.println("\n ===== 프로그램 정상 실행 완료 ===== \n");
	}

}