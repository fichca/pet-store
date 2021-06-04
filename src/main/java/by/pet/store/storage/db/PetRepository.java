package by.pet.store.storage.db;

import by.pet.store.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Integer> {

    boolean existsById(int id);



}
