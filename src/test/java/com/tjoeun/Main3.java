package com.tjoeun;

public class Main3 {

	public static void main(String[] args) {
		
		// 배열 생성
		// 1. 배열변수 선언, 2. 메모리 할당, 3. 원소 초기화, 4. 사용
		int[] nums;
		nums = new int[5];
		nums[0] = 2;
		nums[1] = 3;
		nums[2] = 5;
		System.out.println(nums[0] + nums[2]);
		
		int[] sal = {100, 200, 150};	// 1, 2, 3
		
		int[] price;
//		price = {2000, 1200, 3000};
		price = new int[] {2000, 1200, 3000};
		
		int[] a = new int[3];
		a[0] = 1;
		a[1] = 2;
		a[2] = 3;
		arrTest(a);
		
		int[] b = {1, 2, 3};
		arrTest(b);
		
		int[] c;
		c = new int[] {1, 2, 3};
		arrTest(c);
		
		arrTest(new int[]{1, 2, 3});
		
		
	}
	
	static void arrTest(int[] num) {
		
		int res = 1;
		for(int i=0; i<num.length; i++) {
			res *= num[i];
		}
		System.out.println("누적 곱 = " + res);
		
	}

}
