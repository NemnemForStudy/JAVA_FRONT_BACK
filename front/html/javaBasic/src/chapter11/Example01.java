package chapter11;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

class Batch1 extends TimerTask {

	// run을 오버라이드 해서 받아옴.
	@Override
	public void run() {
		System.out.println("Batch1 동작");
		
	}
	
}

class Batch2 extends TimerTask {

	// run을 오버라이드 해서 받아옴.
	@Override
	public void run() {
		System.out.println("Batch2 동작");
		
	}
	
}

public class Example01 {

	public static void main(String[] args) {
		// 시스템 시간
		// 1960년 1월 1일 00시 00분을 0으로 봄.
		long currentTimeMillis = System.currentTimeMillis();
		System.out.println(currentTimeMillis);
		
		// Calendar
		// 날짜 관련된 java.util package
		// 추상 클래스라 아래처럼 못만듦.
//		Calendar calendar = new Calendar();
		
		// 만드려면 getInstance를 쓰던가
		// GregorianCalendar을 써야함.
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = new GregorianCalendar();
		System.out.println(calendar1.toString());
		
		// 연도
		System.out.println(calendar1.get(Calendar.YEAR));
		// 월 / Calendar 클래스는 1월이 0으로 표시가 된다.
		System.out.println(calendar1.get(Calendar.MONTH));
		// 일
		System.out.println(calendar1.get(Calendar.DATE));
		// 요일
		System.out.println(calendar1.get(Calendar.DAY_OF_WEEK));
		
		calendar2.set(2022, 11, 25);
		System.out.println(calendar2);
		
		// 밀리초 단위를 시간, 분, 초
		int millToHours = 32400000 / (60 * 60 * 1000); // 시간, 분, 초
		System.out.println(millToHours);
		
		// java.util package의 Date 클래스
		Date date = new Date();
		
		// java.text Package의 SimpleDateFormat 클래스
		// 괄호안은 어떠한 형태로 초기화 할 것이다.
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd. MM. yyyy. HH:mm");
		
		System.out.println(sdf1.format(date));
		System.out.println(sdf2.format(date));
		
		// Date 클래스와 Calendar 클래스의 단점 보완한
		// java.time package의 클래스
		LocalDate localDate = LocalDate.now();
		System.out.println(localDate.toString());
		
		LocalTime localTime = LocalTime.now();
		System.out.println(localTime.toString());
		
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime.toString());
		
		LocalDateTime localDateTimeOf = LocalDateTime.of(2022, 12, 25, 8, 20);
		System.out.println(localDateTimeOf.toString());
		
		System.out.println(localDateTime.getMonth());
		System.out.println(localDateTime.getDayOfWeek());
		
		// minusYears(10)가 뱉어내는 값이 인스턴스면 plusMonths(5)사용이 가능.
		localDateTime = localDateTime.minusYears(10).plusMonths(5);
		System.out.println(localDateTime);
		
		// TimerTask
		Batch1 batch1 = new Batch1();
		Batch2 batch2 = new Batch2();
		
		Timer timer = new Timer(true);
		
		// 읽고 종료되지 않고 아래것들을 실행해야하는데 끝나버렸다.
		timer.schedule(batch1, 5000);
		timer.schedule(batch2, 3000);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// Decimal Text Format
		DecimalFormat decimalFormat = new DecimalFormat("$##,###.00");
		System.out.println(decimalFormat.format(50000));
		
	}

}
