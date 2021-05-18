package ir.ac.kntu;

import ir.ac.kntu.db.AdminsDB;
import ir.ac.kntu.db.CouriersDB;
import ir.ac.kntu.db.CustomersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.admin.AdminMenu;
import ir.ac.kntu.menu.courier.CouriersMenu;
import ir.ac.kntu.menu.customer.CustomersMenu;
import ir.ac.kntu.menu.main.MainMenu;
import ir.ac.kntu.menu.order.OrdersMenu;
import ir.ac.kntu.menu.restaurant.RestaurantsMenu;
import ir.ac.kntu.menu.settings.SettingsMenu;
import ir.ac.kntu.models.RestaurantsFilteringStrategy;
import ir.ac.kntu.models.Settings;

import java.util.HashSet;

public class FariFood {

    private AdminMenu adminMenu;

    private AdminsDB adminsDB;

    private CouriersDB couriersDB;

    private RestaurantsDB restaurantsDB;

    private CustomersDB customersDB;

    private Settings settings;

    private SettingsMenu settingsMenu;

    private CustomersMenu customersMenu;

    private CouriersMenu couriersMenu;

    private RestaurantsMenu restaurantsMenu;

    private OrdersMenu ordersMenu;

    private MainMenu mainMenu;

    public void start() {
        initialize();
        adminMenu.show();
    }

    private void initialize() {
        adminsDB = new AdminsDB(new HashSet<>());
        couriersDB = new CouriersDB(new HashSet<>());
        restaurantsDB = new RestaurantsDB(new HashSet<>());
        customersDB = new CustomersDB(new HashSet<>());

        settings = new Settings(RestaurantsFilteringStrategy.BY_RATING_DESCENDING);
        settingsMenu = new SettingsMenu(settings);
        customersMenu = new CustomersMenu(customersDB, restaurantsDB);
        couriersMenu = new CouriersMenu(couriersDB, restaurantsDB);
        restaurantsMenu = new RestaurantsMenu(restaurantsDB, settings, couriersDB);
        ordersMenu = new OrdersMenu(restaurantsDB, customersDB, settings);
        mainMenu = new MainMenu(ordersMenu, couriersMenu, restaurantsMenu, customersMenu, settingsMenu);
        adminMenu = new AdminMenu(adminsDB, mainMenu);
    }

    private void generateFakeData() {

    }
}
