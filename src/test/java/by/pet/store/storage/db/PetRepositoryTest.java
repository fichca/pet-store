package by.pet.store.storage.db;

import by.pet.store.entity.Category;
import by.pet.store.entity.Pet;
import by.pet.store.entity.StatusPet;
import by.pet.store.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetRepositoryTest {

    @Autowired
    PetRepository petRepository;

    @Test
    void add(){
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(new Tag("test"));
        Pet pet = new Pet( new Category("test"), "test", tags, StatusPet.available );
        petRepository.save(pet);

    }

}