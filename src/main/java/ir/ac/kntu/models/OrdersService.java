package ir.ac.kntu.models;

import ir.ac.kntu.utils.IdGenerator;

import java.util.*;

public class OrdersService {
    private Set<Order> orders;

    public OrdersService(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return new HashSet<>(orders);
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        Order newOrder = new Order(IdGenerator.generateNewId(), order.getFood(), order.getFeedback(),
                order.getCustomer(), order.getCourier(), order.getOrderState());
        orders.add(newOrder);
    }

    public boolean removeOrder(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }

    public Set<Order> getOrdersByCustomer(Customer customer) {
        Set<Order> foundOrders = new HashSet<>();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    public Set<Order> getOrdersByCourier(Courier courier) {
        Set<Order> foundOrders = new HashSet<>();
        for (Order order : orders) {
            if (order.getCourier().equals(courier)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    public Set<Order> getOrdersByFood(Food food) {
        Set<Order> foundOrders = new HashSet<>();
        for (Order order : orders) {
            if (order.getFood().equals(food)) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    public Set<Order> getOrdersByState(OrderState orderState) {
        Set<Order> foundOrders = new HashSet<>();
        for (Order order : orders) {
            if (order.getOrderState() == orderState) {
                foundOrders.add(order);
            }
        }
        return foundOrders;
    }

    public ArrayList<Feedback> getAllFeedbacks() {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        for (Order order : orders) {
            Feedback feedback = order.getFeedback();
            if (feedback != null) {
                feedbacks.add(feedback);
            }
        }
        return feedbacks;
    }

    public int getCountOfAllFeedbacks() {
        int count = 0;
        for (Order order : orders) {
            Feedback feedback = order.getFeedback();
            if (feedback != null) {
                count++;
            }
        }
        return count;
    }

    public List<Feedback> getFeedbacksOfFood(Food food) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (Order order : orders) {
            if (order.getFood().equals(food) && order.getFeedback() != null) {
                feedbacks.add(order.getFeedback());
            }
        }
        return feedbacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrdersService that = (OrdersService) o;
        return orders.equals(that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return "orders=" + orders;
    }
}
