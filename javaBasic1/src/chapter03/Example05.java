package chapter03;

import java.util.Scanner;

public class Example05 {

	public static void main(String[] args) {
		// 제어문 (조건) switch
		Scanner sc = new Scanner(System.in);
		System.out.print("숫자를 입력하세요 : ");
		int number = sc.nextInt();
		
		switch(number) {
		case 1:
			System.out.println("1을 더합니다.");
			System.out.println(number += number);
			break;
		case 2:
			System.out.println("2를 곱합니다.");
			System.out.println(number *= 2);
		case 3:
			System.out.println("3을 나눕니다");
			System.out.println(number /= 3);
		default:
			System.out.println("1을 증가 시킵니다.");
			System.out.println(++number);
		}

	}

}

