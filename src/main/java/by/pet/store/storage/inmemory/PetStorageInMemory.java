package by.pet.store.storage.inmemory;

import by.pet.store.entity.Pet;
import by.pet.store.entity.StatusPet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class PetStorageInMemory {

    private List<Pet> pets = new ArrayList<>();

    public void add(Pet pet){
        int size = pets.size();
        pet.setId(++size);
        pets.add(pet);
    }

    public void updateByIndex(int id, Pet newPet){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                pets.remove(pet);
                pets.add(newPet);
                return;
            }
        }
    }

    public void updateNameById(int id, String newName){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                pet.setName(newName);
                return;
            }
        }
    }

    public void updateStatusById(int id, StatusPet statusPet){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                pet.setStatus(statusPet);
                return;
            }
        }
    }
    public List<Pet> getPetByStatus(StatusPet statusPet){
        List<Pet> petsByStatus = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getStatus().equals(statusPet)){
                petsByStatus.add(pet);
            }
        }
        return petsByStatus;
    }
    public Pet getPetById(int id){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                return pet;
            }
        } throw new NoSuchElementException();
    }

    public void deletePetById(int id){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                pets.remove(pet);
                return;
            }
        }
    }
    public boolean contains(int id){
        for (Pet pet : pets) {
            if (pet.getId() == id){
                return true;
            }
        }
        return false;
    }
    public boolean contains(Pet pet){
        for (Pet pet1 : pets) {
            if (pet1.equals(pet)){
                return true;
            }
        }
        return false;
    }
}
