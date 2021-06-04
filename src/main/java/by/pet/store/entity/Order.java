package by.pet.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private StatusOrder status;
    private boolean complete;

    public Order(int petId, int quantity, String shipDate, StatusOrder status, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return petId == order.petId && quantity == order.quantity && complete == order.complete && Objects.equals(shipDate, order.shipDate) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, quantity, shipDate, status, complete);
    }
}
