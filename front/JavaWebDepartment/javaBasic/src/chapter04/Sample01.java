package chapter04;

import java.util.Scanner;

public class Sample01 {

	// 로그인 프로세스를 활용해 변수 및 조건문 사용 활용법
	// 1. 사용자가 아이디, 비밀번호 입력
	// 2. 아이디와 비밀번호가 모두 입력됐는지 검증
	// 2-1. 만약 하나라도 빈값이 있다면 '모두 입력하세요.' 출력 후 프로그램 종료
	// 3. 메모리에 저장된 아이디와 비밀번호를 확인
	// 3-1. 만약 하나라도 틀린다면 '로그인 정보가 일치하지 않습니다.' 출력 후 프로그램 종료
	// 3-2. 모두 맞다면 '로그인에 성공했습니다.' 출력 후 종료
	
	public static void main(String[] args) {
		
		// 3. 메모리에 저장된 아이디와 비밀번호를 확인
		// 저장하고 있는 것이라 바뀌면 안됨. 그래서 상수로 final 붙임.
		final String ID = "Nemnem";
		final String PASSWORD = "p123";
		
		// 1. 사용자가 아이디, 비밀번호 입력.
		Scanner sc = new Scanner(System.in);
		String id;
		String pw;
		System.out.print("아이디를 입력하세요 : ");
		id = sc.nextLine();
		System.out.print("비밀번호를 입력하세요 :");
		pw = sc.nextLine();
		
		System.out.println("id : " + id + ", pw : " + pw);
		
		// 2. 아이디와 비밀번호가 모두 입력됐는지 검증
		// 입력이 되지 않았을 때 문자열은 ""이 들어감.
		// 2-1. 만약 하나라도 빈값이 있다면 '모두 입력하세요.' 출력 후 프로그램 종료
		if(id == "" || pw == "") {
			System.out.println("모두 입력하세요.");
			// main method 종료
			return;
		} else {
			System.out.println("모두 입력되었습니다.");
		}
		
		// 3. 메모리에 저장된 아이디와 비밀번호를 확인
		// 3-2. 만약 하나라도 틀린다면 '로그인 정보가 일치하지 않습니다.' 출력 후 프로그램 종료
		// 3-1. 모두 맞다면 '로그인에 성공했습니다.' 출력 후 종료
		// 3-2. 만약 하나라도 틀린다면 '로그인 정보가 일치하지 않습니다.' 출력 후 프로그램 종료
		if(id.equals(ID) && pw.equals(PASSWORD)) {
			System.out.println("로그인에 성공했습니다.");
			return;
		} else {
			System.out.println("로그인 정보가 일치하지 않습니다.");
			return;
		}
	}

}