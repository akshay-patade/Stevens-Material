package assignment2;
/*
 * Name: Akshay Pradeep Patade
 * CWID: 20009092
 */
public class Complexity {
	
	static int operations = 0;//Counter to store the no of operations for method 6

	//Algorithm that runs on O(n^2) Complexity
	public static void method1(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.println("Operation"+counter);
				counter++;
			}
		}
		
	}
	
	//Algorithm that runs on O(n^3) Complexity
	public static void method2(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; k++) {
					System.out.println("Operation"+counter);
					counter++;
				}
			}
		}
	}

	//Algorithm that runs on O(logn) Complexity
	public static void method3(int n) {
		int counter = 0;
		for(int i = 1; i < n; i *= 2) {
			System.out.println("Operation"+counter);
			counter++;
		}
	
	}
	
	//Algorithm that runs on O(nlogn) Complexity
	public static void method4(int n) {
		int counter = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < n; j *= 2) {
				System.out.println("Operation"+counter);
				counter++;
			}
		}
	
	}
	
	//Algorithm that runs on O(loglogn) Complexity
	public static void method5(int n) {
		int counter = 0;
		for(int i = 2; i < n; i *= i) {
			System.out.println("Operation"+counter);
			counter++;
		}
	}
	
	//Algorithm that runs on O(2^n) Complexity
	public static int method6(int n) {
		if(n <= 0) {
			System.out.println("Operation"+operations);
			operations++;
			return operations;
		}
		method6(n - 1);
		method6(n - 1);
		return operations;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Algorithm with the complexity O(n^2) is executed");
		method1(10);
		System.out.println("Algorithm with the complexity O(n^3) is executed");
		method2(10);
		System.out.println("Algorithm with the complexity O(logn) is executed");
		method3(10);
		System.out.println("Algorithm with the complexity O(nlogn) is executed");
		method4(10);
		System.out.println("Algorithm with the complexity O(loglogn) is executed");
		method5(10);
		System.out.println("Algorithm with the complexity O(2^n) is executed");
		method6(10);
	}

}
