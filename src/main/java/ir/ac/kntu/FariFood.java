package ir.ac.kntu;

import ir.ac.kntu.db.AdminsDB;
import ir.ac.kntu.menu.admin.AdminMenu;
import ir.ac.kntu.menu.main.MainMenu;
import ir.ac.kntu.menu.Menu;

import java.util.HashSet;

public class FariFood {

    public void start(){
        Menu menu = new Menu();
        AdminsDB adminsDB = new AdminsDB(new HashSet<>());
        MainMenu mainMenu = new MainMenu(menu);
        AdminMenu adminMenu = new AdminMenu(menu,adminsDB,mainMenu);

        adminMenu.show();
    }
}
