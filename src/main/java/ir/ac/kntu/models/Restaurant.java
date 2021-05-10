package ir.ac.kntu.models;

import java.util.Objects;

public class Restaurant {

    private int id;

    private String name;

    private String address;

    private FoodMenu foodMenu;

    private Schedule schedule;

    private RestaurantPriceType priceType;

    private double rating = 5;

    private CouriersService couriers;

    private OrdersService ordersService;

    public Restaurant(int id, String name, String address, FoodMenu foodMenu,
                      Schedule schedule, RestaurantPriceType priceType
            , CouriersService couriers, OrdersService ordersService) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodMenu = foodMenu;
        this.schedule = schedule;
        this.priceType = priceType;
        this.couriers = couriers;
        this.ordersService = ordersService;
    }

    public Restaurant(String name, String address, FoodMenu foodMenu,
                      Schedule schedule, RestaurantPriceType priceType
            , CouriersService couriers, OrdersService ordersService) {
        this.name = name;
        this.address = address;
        this.foodMenu = foodMenu;
        this.schedule = schedule;
        this.priceType = priceType;
        this.couriers = couriers;
        this.ordersService = ordersService;
    }

    public int getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FoodMenu getFoodMenu() {
        return foodMenu;
    }

    public void setFoodMenu(FoodMenu foodMenu) {
        this.foodMenu = foodMenu;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public RestaurantPriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(RestaurantPriceType priceType) {
        this.priceType = priceType;
    }

    public CouriersService getCouriers() {
        return couriers;
    }

    public void setCouriers(CouriersService couriers) {
        this.couriers = couriers;
    }

    public OrdersService getOrdersService() {
        return ordersService;
    }

    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Restaurant that = (Restaurant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", schedule=" + schedule +
                ", priceType=" + priceType +
                ", rating=" + rating ;
    }
}
