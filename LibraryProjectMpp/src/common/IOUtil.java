package common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class IOUtil {
	private static Scanner sc = new Scanner(System.in);
	private static final String SEPARATOR = "____________________________________________";

	public static void printTitle(String title) {
		printTitle(title, 0);
	}
	
	public static void printTitle(String title, int lineSpaceBeforeTitle) {
		// create space between titles
		for (int i = 0; i < lineSpaceBeforeTitle; i++)
			System.out.println();
		System.out.println(SEPARATOR);
		System.out.println(title);
		System.out.println(SEPARATOR);
	}

	/**
	 * Method to display label and get input string from user in console
	 * 
	 * @param label
	 * @return inputString
	 */
	public static String getInput(String label) {
		System.out.println();
		System.out.print(label + ": ");
		String input = "";
		input = sc.nextLine();
		return input;
	}

	/**
	 * Method to display the options and get valid option input from user
	 * 
	 * @param options
	 * @return input value
	 */
	public static String getSelectedOption(HashMap<String, String> options) {
		if (options.size() == 0)
			return null;
		System.out.println();
		for (Entry<String, String> e : options.entrySet()) {
			System.out.println(String.format("  %s. %s", e.getKey(), e.getValue()));
		}
		System.out.println();
		String selectedOption = null;
		boolean validInput = false;
		while (!validInput) {
			System.out.print("Enter option: ");
			selectedOption = sc.nextLine();
			validInput = options.containsKey(selectedOption);
			if (!validInput) {
				System.out.println("You entered an invalid option. Please try again.");
				System.out.println();
			}
		}
		System.out.println(SEPARATOR);
		return selectedOption;
	}
	
	/**
	 * Method to print exception message when operation fails
	 * 
	 * @param msg
	 */
	public static void printExceptionMessage(String msg) {
		String ansiYellow = "\u001B[33m";
		String ansiReset = "\u001B[0m";
		System.out.println();
		System.out.println("__[Operation failed]________________________");
		System.out.println(ansiYellow + msg + ansiReset);
		System.out.println(SEPARATOR);
	}
}
