package ir.ac.kntu.menu.courier;

import ir.ac.kntu.db.CouriersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.*;
import ir.ac.kntu.utils.ScannerWrapper;

import java.util.ArrayList;
import java.util.List;

public class CouriersMenu extends Menu {


    private final CouriersDB couriersDB;

    private final RestaurantsDB restaurantsDB;

    public CouriersMenu(CouriersDB couriersDB, RestaurantsDB restaurantsDB) {
        this.couriersDB = couriersDB;
        this.restaurantsDB = restaurantsDB;
    }

    @Override
    public void show() {
        CouriersOption couriersOption = printMenuOptions();
        while (couriersOption != CouriersOption.BACK) {
            if (couriersOption != null) {
                switch (couriersOption) {
                    case REGISTER:
                        registerCourier();
                        break;
                    case UPDATE:
                        updateCourier();
                        break;
                    case FIND_BY_PHONE_NUMBER:
                        findByPhoneNumber();
                        break;
                    case SHOW_ALL:
                        showAll();
                        break;
                    case REMOVE:
                        removeCourier();
                        break;
                }

            }
            couriersOption = printMenuOptions();
        }
    }

    private CouriersOption getOption() {
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        CouriersOption[] couriersOptions = CouriersOption.values();
        if (choice >= 0 && choice < couriersOptions.length) {
            return couriersOptions[choice];
        }
        return null;
    }

    private CouriersOption printMenuOptions() {
        System.out.println("----------Couriers Menu----------");
        System.out.println("\n1.Register new courier\n" +
                "2.Update existing courier\n" +
                "3.Find courier by phone number\n" +
                "4.Show all\n" +
                "5.Remove courier\n" +
                "6.Back\n");

        System.out.print("Enter your choice : ");
        return getOption();
    }

    private void registerCourier() {
        Courier courier = getCourierInfo();
        if (courier == null) {
            return;
        }
        if (couriersDB.containsCourier(courier)) {
            System.out.println("Courier with this phone number already exists!");
            return;
        }
        couriersDB.addCourier(courier);
        System.out.println("Courier is registered");
    }

    private void updateCourier() {
        Courier courier = getCourierInfo();
        if (courier == null) {
            return;
        }
        if (!couriersDB.containsCourier(courier)) {
            System.out.println("Courier not found");
            return;
        }
        couriersDB.addCourier(courier);
        System.out.println("Courier is updated");
    }

    private void findByPhoneNumber() {
        String phoneNumber = getPhoneNumber();
        Courier courier = couriersDB.getCourierByPhoneNumber(phoneNumber);
        if (courier == null) {
            System.out.println("Courier not found");
            return;
        }
        System.out.println("1.General info\n" +
                "2.Feedbacks of Courier\n" +
                "3.Orders history\n");
        System.out.println("Enter your choice :");
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        switch (choice) {
            case 0:
                showGeneralInfoOfCourier(courier);
                break;
            case 1:
                showFeedbacksOfCourier(courier);
                break;
            case 2:
                showOrdersHistoryOfCourier(courier);
                break;
            default:
                System.out.println("Wrong choice!");
                break;
        }

    }


    private void showAll() {
        couriersDB.printAllCouriers();
    }

    private void removeCourier() {
        String phoneNumber = getPhoneNumber();
        Courier courier = couriersDB.getCourierByPhoneNumber(phoneNumber);
        if (courier == null) {
            System.out.println("Courier not found");
            return;
        }
        for (CourierJobInfo courierJobInfo : courier.getJobsInfo()) {
            if (courierJobInfo != null) {
                courierJobInfo.getRestaurant().dismissCourier(phoneNumber);
            }
        }
        couriersDB.removeCourier(courier);
        System.out.println("Courier is removed");
    }

    private Courier getCourierInfo() {
        String phoneNumber = getPhoneNumber();
        String name = getName();
        System.out.println("1.Car\n" +
                "2.Motorcycle\n");
        System.out.println("Enter your vehicle type :");
        int choice = Integer.parseInt(ScannerWrapper.nextLine()) - 1;
        if (choice < 0 || choice >= 2) {
            System.out.println("Wrong Choice!");
            return null;
        }
        VehicleType vehicleType = VehicleType.values()[choice];
        return new Courier(phoneNumber, name, vehicleType);
    }

    private void showGeneralInfoOfCourier(Courier courier) {
        System.out.println("General info of courier : " + courier);
    }

    private void showFeedbacksOfCourier(Courier courier) {
        List<Order> orders = new ArrayList<>(couriersDB.getOrdersOfCourier(courier.getPhoneNumber(), restaurantsDB));
        int count = 0;
        for (int i = 0; i < orders.size(); i++) {
            Feedback feedback = orders.get(i).getFeedback();
            if (feedback != null) {
                count++;
                System.out.println("No." + (i + 1) + " " + feedback);
            }
        }
        System.out.println(count + " feedbacks found");
    }

    private void showOrdersHistoryOfCourier(Courier courier) {
        List<Order> orders = new ArrayList<>(couriersDB.getOrdersOfCourier(courier.getPhoneNumber(), restaurantsDB));
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("No." + (i + 1) + " " + order);
        }
        System.out.println(orders.size() + " orders found");
    }
}
