package common;

import java.util.List;

public class Util {
	public static final int SPACENUM = 30;
	/**
	 * @param input
	 * @return true if input string is integer.
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static int getSpaceCount(String s) {
		int charLength = s.length();
		return Math.abs(SPACENUM - charLength);
		
	}
	
	public static String getPaddedString(List<String>listOfString) {
		StringBuilder strBldr = new StringBuilder();
		strBldr.append("| ");
		for(String s: listOfString) {
			strBldr.append(s);
			int count = getSpaceCount(s);
			 for (int i = 1; i < count; i++) {
				 strBldr.append(" ");
			 }
			strBldr.append("| ");
		}
		strBldr.append("\n"+getRowDividerLine());
		return strBldr.toString();
	}
	
	public static String getRowDividerLine() {
		StringBuilder strbldr = new StringBuilder();
		 for (int i = 1; i < SPACENUM * 5 + 6; i++) {
			 strbldr.append("-");
		 }
		 return strbldr.toString();
	}
	
	public static void printRow(List<String>row) {
		String sa = Util.getPaddedString(row);
		System.out.println(sa);
	}
	
}
