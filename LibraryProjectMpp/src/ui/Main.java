package ui;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class Main {

	public static void main(String[] args) {
		// login()
		DataAccess dal = new DataAccessFacade();
		User testUser = dal.readUserMap().get("101");

		SystemMenu sm = new SystemMenu();

		// showing menu for testUser
		sm.showMenu(testUser);
	}
}
