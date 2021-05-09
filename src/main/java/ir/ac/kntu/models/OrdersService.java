package ir.ac.kntu.models;

import ir.ac.kntu.utils.IdGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    public void addOrder(Order order){
        Order newOrder = new Order(IdGenerator.generateNewId(),order.getFood(),order.getFeedback(),
                order.getCustomer(),order.getCourier(),order.getOrderState());
        orders.add(newOrder);
    }

    public boolean removeOrder(int orderId){
        for (Order order : orders){
            if (order.getId()==orderId){
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    public Order getOrderById(int orderId){
        for (Order order : orders){
            if (order.getId()==orderId){
                return order;
            }
        }
        return null;
    }

    public Order getOrderByCustomer(Customer customer){
        for (Order order : orders){
            if (order.getCustomer().equals(customer)){
                return order;
            }
        }
        return null;
    }

    public Order getOrderByCourier(Courier courier){
        for (Order order : orders){
            if (order.getCourier().equals(courier)){
                return order;
            }
        }
        return null;
    }

    public Order getOrderByFood(Food food){
        for (Order order : orders){
            if (order.getFood().equals(food)){
                return order;
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
