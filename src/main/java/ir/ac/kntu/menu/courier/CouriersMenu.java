package ir.ac.kntu.menu.courier;

import ir.ac.kntu.db.CouriersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.*;

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
                    default:
                        break;
                }

            }
            couriersOption = printMenuOptions();
        }
    }


    private CouriersOption printMenuOptions() {
        System.out.println("----------Couriers Menu----------");
        CouriersOption.printOptions();
        System.out.print("Enter your choice : ");
        return getOption(CouriersOption.class);
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
        CourierInfoOption.printOptions();
        System.out.println("Enter your choice :");
        CourierInfoOption courierInfoOption = getOption(CourierInfoOption.class);
        if (courierInfoOption == null) {
            return;
        }
        switch (courierInfoOption) {
            case GENERAL:
                showGeneralInfoOfCourier(courier);
                break;
            case FEEDBACKS:
                showFeedbacksOfCourier(courier);
                break;
            case ORDERS_HISTORY:
                showOrdersHistoryOfCourier(courier);
                break;
            default:
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
        VehicleType.printOptions();
        System.out.println("Enter your vehicle type :");
        VehicleType vehicleType = getOption(VehicleType.class);
        if (vehicleType == null) {
            return null;
        }
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
