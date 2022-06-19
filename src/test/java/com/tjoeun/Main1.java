package com.tjoeun;

import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		
//		arr에 저장된 정수의 합이 화면에 표시되도록 함수형 인터페이스
//		를 정의하고 활용해보세요.
		
		Scanner sc = new Scanner(System.in);
		System.out.print("배열의 갯수를 입력해주세요 : ");
		int a = sc.nextInt();
		int[] arr = new int[a];
		int num = 1;
		for(int i=0; i<arr.length; i++) {
			arr[i] = num++;
		}
//		for(int i=0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
		
		exec(arr,() -> {
			
			int sum = 0;
			for(int i=0; i<arr.length; i++) {
				sum += arr[i];
			}
			System.out.println("배열의 합: "+sum);
		}); 
	

	}
	
	static void exec(int[] arr, Runnable r) {
		
//		int sum = 0;
//		for(int i=0; i<arr.length; i++) {
//			sum = arr[i];
//		}
//		System.out.println(sum);
		new Thread(r).start();
		
	} 

}
