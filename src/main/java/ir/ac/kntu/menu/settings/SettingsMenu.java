package ir.ac.kntu.menu.settings;

import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.RestaurantsFilteringStrategy;
import ir.ac.kntu.models.Settings;
import ir.ac.kntu.utils.ScannerWrapper;

public class SettingsMenu extends Menu {

    private final Settings settings;

    public SettingsMenu(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void show() {
        SettingsOption settingsOption = printMenuOptions();
        while (settingsOption!=SettingsOption.BACK){
            if (settingsOption!=null){
                switch (settingsOption){
                    case RESTAURANTS_FILTERING:
                        filterRestaurants();
                        break;
                    case SHOW_SETTINGS:
                        showSettings();
                        break;
                }
            }
            settingsOption = printMenuOptions();
        }
    }



    private SettingsOption printMenuOptions() {
        System.out.println("----------Settings Menu----------");
        System.out.println("1.Restaurants filtering\n" +
                "2.Show current settings\n" +
                "3.Back\n");

        System.out.print("Enter your choice : ");
        return getOption(SettingsOption.class);
    }

    private void filterRestaurants(){
        System.out.println("1.Ascending by rating\n" +
                "2.Descending by rating\n" +
                "3.Ascending by feedbacks count\n" +
                "4.Descending by feedbacks count\n" +
                "5.By alpha score\n");

        System.out.println("Enter your choice of filtering restaurants :");
        int choice = Integer.parseInt(ScannerWrapper.nextLine())-1;
        RestaurantsFilteringStrategy[] restaurantsFilteringStrategies = RestaurantsFilteringStrategy.values();
        if (choice<0 || choice >= restaurantsFilteringStrategies.length){
            System.out.println("Wrong choice!");
            return;
        }
        settings.setRestaurantsFilteringStrategy(restaurantsFilteringStrategies[choice]);
    }

    private void showSettings(){
        System.out.println(settings);
    }
}
