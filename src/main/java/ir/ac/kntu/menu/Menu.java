package ir.ac.kntu.menu;

import ir.ac.kntu.utils.ScannerWrapper;

public abstract class Menu {

    public abstract void show();

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
}
