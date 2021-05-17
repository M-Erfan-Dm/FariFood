package ir.ac.kntu.menu.main;

import ir.ac.kntu.db.CustomersDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.menu.courier.CouriersMenu;
import ir.ac.kntu.menu.customer.CustomersMenu;
import ir.ac.kntu.menu.restaurant.RestaurantsMenu;
import ir.ac.kntu.menu.settings.SettingsMenu;
import ir.ac.kntu.models.Settings;
import ir.ac.kntu.utils.ScannerWrapper;

public class MainMenu extends Menu {


    private final CouriersMenu couriersMenu;

    private final RestaurantsMenu restaurantsMenu;

    private final CustomersMenu customersMenu;

    private final SettingsMenu settingsMenu;

    public MainMenu(CouriersMenu couriersMenu,RestaurantsMenu restaurantsMenu,CustomersMenu customersMenu,SettingsMenu settingsMenu) {
        this.couriersMenu = couriersMenu;
        this.restaurantsMenu = restaurantsMenu;
        this.customersMenu = customersMenu;
        this.settingsMenu = settingsMenu;
    }

    @Override
    public void show() {
        MainMenuOption mainMenuOption = printMenuOptions();
        while (mainMenuOption != MainMenuOption.BACK) {
            if (mainMenuOption != null) {
                switch (mainMenuOption) {
                    case ORDERS:
                        break;
                    case COURIERS:
                        couriersMenu.show();
                        break;
                    case RESTAURANTS:
                        restaurantsMenu.show();
                        break;
                    case CUSTOMERS:
                        customersMenu.show();
                        break;
                    case SETTINGS:
                        settingsMenu.show();
                        break;
                }

            }
            mainMenuOption = printMenuOptions();
        }
    }



    private MainMenuOption printMenuOptions() {
        System.out.println("----------Main Menu----------");
        MainMenuOption.printOptions();
        System.out.print("Enter your choice : ");
        return getOption(MainMenuOption.class);
    }

}
