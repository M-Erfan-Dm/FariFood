package ir.ac.kntu.db;

import ir.ac.kntu.models.Courier;
import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.OrdersService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CouriersDB {
    private Set<Courier> couriers;

    public CouriersDB(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public Set<Courier> getCouriers() {
        return new HashSet<>(couriers);
    }

    public void setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public void addCourier(Courier courier) {
        removeCourier(courier);
        couriers.add(courier);
    }

    public boolean removeCourier(Courier courier){
        return couriers.remove(courier);
    }


    public Courier getCourierByPhoneNumber(String phoneNumber){
        for (Courier courier : couriers){
            if (courier.getPhoneNumber().equals(phoneNumber)){
                return courier;
            }
        }
        return null;
    }

    public Set<Order> getOrdersOfCourier(String courierPhoneNumber,RestaurantsDB restaurantsDB){
        Courier courier = getCourierByPhoneNumber(courierPhoneNumber);
        if (courier!=null){
            OrdersService ordersService = new OrdersService(restaurantsDB.getAllOrders());
            return ordersService.getOrdersByCourier(courier);
        }
        return null;
    }

    public boolean containsCourier(Courier courier){
        return couriers.contains(courier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        CouriersDB that = (CouriersDB) o;
        return couriers.equals(that.couriers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(couriers);
    }

    @Override
    public String toString() {
        return "couriers=" + couriers;
    }
}
