package chapter08.util;

public class UtilExample01 {
	
	static final int NUMBER = 10;
	
	int add(int a, int b) {
		return a + b;
	}
	
	// 거듭제곱 코드
	int pow(int a, int b) {
		for(int i = 0; i < b; i++) a *= a;
		return a;
	}
}
