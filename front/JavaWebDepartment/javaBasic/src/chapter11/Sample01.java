package chapter11;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

// 로또 번호 생성기
public class Sample01 {

	public static void main(String[] args) {
		// 1. 1~45까지 총 6개의 숫자
		// 2. 중복은 허용 안됨
		// 로또 번호를 저장할 배열
		Random random = new Random();
		
		int[] lottoList1 = new int[6];
		
		for(int i = 0; i < lottoList1.length; i++) {
			boolean flag = false;
			// 45까지 지정
			int lotto = random.nextInt(45) + 1;
			
			// 중복 없애기
			for(int j = 0; j < lottoList1.length; j++) {
				if(lottoList1[j] == lotto) {
					flag = true;
					break; // 이 for문 벗어나게 해줌.
				}
			}
			
			// continue를 먹여도 무조건 6번만 돈다.
			// 6번 돌때 마지막 6번일 때는 ++가 아닌 감소를 해줘야 0이 뜨지 않는다.
			if(flag) {
				i--;
				continue;
			}
			
			lottoList1[i] = lotto;	
		}
			
		for(int lotto: lottoList1) System.out.println(lotto);
	
		System.out.println("=========================");
		
		Set<Integer> lottoList2 = new TreeSet<Integer>();
		
		while(lottoList2.size() < 6) {
			lottoList2.add(random.nextInt(45) + 1);
		}
		for(Integer lotto: lottoList2) System.out.println(lotto);
		
	}
}
