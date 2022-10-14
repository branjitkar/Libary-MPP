package ui;

import common.IOUtil;
import dataaccess.Auth;

public class Main {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("Welcome to Library Management System");
		try {
			// prompt login
			Auth loggedInUserAuth = login();

			// go to main menu
			goToMainMenu(loggedInUserAuth);
		} catch (Exception ex) {
			//log exception here
			IOUtil.printExceptionMessage("Opps! Something went wrong. Please contact system admin. Press enter to continue.");
		}
	}

	private static Auth login() {
		Auth auth = LoginMenu.login();
		return auth;
	}

	private static void goToMainMenu(Auth loggedInUserAuth) {
		SystemMenu sm = new SystemMenu();
		sm.showMenu(loggedInUserAuth);
	}
}
