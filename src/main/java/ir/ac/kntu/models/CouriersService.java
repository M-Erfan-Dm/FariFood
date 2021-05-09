package ir.ac.kntu.models;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CouriersService {

    private Set<Courier> couriers;

    public CouriersService(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public Set<Courier> getCouriers() {
        return new HashSet<>(couriers);
    }

    public void setCouriers(Set<Courier> couriers) {
        this.couriers = couriers;
    }

    public void hireCourier(Courier courier){
        couriers.add(courier);
    }

    public boolean dismissCourier(String courierPhoneNumber){
        for (Courier courier : couriers){
            if (courier.getPhoneNumber().equals(courierPhoneNumber)){
                couriers.remove(courier);
                return true;
            }
        }
        return false;
    }

    public boolean hasHiredCourier(String phoneNumber){
        for (Courier courier : couriers){
            if (courier.getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
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
        CouriersService that = (CouriersService) o;
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
