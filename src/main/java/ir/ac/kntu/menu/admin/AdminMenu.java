package ir.ac.kntu.menu.admin;

import ir.ac.kntu.db.AdminsDB;
import ir.ac.kntu.menu.main.MainMenu;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.Admin;
import ir.ac.kntu.utils.ScannerWrapper;

public class AdminMenu {

    private final Menu menu;
    private final AdminsDB adminsDB;
    private final MainMenu mainMenu;

    public AdminMenu(Menu menu, AdminsDB adminsDB, MainMenu mainMenu) {
        this.menu = menu;
        this.adminsDB = adminsDB;
        this.mainMenu = mainMenu;
    }

    public void show() {
        System.out.println("Welcome to Fari Food");
        AdminOption adminOption = printMenuOptions();
        while (adminOption != AdminOption.EXIT) {
            if (adminOption!=null) {

                switch (adminOption) {
                    case LOGIN:
                        login();
                        break;
                    case REGISTER:
                        register();
                        break;
                }
            }
            adminOption = printMenuOptions();
        }
        System.exit(0);
    }

    private AdminOption getOption() {
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        AdminOption[] adminOptions = AdminOption.values();
        if (choice >= 0 && choice < adminOptions.length) {
            return adminOptions[choice];
        }
        return null;
    }

    private AdminOption printMenuOptions() {
        System.out.println("----------Admin Menu----------");
        System.out.println("\n1.Login\n" +
                "2.Register\n" +
                "3.Exit\n");

        System.out.print("Enter your choice : ");
        return getOption();
    }

    private void login() {
        System.out.println("---Login---");
        String username = menu.getUsername();
        String password = menu.getPassword();
        Admin admin = new Admin(username, password);
        if (adminsDB.isAdminValid(admin)) {
            mainMenu.show();
        } else {
            System.out.println("Username or password is invalid");
        }
    }

    private void register() {
        System.out.println("---Register---");
        String username = menu.getUsername();
        String password = menu.getPassword();
        Admin admin = new Admin(username, password);
        boolean isAdded = adminsDB.addAdmin(admin);
        if (isAdded) {
            System.out.println("Admin is registered");
        } else {
            System.out.println("Username already exists");
        }
    }

}