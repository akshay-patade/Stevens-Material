package Maze;

public class PairInt {
	
	private int x;
	private int y;
	
	//Using Parameterised constructor to initiate the values
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Returning the value of x i.e. column value
	public int getX() {
		
		return this.x;
	}
	
	//Returning the value of y i.e. row value
	public int getY() {
		
		return this.y;
	}
	
	//Setting the value of x using function i.e. column value
	public void setX(int x) {
		
		this.x = x;
	}
	
	//Setting the value of y using function i.e. row value
	public void setY(int y) {
			
			this.y = y;
	}
	
	//Checking if the two objects are equal or not
	public boolean equals(Object p) {
		
		if(p == null)
			return false;
		
		else {
			
			PairInt temp = (PairInt) p;
			
			if(this.x == temp.x && this.y == temp.y)
					return true;
		}
		
		return false;
	}
	
	// Returning the number in the String format (x,y)
	public String toString() {
		
		String result = "(" + x+ "," +y+ ")";
		return result;
	}
	
	//Making a copy of PairInit Object
	public PairInt copy() {
		
		PairInt temp = new PairInt(this.x,this.y);
		return temp;
		
	}
	


}
