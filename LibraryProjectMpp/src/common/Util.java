package common;

public class Util {
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
}
