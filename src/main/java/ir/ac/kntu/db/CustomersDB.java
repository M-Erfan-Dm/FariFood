package ir.ac.kntu.db;

import ir.ac.kntu.models.Courier;
import ir.ac.kntu.models.Customer;
import ir.ac.kntu.models.Order;
import ir.ac.kntu.models.OrdersService;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomersDB {
    private Set<Customer> customers;

    public CustomersDB(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Customer> getCustomers() {
        return new HashSet<>(customers);
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public boolean removeCustomer(Customer customer){
        return customers.remove(customer);
    }

    public Customer getCustomerByPhoneNumber(String phoneNumber){
        for (Customer customer : customers){
            if (customer.getPhoneNumber().equals(phoneNumber)){
                return customer;
            }
        }
        return null;
    }

    public Set<Order> getOrdersOfCustomer(String customerPhoneNumber, RestaurantsDB restaurantsDB){
        Customer customer = getCustomerByPhoneNumber(customerPhoneNumber);
        if (customer!=null){
            OrdersService ordersService = new OrdersService(restaurantsDB.getAllOrders());
            return ordersService.getOrdersByCustomer(customer);
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
        CustomersDB that = (CustomersDB) o;
        return customers.equals(that.customers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customers);
    }

    @Override
    public String toString() {
        return "customers=" + customers;
    }
}

