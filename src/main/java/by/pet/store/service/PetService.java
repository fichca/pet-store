package by.pet.store.service;

import by.pet.store.entity.Pet;
import by.pet.store.entity.StatusPet;
import by.pet.store.storage.db.PetRepository;
import by.pet.store.storage.inmemory.PetStorageInMemory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class PetService {

    private final PetStorageInMemory petRepository;


    public boolean add(Pet pet) {
//        petRepository.save(pet);
        return true;
    }

    public boolean updateByIndex(int id, Pet newPet) {
//        if (petRepository.existsById(id)) {
//            petRepository.updateByIndex(id, newPet);
//            return true;
//        }
        return false;
    }

    public void updateNameById(int id, String newName) {
        if (petRepository.contains(id)) {
            petRepository.updateNameById(id, newName);
        }
    }

    public void updateStatusById(int id, StatusPet statusPet) {
        if (petRepository.contains(id)) {
            petRepository.updateStatusById(id, statusPet);
        }
    }

    public List<Pet> getPetByStatus(StatusPet statusPet) {
        return petRepository.getPetByStatus(statusPet);
    }


    public Pet getPetById(int id) {
        if (petRepository.contains(id)) {
            return petRepository.getPetById(id);
        }
        throw new NoSuchElementException();
    }

    public boolean deletePetById(int id) {
        if (petRepository.contains(id)) {
            petRepository.deletePetById(id);
            return true;
        } else {
            return false;
        }
    }
}
