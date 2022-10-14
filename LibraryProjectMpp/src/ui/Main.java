package ui;

import dataaccess.Auth;

public class Main {

	public static void main(String[] args) {
		System.out.println();
		System.out.println("Welcome to Library Management System");

		// prompt login
		Auth loggedInUserAuth = login();

		// go to main menu
		goToMainMenu(loggedInUserAuth);
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
