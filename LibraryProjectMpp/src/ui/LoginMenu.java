package ui;

import common.IOUtil;
import controller.SystemController;
import dataaccess.*;

public class LoginMenu {
	public static Auth login() {
		IOUtil.printTitle("Login", 1);
		Auth auth = null;
		while (auth == null) {
			String username = IOUtil.getInput("Enter username");
			String password = IOUtil.getInput("Enter password");
			auth = SystemController.login(username, password);
			if (auth == null) {
				IOUtil.printExceptionMessage("Invalid username or password. Try again.");
			}
		}
		return auth;
	}

}
