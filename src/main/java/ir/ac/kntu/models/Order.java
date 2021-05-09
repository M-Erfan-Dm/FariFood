package ir.ac.kntu.models;

import java.util.Objects;

public class Order {
    private int id;

    private Food food;

    private Feedback feedback;

    private Customer customer;

    private Courier courier;

    private OrderState orderState;

    public Order(Food food, Feedback feedback, Customer customer,
                 Courier courier, OrderState orderState) {
        this.food = food;
        this.feedback = feedback;
        this.customer = customer;
        this.courier = courier;
        this.orderState = orderState;
    }

    public Order(int id, Food food, Feedback feedback, Customer customer, Courier courier, OrderState orderState) {
        this.id = id;
        this.food = food;
        this.feedback = feedback;
        this.customer = customer;
        this.courier = courier;
        this.orderState = orderState;
    }

    public int getId() {
        return id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String feedBackString;
        if (feedback==null){
            feedBackString = "No Feedback Inserted";
        }else {
            feedBackString = feedback.toString();
        }
        return "id=" + id +
                ", food=" + food +
                ", feedback=" + feedBackString +
                ", customer=" + customer +
                ", courier=" + courier +
                ", orderState=" + orderState;
    }
}
