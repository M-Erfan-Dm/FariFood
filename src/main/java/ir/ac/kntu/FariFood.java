package ir.ac.kntu;

import ir.ac.kntu.db.AdminsDB;
import ir.ac.kntu.db.CouriersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.admin.AdminMenu;
import ir.ac.kntu.menu.couriers.CouriersMenu;
import ir.ac.kntu.menu.main.MainMenu;
import ir.ac.kntu.menu.Menu;

import java.util.HashSet;

public class FariFood {

    public void start(){
        AdminsDB adminsDB = new AdminsDB(new HashSet<>());
        CouriersDB couriersDB = new CouriersDB(new HashSet<>());
        RestaurantsDB restaurantsDB = new RestaurantsDB(new HashSet<>());

        CouriersMenu couriersMenu = new CouriersMenu(couriersDB, restaurantsDB);
        MainMenu mainMenu = new MainMenu(couriersMenu);
        AdminMenu adminMenu = new AdminMenu(adminsDB,mainMenu);

        adminMenu.show();
    }
}
