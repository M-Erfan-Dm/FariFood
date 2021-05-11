package ir.ac.kntu.db;

import ir.ac.kntu.models.*;
import ir.ac.kntu.utils.IdGenerator;

import java.util.*;

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

    public void addRestaurant(Restaurant restaurant) {
        Restaurant newRestaurant = new Restaurant(IdGenerator.generateNewId(), restaurant.getName(),
                restaurant.getAddress(), restaurant.getFoodMenu(),
                restaurant.getSchedule(), restaurant.getPriceType(), restaurant.getHiredCouriers(), restaurant.getOrdersService());

        restaurants.add(newRestaurant);
    }

    public void removeRestaurant(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                restaurants.remove(restaurant);
            }
        }
    }

    public Set<Restaurant> getRestaurantsByName(String name){
        Set<Restaurant> foundRestaurants = new HashSet<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getName().equals(name)){
                foundRestaurants.add(restaurant);
            }
        }
        return foundRestaurants;
    }

    public Set<Restaurant> getRestaurantsByPriceType(RestaurantPriceType priceType){
        Set<Restaurant> foundRestaurants = new HashSet<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.getPriceType()==priceType){
                foundRestaurants.add(restaurant);
            }
        }
        return foundRestaurants;
    }

    public Set<Restaurant> getActiveRestaurants(){
        Set<Restaurant> activeRestaurants = new HashSet<>();
        for (Restaurant restaurant : restaurants){
            if (restaurant.isActive()){
                activeRestaurants.add(restaurant);
            }
        }
        return activeRestaurants;
    }

    public Restaurant getRestaurantById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

    public Set<Order> getAllOrders() {
        Set<Order> orders = new HashSet<>();
        for (Restaurant restaurant : restaurants) {
            orders.addAll(restaurant.getOrdersService().getOrders());
        }
        return orders;
    }

    public List<Feedback> getAllFeedbacksOfFood(Food food){
        List<Feedback> feedbacks = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            feedbacks.addAll(restaurant.getOrdersService().getFeedbacksOfFood(food));
        }
        return feedbacks;
    }

    public List<Restaurant> getBestRestaurants(int count){
        List<Restaurant> orderedRestaurants = getOrderedListOfRestaurantsByRating(false);
        if (orderedRestaurants.size()>count){
            orderedRestaurants =  orderedRestaurants.subList(0,count);
        }
        return orderedRestaurants;
    }

    public List<Food> getBestFoodsOfEachRestaurant(int bestFoodsCountOfEachRestaurant){
        List<Food> bestFoods = new ArrayList<>();
        for (Restaurant restaurant : restaurants){
            bestFoods.addAll(restaurant.getOrdersService().getBestFoods(bestFoodsCountOfEachRestaurant));
        }
        return bestFoods;
    }

    public List<Restaurant> getBestRestaurantsByFood(String foodName , int count){
        List<Restaurant> orderedList = new ArrayList<>(restaurants);
        orderedList.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return compareNumbers(o2.getOrdersService().getRatingAverageOfFood(foodName),
                        o1.getOrdersService().getRatingAverageOfFood(foodName));
            }
        });
        if (orderedList.size()>count){
            orderedList = orderedList.subList(0,count);
        }
        return orderedList;
    }

    public List<Restaurant> getOrderedListOfRestaurantsByRating(boolean isAscending) {
        List<Restaurant> orderedList = new ArrayList<>(restaurants);
        orderedList.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                if (isAscending) {
                    return compareNumbers(o1.getRating(), o2.getRating());
                } else {
                    return compareNumbers(o2.getRating(), o1.getRating());
                }
            }
        });
        return orderedList;
    }

    public List<Restaurant> getOrderedListOfRestaurantsByFeedbacksCount(boolean isAscending) {
        List<Restaurant> orderedList = new ArrayList<>(restaurants);
        orderedList.sort(new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                int size1 = o1.getOrdersService().getCountOfAllFeedbacks();
                int size2 = o2.getOrdersService().getCountOfAllFeedbacks();
                if (isAscending) {
                    return compareNumbers(size1, size2);
                } else {
                    return compareNumbers(size2, size1);
                }
            }
        });
        return orderedList;
    }

    private int compareNumbers(double one, double two) {
        if (one < two) {
            return -1;
        } else if (one > two) {
            return 1;
        }
        return 0;
    }

}
