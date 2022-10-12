package ui;

import dataaccess.*;

public class LoginMenu {
	public static void login() {
		// TODO: implement this
	}

	public static void main() {
		// login()
		DataAccess dal = new DataAccessFacade();
		User testUser = dal.readUserMap().get("101");
		
		SystemMenu sm = new SystemMenu();
		
		//showing menu for testUser
		sm.showMenu(testUser);
	}
}
