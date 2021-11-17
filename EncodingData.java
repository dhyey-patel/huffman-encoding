/*
 *@author Dhyey Patel
 *
 *The purpose of this class is to store a specific symbol and its binary code in the form of a string
*/

public class EncodingData {
	private char symbol;
	private String encoding;

	public EncodingData(char symbol, String encoding) {
		this.symbol = symbol;
		this.encoding = encoding;
	}

	public char getSymbol() {
		return symbol;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean equals(Object obj) {
		EncodingData other = (EncodingData) obj;
		if (symbol != other.symbol)
			return false;
		return true;
	}

	public String toString() {
		String returnString = String.valueOf(symbol) + " - " + encoding;
		return returnString;
	}

}
