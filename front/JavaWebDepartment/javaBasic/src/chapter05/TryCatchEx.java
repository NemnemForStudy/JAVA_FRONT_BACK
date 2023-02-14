package chapter05;

public class TryCatchEx {

	public static void main(String[] args) {
		System.out.println("===== 프로그램 시작 =====\n");
		
		try {
			Class clazz = Class.forName("java.lang.String2");
			String[] arr = new String[3];
			arr[0] = "10";
			arr[1] = "20";
			arr[2] = "30";
			arr[3] = "40"; // 오류발생.
			String data1 = null;
			System.out.println(data1.equals(data1));
			
			System.out.println("\n ===== 프로그램 정상 실행 완료 ===== \n");
		} catch(Exception e) {
			System.out.println("클래스가 존재하지 않습니다.");
		}
		System.out.println("\n ===== 프로그램 정상 종료! ===== \n");
	}

}
