package ir.ac.kntu.db;

import ir.ac.kntu.models.Courier;

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

    public void addCourier(Courier courier){
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
