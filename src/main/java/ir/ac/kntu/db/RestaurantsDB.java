package ir.ac.kntu.db;

import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.Restaurant;
import ir.ac.kntu.utils.IdGenerator;

import java.util.HashSet;
import java.util.Set;

public class RestaurantsDB {
    private Set<Restaurant> restaurants;

    public RestaurantsDB(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public Set<Restaurant> getRestaurants() {
        return new HashSet<>(restaurants);
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant){
        Restaurant newRestaurant = new Restaurant(IdGenerator.generateNewId(), restaurant.getName(),
                restaurant.getAddress(), restaurant.getFoodMenu(),
                restaurant.getSchedule(),restaurant.getPriceType(),restaurant.getCouriers(),restaurant.getOrdersService());

        restaurants.add(newRestaurant);
    }

    public void removeRestaurant(int restaurantId){
        for (Restaurant restaurant : restaurants){
            if (restaurant.getId()==restaurantId){
                restaurants.remove(restaurant);
            }
        }
    }

    public Restaurant getRestaurantById(int restaurantId){
        for (Restaurant restaurant : restaurants){
            if (restaurant.getId()==restaurantId){
                return restaurant;
            }
        }
        return null;
    }

    public Set<Order> getAllOrders(){
        Set<Order> orders = new HashSet<>();
        for (Restaurant restaurant : restaurants){
            orders.addAll(restaurant.getOrdersService().getOrders());
        }
        return orders;
    }

}
