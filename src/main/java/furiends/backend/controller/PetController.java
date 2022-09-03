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

    // list all pet within the organization
    @GetMapping("/organization-pets")
    public ResponseEntity<List<Pet>> getAllPetsWithinOrganization(@RequestBody PetRequest petRequest) {
        return ResponseEntity.ok(petService.findAllPetsWithinOrganization(petRequest.getOrganizationId()));
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
    @GetMapping("/by-organization/published={isPublished}")
    public ResponseEntity<List<Pet>> getAllPetsByPublishStatusOrg(@PathVariable("isPublished") boolean isPublished, @RequestBody PetRequest petRequest) {
        return ResponseEntity.ok(petService.findAllByPublishStatusOrg(isPublished, petRequest.getOrganizationId()));
    }

    // get pets by adoption status, ordered by post's last update time
    @GetMapping("/adopted={isAdopted}")
    public ResponseEntity<List<Pet>> getAllPetsByAdoptionStatus(@PathVariable("isAdopted") boolean isAdopted) {
        return ResponseEntity.ok(petService.findAllByAdoptionStatus(isAdopted));
    }


    // (by organization) get pets by adoption status, ordered by post's last update time
    @GetMapping("/by-organization/adopted={isAdopted}")
    public ResponseEntity<List<Pet>> getAllPetsByAdoptionStatusOrg(@PathVariable("isAdopted") boolean isAdopted, @RequestBody PetRequest petRequest) {
        return ResponseEntity.ok(petService.findAllByAdoptionStatusOrg(isAdopted, petRequest.getOrganizationId()));
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
