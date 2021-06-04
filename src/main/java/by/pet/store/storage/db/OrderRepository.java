package by.pet.store.storage.db;

import by.pet.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getOrderByStatus(String status);

    Order getOrderById(int id);

    void deleteById(int id);

    boolean existsByPetIdAndQuantityAndShipDate(int petId, int quantity,String shipDate);

}
