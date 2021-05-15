package ir.ac.kntu.menu.customer;

import ir.ac.kntu.db.CustomersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.*;
import ir.ac.kntu.utils.ScannerWrapper;

import java.util.ArrayList;
import java.util.List;

public class CustomersMenu extends Menu {

    private final CustomersDB customersDB;

    private final RestaurantsDB restaurantsDB;

    public CustomersMenu(CustomersDB customersDB, RestaurantsDB restaurantsDB) {
        this.customersDB = customersDB;
        this.restaurantsDB = restaurantsDB;
    }

    @Override
    public void show() {
        CustomersOption customersOption = printMenuOptions();
        while (customersOption != CustomersOption.BACK){
            if (customersOption!=null){
                switch (customersOption){
                    case REGISTER:
                        registerCustomer();
                        break;
                    case UPDATE:
                        updateCustomer();
                        break;
                    case FIND_BY_PHONE_NUMBER:
                        findByPhoneNumber();
                        break;
                    case SHOW_ALL:
                        showAll();
                        break;
                    case REMOVE:
                        removeCustomer();
                        break;
                }
            }
            customersOption = printMenuOptions();
        }

    }

    private CustomersOption getOption() {
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        CustomersOption[] customersOptions = CustomersOption.values();
        if (choice >= 0 && choice < customersOptions.length) {
            return customersOptions[choice];
        }
        return null;
    }

    private CustomersOption printMenuOptions() {
        System.out.println("----------Customers Menu----------");
        System.out.println("\n1.Register new customer\n" +
                "2.Update existing customer\n" +
                "3.Find customer by phone number\n"+
                "4.Show all\n" +
                "5.Remove customer\n" +
                "6.Back\n");

        System.out.print("Enter your choice : ");
        return getOption();
    }

    private void registerCustomer(){
        Customer customer = getCustomerInfo();
        if (customersDB.containsCustomer(customer)){
            System.out.println("Customer with this phone number already exists!");
            return;
        }
        customersDB.addCustomer(customer);
        System.out.println("Customer is registered");
    }

    private void updateCustomer(){
        Customer customer = getCustomerInfo();
        if (!customersDB.containsCustomer(customer)){
            System.out.println("Customer not found");
            return;
        }
        customersDB.addCustomer(customer);
        System.out.println("Customer is updated");
    }

    public void findByPhoneNumber(){
        String phoneNumber = getPhoneNumber();
        Customer customer = customersDB.getCustomerByPhoneNumber(phoneNumber);
        if (customer==null){
            System.out.println("Customer not found");
            return;
        }
        System.out.println("1.General info\n" +
                "2.Feedbacks of Customer\n" +
                "3.Orders history\n");
        System.out.println("Enter your choice :");
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        switch (choice) {
            case 0:
                showGeneralInfoOfCustomer(customer);
                break;
            case 1:
                showFeedbacksOfCustomer(customer);
                break;
            case 2:
                showOrdersHistoryOfCustomer(customer);
                break;
            default:
                System.out.println("Wrong choice!");
                break;
        }
    }

    private void showAll(){
        customersDB.printAllCustomers();
    }

    private void removeCustomer() {
        String phoneNumber = getPhoneNumber();
        Customer customer = new Customer(phoneNumber,"");
        boolean isRemoved = customersDB.removeCustomer(customer);
        if (isRemoved){
            System.out.println("Customer is removed");
        }else {
            System.out.println("Customer not found");
        }
    }

    private void showGeneralInfoOfCustomer(Customer customer){
        System.out.println("General info of customer : " + customer);
    }

    private void showFeedbacksOfCustomer(Customer customer){
        List<Feedback> feedbacks = customersDB.getFeedbacksOfCustomer(customer.getPhoneNumber(),restaurantsDB);
        for (int i = 0;i< feedbacks.size();i++){
            Feedback feedback = feedbacks.get(i);
            System.out.println("No." + (i + 1) + " " + feedback);
        }
        System.out.println(feedbacks.size() + " feedbacks found");
    }

    private void showOrdersHistoryOfCustomer(Customer customer){
        List<Order> orders = new ArrayList<>(customersDB.getOrdersOfCustomer(customer.getPhoneNumber(), restaurantsDB));
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("No." + (i + 1) + " " + order);
        }
        System.out.println(orders.size() + " orders found");
    }

    private Customer getCustomerInfo(){
        String phoneNumber = getPhoneNumber();
        String address = getAddress();
        return new Customer(phoneNumber,address);
    }
}
