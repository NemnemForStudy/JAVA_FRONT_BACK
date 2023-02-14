package chapter09;

class Animal {
	String ears;
	String eyes;
	String legs;
}

class Bird extends Animal {
	String wings;
}

class Monkey extends Animal {
	String arms;
	
	// 생성자는 값을 초기화 시키고 사용하는 것
	Monkey() {}
	
	Monkey(String arms, String eyes, String ears, String legs) {
		// super : 상위 클래스
		super.ears = ears;
		super.eyes = eyes;
		super.legs = legs;
		// this : 자기 자신
		this.arms = arms;
	}
}

public class Polymorphism {
	public static void main(String[] args) {
		// 얘가 볼 수 있는 것은 ears, eyes, legs 밖에 안된다. wings는 X
		Animal bird = new Bird();
		bird.ears = "ear";
		bird.eyes = "eye";
		bird.legs = "leg";
//		bird.wings = "wing";
		
		// 에러가 뜨므로 강제로 지정해주자
		Bird bird2 = (Bird) bird;
		bird2.wings = "wing";
		
		// 초기화 시킴
		Monkey monkey1 = new Monkey("arm", "eye", "ear", "leg");
		System.out.println(monkey1);
		System.out.println(monkey1.arms);
		System.out.println(monkey1.ears);
		System.out.println(monkey1.eyes);
		System.out.println(monkey1.legs);
		
		Animal animal1 = monkey1;
		System.out.println(animal1);
		System.out.println(animal1.ears);
		System.out.println(animal1.eyes);
		System.out.println(animal1.legs);
		
		// Monkey는 Animal이자 동물이니 true
		System.out.println(animal1 instanceof Monkey);
		System.out.println(animal1 instanceof Animal);
		
		Monkey monkey2 = (Monkey)animal1;
		System.out.println(monkey2);
		System.out.println(monkey2.arms);
		System.out.println(monkey2.ears);
		System.out.println(monkey2.eyes);
		System.out.println(monkey2.legs);
	}
}
