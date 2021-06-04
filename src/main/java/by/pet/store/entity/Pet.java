package by.pet.store.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Category category;
    private String name;

    @OneToMany
    private List<Tag> tags;
    private StatusPet status;

    public Pet(Category category, String name, List<Tag> tags, StatusPet status) {
        this.category = category;
        this.name = name;
        this.tags = tags;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(category, pet.category) && Objects.equals(name, pet.name) && Objects.equals(tags, pet.tags) && status == pet.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, tags, status);
    }
}
