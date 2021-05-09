package ir.ac.kntu.models;

import java.util.Arrays;
import java.util.Objects;

public class Courier {
    private String phoneNumber;

    private String name;

    private VehicleType vehicleType;

    private CourierWorkInfo[] courierWorksInfo = new CourierWorkInfo[2];

    public Courier(String phoneNumber, String name, VehicleType vehicleType) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.vehicleType = vehicleType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public CourierWorkInfo[] getCourierWorksInfo() {
        return courierWorksInfo.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Courier courier = (Courier) o;
        return phoneNumber.equals(courier.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", vehicleType=" + vehicleType +
                ", courierWorksInfo=" + Arrays.toString(courierWorksInfo);
    }
}
