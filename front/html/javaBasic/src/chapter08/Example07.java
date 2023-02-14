package chapter08;

// 2. 1을 해결하기 위해 아래 코드 삽입
// import chapter08.util.UtilExample01;

// 3. chapter08.util에 있는 모든 클래스를 사용하고 싶으면
import chapter08.util.*;

// static 변수 혹은 메서드 import, 지금까지는 클래스를 import였다면 지금은 변수와 메서드
import static java.lang.Math.PI;
import static java.lang.Math.*;

import chapter08.util.UtilExample01;

public class Example07 {

	public static void main(String[] args) {
		// 1. 이렇게 쓰면 안됨
		UtilExample01 util = new UtilExample01();
		System.out.println(PI);
		System.out.println(E);

	}

}
