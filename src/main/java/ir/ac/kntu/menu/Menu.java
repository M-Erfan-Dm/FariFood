package ir.ac.kntu.menu;

import ir.ac.kntu.utils.ScannerWrapper;

public class Menu {

    public String getUsername(){
        System.out.println("Enter your username : ");
        return ScannerWrapper.nextLine();
    }

    public String getPassword(){
        System.out.println("Enter your password : ");
        return ScannerWrapper.nextLine();
    }
}
