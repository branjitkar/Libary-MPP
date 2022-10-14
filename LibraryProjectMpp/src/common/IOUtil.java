package common;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class IOUtil {
	private static Scanner sc = new Scanner(System.in);
	private static final String SEPARATOR = "______________________________________________________________________";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";

	public static void printTitle(String title) {
		printTitle(title, 0);
	}

	public static void printTitle(String title, int lineSpaceBeforeTitle) {
		// create space between titles
		for (int i = 0; i < lineSpaceBeforeTitle; i++)
			System.out.println();
		System.out.println(ANSI_YELLOW + SEPARATOR);
		System.out.println();
		System.out.println("  " + title);
		System.out.println(SEPARATOR + ANSI_RESET);
	}

	/**
	 * Method to display label and get input string from user in console
	 * 
	 * @param label
	 * @return inputString
	 */
	public static String getInput(String label) {
		String input = "";
		while (input.isBlank()) {
			System.out.println();
			System.out.print(label + ": ");
			input = sc.nextLine();
			if (input.isBlank()) {
				System.out.println();
				System.out.println(ANSI_RED + "Input cannot be blank. Please try again." + ANSI_RESET);
			}
		}
		return input;
	}

	/**
	 * Method to display label and get input string from user in console
	 * 
	 * @param label
	 * @return inputString
	 */
	public static String getNumberInput(String label) {
		while (true) {
			String input = getInput(label);
			if (Util.isInteger(input)) {
				return input;
			}
			System.out.println();
			System.out.println(ANSI_RED + "Invalid Input. Please enter a number." + ANSI_RESET);
		}
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
				System.out.println();
				System.err.println("You entered an invalid option. Please try again.");
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
		System.out.println();
		System.out.println(ANSI_RED + "__[Operation failed]__________________________________________________");
		System.out.println(msg);
		System.out.println(SEPARATOR + ANSI_RESET);
	}

	/**
	 * Method to print message when operation succeeds
	 * 
	 * @param msg
	 */
	public static void printSuccessMessage(String msg) {
		System.out.println();
		System.out.println(ANSI_GREEN + SEPARATOR);
		System.out.println(msg);
		System.out.println(SEPARATOR + ANSI_RESET);
	}

	public static void pauseConsole() {
		sc.nextLine();
	}
}
