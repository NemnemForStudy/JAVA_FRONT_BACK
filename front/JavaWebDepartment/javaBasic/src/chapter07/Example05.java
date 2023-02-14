package chapter07;

// 생성자 & 오버로딩
// 오버로딩 : 같은 이름의 메서드를 매개변수의 상태를 다르게 해 여러 개를 생성할 수 있도록 함

class Juice {
	String name;
	String company;
	int kcal;
	int volume;
	
	// 생성자
	Juice() {
		name = "coke";
		company = "coca-cola";
		kcal = 288;
		volume = 355;
	}
	
	Juice(String name, String company){
		this.name = name;
		this.company = company;
		this.kcal = 0;
		this.volume = 0;
	}
	
	// 메서드이기 때문에 매개변수를 다르게 하면 두 개 이상 만들 수 있음.
	Juice(String name, String company, int kcal, int volume){
		this(); // Juice 생성자가 호출 됨.
		this.name = name;
		this.company = company;
		this.kcal = kcal;
		this.volume = volume;
	}
	
	void drink(int ml) {
		System.out.println("int drink!");
		this.volume -= ml;
	}
	
	void drink(float ml) {
		System.out.println("float drink!");
		this.volume -= ml;
	}
	
	void drink(int ml1, int ml2) {
		System.out.println("int int drink!");
		this.volume -= (ml1 + ml2);
	}
}

public class Example05 {

	public static void main(String[] args) {
		// 인스턴스 생성
		Juice juice = new Juice();
		
		juice.name = "보성녹차";
		juice.company = "보성";
		juice.kcal = 0;
		juice.volume = 455;		
		
		// 인스턴스에 접근해서 하나씩 실행하면 나중에 대용량일 때 너무 힘들어진다.
		// Juice() <- 이건 메소드
		// Juice로 Juice()라는 메소드를 만듦
		
		Juice cola = new Juice("coke", "coca-cola", 450, 550);
		System.out.println(cola.volume);
		System.out.println(cola.company);
		System.out.println(cola.kcal);
		System.out.println(cola.volume);
				
		juice.drink(100);
		juice.drink(50.5F);
		juice.drink(10, 20);

	}

}
