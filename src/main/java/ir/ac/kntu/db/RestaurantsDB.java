package ir.ac.kntu.db;

import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.Restaurant;
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
                restaurant.getSchedule(), restaurant.getPriceType(), restaurant.getCouriers(), restaurant.getOrdersService());

        restaurants.add(newRestaurant);
    }

    public void removeRestaurant(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                restaurants.remove(restaurant);
            }
        }
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
