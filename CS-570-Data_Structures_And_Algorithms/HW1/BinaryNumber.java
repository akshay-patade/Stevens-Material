package assignment1;

import java.util.Arrays;

/*
 Name: Akshay Pradeep Patade
 Student ID: 20009092
 Using Big Endian Method
 
 */

public class BinaryNumber {

	private int data[]; // Storing the binary number into an integer array object
	private boolean overflow; // Used to set overflow between the addition of two binary numbers
	
	//Creating a binary numbers with all zeros if length is mentioned
	public BinaryNumber(int length) {
		data = new int[length];
	}
	
	//Creating a binary number if String is mentioned
	public BinaryNumber(String str) {
		boolean check;
		check = validate(str);//Checking if the user has inputed only 1s or 0s. If not setting the binary number with all zeros
		if(!check) {
			System.out.println("User has inputted invalid binary number");
		}
		else {
			data = new int[str.length()];
			for(int i = 0; i < str.length(); i++) {
				data[i] = Character.getNumericValue(str.charAt(i));
			}
		}
		
	}
	
	//Returns the length of the binary number
	public int getLength() {
		return data.length;	
	}
	
	//Returns the binary digit present at a particular index
	public int getDigit(int index) {
		if(index > -1 && index < data.length) //checking if the user has inputed valid index or not
			return data[index];
		else
			System.out.println("Array index out of bounds hence returning -1");
		return -1;	
	}
	
	//Sifting the binary digits to the right
	public void shiftR(int amount) {
		int temp[] = data.clone();	//Creating a duplicate of the original data arryay
		data = Arrays.copyOf(data, amount + data.length);
		
		for(int i = 0; i < data.length - amount; i++)
			data[i + amount] = temp[i];
		
		for(int i = 0; i < amount; i++)
			data[i] = 0;
	}
	
	//Addition between two binary Numbers
	public void add(BinaryNumber aBinaryNumber) {
		if(aBinaryNumber.getLength() != this.getLength()) //Checking if the two binary numbers has same length or not
			System.out.println("Length of the two binary numbers does not match");
		else {
			int temp[] = aBinaryNumber.data.clone();
			int carry = 0;
			for(int i = temp.length - 1; i > -1; i--) {
				int sum = temp[i] + carry + data[i];
				if(sum == 0) {
					data[i] = 0;
					carry = 0;
				}
				else if(sum == 1) {
					data[i] = 1;
					carry = 0;
				}
				else if(sum == 2) {
					data[i] = 0;
					carry = 1;
				}
				else {
					data[i] = 1;
					carry = 1;
				}
			}
			if(carry == 1)
				overflow = true;	
		}
	}
	
	//Overriding Object class in java to print the Binary Number.
	public String toString() {
		String result = "";
		if(overflow)
			result += "Overflow";
		else {
			if(data == null) //check if there are any contents in the data array
				result += "No binary number present";
			else {
				for(int x: data)
				result += x+" ";
			}
		}
		return result;
	}
	
	//Converting a Binary Number into decimal
	public int toDecimal() {
		int sum = 0;
		for(int i = data.length - 1; i > -1; i--)
				sum += Math.pow(2, data.length - i - 1) * data[i];
		return sum;
	}
	
	//To clear the overflow 
	public void clearOverflow() {
		overflow = false;
	}
	
	//To Check if the user has inputed valid binary string or not. If not returning false
	private boolean validate(String str) {
		for(int i = 0; i < str.length(); i++) {
			if(Character.getNumericValue(str.charAt(i)) != 1 && Character.getNumericValue(str.charAt(i)) != 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryNumber b1 = new BinaryNumber("11100011001");
		BinaryNumber b2 = new BinaryNumber("10010100111");
		//b2.add(b1);
		b2.shiftR(5);
		System.out.println(b2.toDecimal());
		System.out.println(b2.toString());	
	}
}
