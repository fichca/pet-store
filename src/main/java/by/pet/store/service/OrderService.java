package by.pet.store.service;

import by.pet.store.entity.Order;
import by.pet.store.entity.StatusOrder;
import by.pet.store.storage.db.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Map<StatusOrder, Integer> getMapPetsByStatus(){
        StatusOrder[] values = StatusOrder.values();
        Map<StatusOrder, Integer> map = new HashMap<>();

        for (StatusOrder value : values) {
            List<Order> byStatus = orderRepository.getOrderByStatus(value.name());
            map.put(value, byStatus.size());
        }
        return map;
    }

    public void add(Order order){
        if (!orderRepository.existsById(order.getId())){
            orderRepository.save(order);
        }
    }

    public Order getOrder(int id){
       if (orderRepository.existsById(id)){
           return orderRepository.getOrderById(id);
       } throw new NoSuchElementException();
    }

    public void deleteOrderById(int id){
        if (orderRepository.existsById(id)){
            orderRepository.deleteById(id);
        }
    }

}

