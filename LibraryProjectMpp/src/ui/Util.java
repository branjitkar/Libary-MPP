package ui;

import java.util.Scanner;

public class Util {
	/**
	 * method to display label and get input from user in console
	 * 
	 * @param label
	 * @return inputString
	 */
	public static String getInput(String label) {
		System.out.print(label + ": ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		sc.close();
		System.out.println();
		return input;
	}
}
