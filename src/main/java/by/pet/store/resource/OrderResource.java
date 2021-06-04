package by.pet.store.resource;


import by.pet.store.entity.Order;
import by.pet.store.entity.StatusOrder;
import by.pet.store.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/store")
public class OrderResource {


    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<Map<StatusOrder, Integer>> getInventory(){
        Map<StatusOrder, Integer> mapPetsByStatus = orderService.getMapPetsByStatus();
        return new ResponseEntity<>(mapPetsByStatus, HttpStatus.CREATED);
    }


    @PostMapping("/order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        orderService.add(order);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getOrderById(@PathVariable int orderId){
        try {
            Order order = orderService.getOrder(orderId);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<?> deleteOrderById(@PathVariable int orderId){
        try {
            orderService.deleteOrderById(orderId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
