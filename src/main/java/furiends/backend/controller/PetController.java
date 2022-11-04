package furiends.backend.controller;


import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.service.PetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/pets" )
public class PetController {

    private static final Logger logger = LogManager.getLogger(PetController.class);

    @Autowired
    private PetService petService;

    // list all pets
    @GetMapping("")
    public ResponseEntity<List<Pet>> getAllPets() {
        try {
            return ResponseEntity.ok(petService.findAllPets());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // list all pets within the organization
    @GetMapping("/organization={organizationId}/pets")
    public ResponseEntity<List<Pet>> getAllPetsWithinOrganization(@PathVariable("organizationId") String organizationId) {
        return ResponseEntity.ok(petService.findAllPetsWithinOrganization(organizationId));
    }

    // list all dogs
    @GetMapping("/dogs")
    public ResponseEntity<List<Pet>> getAllDogs() {
        return ResponseEntity.ok(petService.findAllDogs());
    }

    // list all dogs
    @GetMapping("/cats")
    public ResponseEntity<List<Pet>> getAllCats() {
        return ResponseEntity.ok(petService.findAllCats());
    }


    // get pet by id
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(petService.findPetById(id).get());
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // get pets by publish status (in edit or published), ordered by post's last update time
    @GetMapping("/published={isPublished}")
    public ResponseEntity<List<Pet>> getAllPetsByPublishStatus(@PathVariable("isPublished") boolean isPublished) {
        return ResponseEntity.ok(petService.findAllByPublishStatus(isPublished));
    }


    // (by organization) get pets by publish status (in edit or published), ordered by post's last update time
    @GetMapping("/organization={organizationId}/published={isPublished}")
    public ResponseEntity<List<Pet>> getAllPetsByPublishStatusOrg(@PathVariable("organizationId") String organizationId, @PathVariable("isPublished") boolean isPublished) {
        return ResponseEntity.ok(petService.findAllByPublishStatusOrg(organizationId, isPublished));
    }

    // get pets by adoption status, ordered by post's last update time
    @GetMapping("/adopted={isAdopted}")
    public ResponseEntity<List<Pet>> getAllPetsByAdoptionStatus(@PathVariable("isAdopted") boolean isAdopted) {
        return ResponseEntity.ok(petService.findAllByAdoptionStatus(isAdopted));
    }


    // (by organization) get pets by adoption status, ordered by post's last update time
    @GetMapping("/organization={organizationId}/adopted={isAdopted}")
    public ResponseEntity<List<Pet>> getAllPetsByAdoptionStatusOrg(@PathVariable ("organizationId") String organizationId, @PathVariable("isAdopted") boolean isAdopted) {
        return ResponseEntity.ok(petService.findAllByAdoptionStatusOrg(organizationId, isAdopted));
    }


    // create a pet
    @PostMapping("")
    public ResponseEntity createPet(@RequestBody PetRequest petRequest) {
        try {
            return ResponseEntity.ok(petService.createPet(petRequest));
        } catch (Exception e) {
            logger.error((e.toString()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update a pet's info
    @PutMapping({"/{id}"})
    public ResponseEntity updatePet(@RequestBody PetRequest petRequest, @PathVariable("id") String id) {
        try {
            return ResponseEntity.ok(petService.updatePet(petRequest, id));
        } catch (Exception e) {
            logger.error((e.toString()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete a pet
    @DeleteMapping ({"/{id}"})
    public ResponseEntity deletePet(@PathVariable("id") String id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception e) {
            logger.error((e.toString()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
