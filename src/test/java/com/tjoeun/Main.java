package com.tjoeun;

import java.util.Date;

@FunctionalInterface
interface MyInterface { // 함수형 인터페이스 -> method가 하나
	
	void println(String msg);
//	void printMsg();
}

// 인터페이스를 사용하는 방법 1
// 인터페이스를 구현한 클래스를 정의한다.
class ImplMyinterface implements MyInterface {

	@Override
	public void println(String msg) {
		System.out.println(msg);
		
	}
	
}

public class Main {

	public static void main(String[] args) {
		
		MyInterface mi = new ImplMyinterface();
//		mi.println("Hello");
		
//		인터페이스를 구현한 익명 클래스를 선언하고 사용하는 방법
//		Anonymous Class(이름 없는 클래스 선언/ 인스턴스 생성/사용)
		MyInterface m1 = new MyInterface() {

			@Override
			public void println(String msg) {
				System.out.println(msg);
				
			}
			
		}/* .println("익명 클래스 테스트") */;
//		인터페이스 구현한 클래스 정의, 인스턴스 생성, 메소드 호출
		
//		m1.println("익명 클래스");
		
//		Lambda 표현식으로 함수형 인터페이스를 구현하는 예
		MyInterface mi2 = msg -> System.out.println(msg);
//		mi2.println("람다식으로 표현한 익명 클래스");
		
//		test("테스트",mi2);
		test("테스트",(msg) -> {
			System.out.println(msg);
//			여러개의 문장 정의 가능
			return;
			} );
		
		threadTest();
		
	}
	
	static void test(String msg, MyInterface mi) {
		
		mi.println(msg);
	
	}
	
	static void threadTest() {
		
//		VCPU(Virtual CPU) 에게 아래의 코드를 할당하고 실행하게 한다.
//		VCPU(Thread)에게 아래의 코드를 할당하려면 따라야 할 규칙이 있다.
//		Thread에게 전달할 코드는 Runnable 인터페이스를 구현해야 한다.
		
		class DataPrinter implements Runnable {

			@Override
			public void run() {
				
				while(true) {
					System.out.println(new Date());
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
			
		}
		class NumPrinter implements Runnable {

			@Override
			public void run() {
				
				int num = 0;
				while(true) {
					System.out.println(++num);
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
	
		}
		
//		new DataPrinter().run();
//		new NumPrinter().run();
		
//		DataPrinter code1 = new DataPrinter();
//		NumPrinter code2 = new NumPrinter();
//		
//		Thread t1 = new Thread(code1);
//		Thread t2 = new Thread(code2);
//		
//		t1.start();
//		t2.start();
//		
		
		FITest(new NumPrinter());	// Runnable 구현 클래스의 객체 참조 전달
		
		FITest(new Runnable() {

			@Override
			public void run() {
				while(true) {
					System.out.println(new Date());
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
				
			}
			
		});	// Runnable 을 익명클래스로 구현한 객체 참조
		
		FITest(() -> {
				while(true) {
					System.out.println(new Date());
					try {
						Thread.sleep(1000);
						
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}	
		});	// Runnable 을 Lamda 표현식으로 구현한 객체 참조
		
		
//		while(true) {
//			System.out.println(new Date());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				
//				e.printStackTrace();
//			}
//		}
//		System.out.println("프로그램 종료...");  무한루프
		
//		arr에 저장된 정수의 합이 화면에 표시되도록 함수형 인터페이스
//		를 정의하고 활용해보세요.
		int[] arr = {1,2,3};
		exec(arr, nums -> {
			int total = 0;
			for(int i=0; i<nums.length; i++) {
				total += nums[i];
			}
			return total;
		});
		
	}
	
	static void FITest(Runnable r) {
		
		new Thread(r).start();
	}
	
	@FunctionalInterface
	interface Operation {
		public int process(int[] nums);
	}
	
	static void exec(int[] arr, Operation op) {
		
		int total = op.process(arr);
		System.out.println("total = " + total);
	}
	
	
	
	
	
}
