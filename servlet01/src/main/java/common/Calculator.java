package common;

public class Calculator {
	public int calculate(int no1, int no2, String operator) {
		int result;
		switch( operator ) {
		case "+":
				result = no1 + no2;	break;
		case "-":
				result = no1 - no2; break;
		case "*":
				result = no1 * no2; break;
		default:
				result = no1 / no2; break;
		}
		return result;
	}
	
}
