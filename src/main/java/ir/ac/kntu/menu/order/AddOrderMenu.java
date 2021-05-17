package ir.ac.kntu.menu.order;

import ir.ac.kntu.db.CustomersDB;
import ir.ac.kntu.db.RestaurantsDB;
import ir.ac.kntu.menu.Menu;
import ir.ac.kntu.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddOrderMenu extends Menu {

    private final CustomersDB customersDB;

    private final RestaurantsDB restaurantsDB;

    private final Settings settings;

    public AddOrderMenu(CustomersDB customersDB, RestaurantsDB restaurantsDB,Settings settings) {
        this.customersDB = customersDB;
        this.restaurantsDB = restaurantsDB;
        this.settings = settings;
    }

    @Override
    public void show() {
        System.out.println("Customer :");
        Customer customer = getCustomer();
        if (customer == null) {
            return;
        }
        System.out.println("Restaurant :");
        Restaurant restaurant = chooseRestaurant();
        if (restaurant == null) {
            return;
        }
        System.out.println("Food :");
        Food food = chooseFood(restaurant);
        if (food == null) {
            return;
        }
        Order order = new Order(food, null, customer, null, OrderState.PROCESSING);
        restaurant.getOrdersService().addOrder(order);
        System.out.println("Your order is in process");
    }

    private Customer getCustomer() {
        String phoneNumber = getPhoneNumber();
        Customer customer = customersDB.getCustomerByPhoneNumber(phoneNumber);
        if (customer == null) {
            System.out.println("Customer not found");
            return null;
        }
        return customer;
    }

    private Restaurant chooseRestaurant() {
        OrdersRestaurantOption.printOptions();
        System.out.println("Enter your choice :");
        OrdersRestaurantOption ordersRestaurantOption = getOption(OrdersRestaurantOption.class);
        if (ordersRestaurantOption == null) {
            return null;
        }
        switch (ordersRestaurantOption) {
            case SHOW_ACTIVE_RESTAURANTS:
                return showActiveRestaurants();
            case SHOW_THREE_BEST_RESTAURANTS:
                return showThreeBestRestaurants();
            case SHOW_RESTAURANTS_BY_NAME:
                return showRestaurantsByName();
            case SHOW_RESTAURANTS_BY_PRICE_TYPE:
                return showRestaurantsByPriceType();
            case SHOW_FIVE_BEST_RESTAURANTS_BY_FOOD:
                return showFiveBestRestaurantsByFood();
        }
        return null;
    }

    private Food chooseFood(Restaurant restaurant) {
        OrdersFoodOption.printOptions();
        System.out.println("Enter your choice :");
        OrdersFoodOption ordersFoodOption = getOption(OrdersFoodOption.class);
        if (ordersFoodOption == null) {
            return null;
        }
        switch (ordersFoodOption) {
            case SHOW_ALL:
                return showAllFoods(restaurant);
            case SHOW_THREE_BEST:
                return showThreeBestFoods(restaurant);
        }
        return null;
    }

    private Restaurant showActiveRestaurants() {
        List<Restaurant> restaurants = new RestaurantsDB(restaurantsDB.getActiveRestaurants()).getOrderedListOfRestaurants(settings);
        restaurantsDB.printRestaurants(restaurants);
        if (restaurants.size() == 0) {
            return null;
        }
        return getRestaurantById();
    }

    private Restaurant showThreeBestRestaurants() {
        List<Restaurant> restaurants = restaurantsDB.getBestRestaurants(3);
        restaurantsDB.printRestaurants(restaurants);
        if (restaurants.size() == 0) {
            return null;
        }
        return getRestaurantById();
    }

    private Restaurant showRestaurantsByName() {
        String name = getName();
        List<Restaurant> foundRestaurantsByName = new RestaurantsDB(restaurantsDB.getRestaurantsByName(name)).getOrderedListOfRestaurants(settings);
        restaurantsDB.printRestaurants(foundRestaurantsByName);
        if (foundRestaurantsByName.size() == 0) {
            return null;
        }
        return getRestaurantById();
    }

    private Restaurant showRestaurantsByPriceType() {
        RestaurantPriceType restaurantPriceType = getRestaurantPriceType();
        if (restaurantPriceType == null) {
            return null;
        }
        List<Restaurant> foundRestaurantsByPriceType = new RestaurantsDB(restaurantsDB.
                getRestaurantsByPriceType(restaurantPriceType)).getOrderedListOfRestaurants(settings);
        restaurantsDB.printRestaurants(foundRestaurantsByPriceType);
        if (foundRestaurantsByPriceType.size() == 0) {
            return null;
        }
        return getRestaurantById();
    }

    private Restaurant showFiveBestRestaurantsByFood() {
        String foodName = getName();
        List<Restaurant> restaurants = restaurantsDB.getBestRestaurantsByFood(foodName, 5);
        restaurantsDB.printRestaurants(restaurants);
        if (restaurants.size() == 0) {
            return null;
        }
        return getRestaurantById();
    }

    private Restaurant getRestaurantById() {
        int id = getId();
        Restaurant restaurant = restaurantsDB.getRestaurantById(id);
        if (restaurant == null) {
            System.out.println("Restaurant not found");
            return null;
        }
        if (!restaurant.isActive()) {
            System.out.println("Restaurant is closed!");
            return null;
        }
        return restaurant;
    }

    private Food showAllFoods(Restaurant restaurant) {
        FoodMenu foodMenu = restaurant.getFoodMenu();
        Set<Food> foods = foodMenu.getFoods();
        foodMenu.printAllFoods();
        if (foods.size() == 0) {
            return null;
        }
        return getFoodByName(restaurant);
    }

    private Food showThreeBestFoods(Restaurant restaurant) {
        List<Food> foods = restaurant.getOrdersService().getBestFoods(3);
        new FoodMenu(new HashSet<>(foods)).printAllFoods();
        if (foods.size() == 0) {
            return null;
        }
        return getFoodByName(restaurant);
    }

    private Food getFoodByName(Restaurant restaurant) {
        String name = getName();
        Food food = restaurant.getFoodMenu().getFoodByName(name);
        if (food == null) {
            System.out.println("Food not found");
            return null;
        }
        return food;
    }
}
