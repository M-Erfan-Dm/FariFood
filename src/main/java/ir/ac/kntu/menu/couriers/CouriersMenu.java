package ir.ac.kntu.menu.couriers;

import ir.ac.kntu.db.CouriersDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.Courier;
import ir.ac.kntu.models.CourierJobInfo;
import ir.ac.kntu.models.VehicleType;
import ir.ac.kntu.utils.ScannerWrapper;

public class CouriersMenu {

    private final Menu menu;

    private final CouriersDB couriersDB;

    public CouriersMenu(Menu menu, CouriersDB couriersDB) {
        this.menu = menu;
        this.couriersDB = couriersDB;
    }

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
        String phoneNumber = menu.getPhoneNumber();
        Courier courier = couriersDB.getCourierByPhoneNumber(phoneNumber);
        if (courier == null) {
            System.out.println("Courier not found");
            return;
        }
        System.out.println("Found courier : " + courier);
    }


    private void showAll() {
        couriersDB.printAllCouriers();
    }

    private void removeCourier() {
        String phoneNumber = menu.getPhoneNumber();
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
        String phoneNumber = menu.getPhoneNumber();
        String name = menu.getName();
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
}
