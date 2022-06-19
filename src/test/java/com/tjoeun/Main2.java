package com.tjoeun;

public class Main2 {

	public static void main(String[] args) {
//		배열{3,5}에 저장된 정수 중에서 최대값을 리턴
		int[] arr = {3,5};
		
		exec(arr, a -> {
			return a[0]>a[1] ? a[0] : a[1];
		});
		
		exec(arr, a -> a[0]>a[1] ? a[0] : a[1]);
		
		exec(arr, nums -> {
			int max = 0;
			if(arr[0]>arr[1]) {
				return max = arr[0];
			} else {
				return max = arr[1];				
			}
		});

	}
	@FunctionalInterface
	interface Operation {
		public int process(int[] nums);
	}
	
	static void exec(int[] arr, Operation op) {
		
		int max = op.process(arr);
		System.out.println("max = " + max);
	}

}
