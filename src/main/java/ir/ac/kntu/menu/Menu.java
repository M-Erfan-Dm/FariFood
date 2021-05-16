package ir.ac.kntu.menu;

import ir.ac.kntu.menu.settings.SettingsOption;
import ir.ac.kntu.utils.ScannerWrapper;

public abstract class Menu {

    public abstract void show();

    public <T extends Enum<T>> T getOption(Class<T> tEnum){
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        T[] options = tEnum.getEnumConstants();
        if (choice >= 0 && choice < options.length) {
            return options[choice];
        }
        return null;
    }

    public String getUsername(){
        System.out.println("Enter your username : ");
        return ScannerWrapper.nextLine();
    }

    public String getPassword(){
        System.out.println("Enter your password : ");
        return ScannerWrapper.nextLine();
    }

    public String getPhoneNumber(){
        System.out.println("Enter your phone number : ");
        return ScannerWrapper.nextLine();
    }

    public String getName(){
        System.out.println("Enter name : ");
        return ScannerWrapper.nextLine();
    }

    public String getAddress(){
        System.out.println("Enter address : ");
        return ScannerWrapper.nextLine();
    }
}
