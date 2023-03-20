package chapter08;

import chapter08.entity.BusDriver;
import chapter08.entity.Developer;
import chapter08.entity.Police;

import chapter08.entity.*;

public class Example06 {

	public static void main(String[] args) {
		// 인스턴스 생성
		// public은 이 프로젝트 내부 어디에서든 쓸 수 있다는 것.
		// 에러 이유 Police에는 public이 있어서 사용할 수 있는데 다른 것에는 public이 없어서
		// public == 제어자
		Developer developer = new Developer("Nemnem", null, null);
		Police police = new Police();
		BusDriver busDriver = new BusDriver();
		
		// 참조형 변수에서 데이터가 미정인 상태일 때 null
		String str = null;
		// 실제로 가리키고 있는게 null로 없기 때문에 languageSkill을 찾을 수가 없어서 그렇다.
//		developer = null;
		
		developer.languageSkill = "";
		
		System.out.println(developer.name);
		System.out.println(developer.gender);
		System.out.println(developer.company);
//		developer.eatBreakfast(11);
//		police.eatBreakfast();
//		busDriver.eatBreakfast();

	}

}
