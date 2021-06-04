package by.pet.store.storage.inmemory;

import by.pet.store.entity.Order;
import by.pet.store.entity.StatusOrder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrderStorageInMemory {
    private List<Order> orders = new ArrayList<>();

    public void add(Order order){
        int size = orders.size();
        order.setId(size);
        orders.add(order);
    }

    public List<Order> getByStatus(StatusOrder status){
        List<Order> orders1 = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus().equals(status)){
                orders1.add(order);
            }
        }
        return orders1;
    }

    public Order getOrder(int id){
        for (Order order : orders) {
            if (order.getId() == id){
                return order;
            }
        } throw new NoSuchElementException();
    }

    public void deleteOrderById(int id){
        for (Order order : orders) {
            if (order.getId() == id){
                orders.remove(order);
                return;
            }
        }
    }
    public boolean contains(Order order){
        for (Order order1 : orders) {
            if (order.equals(order1)){
                return true;
            }
        }
        return false;
    }
    public boolean contains(int id){
        for (Order order1 : orders) {
            if (order1.getId() == id){
                return true;
            }
        }
        return false;
    }
}
