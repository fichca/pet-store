package by.pet.store.resource;

import by.pet.store.entity.Pet;
import by.pet.store.entity.StatusPet;
import by.pet.store.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetResource {

    private final PetService petService;

    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @PostMapping()
    public ResponseEntity<?> addPet(@RequestBody Pet pet) {
        if (petService.add(pet)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        if (petService.updateByIndex(pet.getId(), pet)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> getPetsByStatus(@RequestParam StatusPet status) {
//        StatusPet statusPet = StatusPet.valueOf(status.toUpperCase());
        List<Pet> petByStatus = petService.getPetByStatus(status);
        return new ResponseEntity<>(petByStatus, HttpStatus.OK);
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable int petId) {
        Pet petById = petService.getPetById(petId);
        return new ResponseEntity<>(petById, HttpStatus.CREATED);
    }

    @PostMapping("/{petId}")
    public ResponseEntity<?> updatePet(@PathVariable int petId, @RequestHeader String name, @RequestHeader StatusPet status) {
        petService.updateNameById(petId, name);
        petService.updateStatusById(petId, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<?> deletePet(@PathVariable int petId) {
        if (petService.deletePetById(petId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
