package ir.ac.kntu.menu.main;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.utils.ScannerWrapper;

public class MainMenu {

    private final Menu menu;

    public MainMenu(Menu menu) {
        this.menu = menu;
    }

    public void show() {
        MainMenuOption mainMenuOption = printMenuOptions();
        while (mainMenuOption != MainMenuOption.BACK) {
            if (mainMenuOption != null) {
                switch (mainMenuOption) {
                    case ORDERS:
                        break;
                    case COURIERS:
                        break;
                    case RESTAURANTS:
                        break;
                    case CUSTOMERS:
                        break;
                    case SETTINGS:
                        break;
                }

            }
            mainMenuOption = printMenuOptions();
        }
    }

    private MainMenuOption getOption() {
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        MainMenuOption[] mainMenuOptions = MainMenuOption.values();
        if (choice >= 0 && choice < mainMenuOptions.length) {
            return mainMenuOptions[choice];
        }
        return null;
    }

    private MainMenuOption printMenuOptions() {
        System.out.println("----------Main Menu----------");
        System.out.println("\n1.Orders\n" +
                "2.Couriers\n" +
                "3.Restaurants\n" +
                "4.Customers\n" +
                "5.Settings\n" +
                "6.Back");

        System.out.print("Enter your choice : ");
        return getOption();
    }

}
