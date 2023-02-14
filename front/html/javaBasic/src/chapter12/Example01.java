package chapter12;

// Thread 클래스를 상속받아서 Thread 만드는 방법
class Thread1 extends Thread {
	// run을 Override 해서 작업할 내용을 구현
	public void run() {
		for(int i = 0; i < 20; i++) {
			System.out.print("*");
			
			// Thread.yield()는 자신의 시간을 다음 순번의 Thread에 양보
//			Thread.yield();
			
			// 시간 지연
			for(int j = 0; j < 100000; j++) {}
		}

	}
}

// Runnable 인터페이스 구현해서 Thread 만드는 법
class Thread2 implements Runnable{

	// Runnable 인터페이스의 추상메서드 run() 구현
	@Override
	public void run() {
		for(int i = 0; i < 20; i++) {
			System.out.print("#");
			// 시간 지연
			for(int j = 0; j < 100000; j++) {}
		}
		
	}
	
}


class CountDown extends Thread{
	public void run() {
		// Thread.sleep()는 지정한 시간동안
		// 해당 스레드를 block 상태로 만듦.
		for(int i = 10; i > 0; i --) {
			System.out.println(i + "seconds..");
			try {
				// 해당 스레드를 1초동안 Blocked 상태로 만듦
				// Blocked 상태 : 실행 중이지만 CPU 스케줄러에 선택은 받지 못하는 상태.
				Thread.sleep(1000);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("It's over");
	}
}


public class Example01 {
	public static void main(String[] args) {
		Thread1 thread1 = new Thread1();
		// Runnable 인터페이스를 구현한 클래스의 경우
		// Thread 클래스의 생성자의 인자로 담아서 Thread 인스턴스를 생성한 후 사용 가능.
		Thread2 thread2 = new Thread2();
		Thread thread = new Thread(thread2);
		
		// Thread 클래스의 setPriority 메서드로 우선순위를 지정
		// 우선순위를 지정하지 않으면 기본값으로 5를 지정.
		thread1.setPriority(1);
		thread.setPriority(9);
		
		// 상속 받은 Thread 클래스의 start() 메서드로 해당 Thread 실행
		thread1.start();
		
//		try {
//			// 먼저 작업 수행할 때 사용.
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		
//		for(int i = 0; i < 20; i++) {
//			System.out.print("@");
//			// 시간 지연
//			for(int j = 0; j < 100000; j++) {}
//		}
		
//		CountDown countDown = new CountDown();
//		countDown.start();
		
		
	}
}
