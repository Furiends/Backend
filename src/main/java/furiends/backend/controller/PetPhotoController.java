package furiends.backend.controller;

import furiends.backend.dto.PetPhotoResponse;
import furiends.backend.model.PetPhoto;
import furiends.backend.service.PetPhotoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/pet_photo")
public class PetPhotoController {
    private static final Logger logger = LogManager.getLogger(PetPhotoController.class);
    @Autowired
    private PetPhotoService petPhotoService;

    // get a list of all pet ids and their photos
    @GetMapping("")
    public ResponseEntity<List<PetPhoto>> getAllPetPhotos() {
        return ResponseEntity.ok(petPhotoService.findAllPetPhotos());
    }

    // get pet photos for a pet by its pet id
    @GetMapping(value = "/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PetPhotoResponse> getPetPhotosByPetId(@PathVariable("petId") String petId) {
        PetPhotoResponse petPhotoResponse;
        try {
            petPhotoService.createCosClient();
            petPhotoResponse = petPhotoService.findPetPhotosByPetId(petId);
            petPhotoService.shutDownCosClient();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(petPhotoResponse);
    }

    // TODO: find all covers for all pets

    // create pet photos for a pet
    @PostMapping(value = "/{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createPetPhotosByPetId(@PathVariable String petId, @RequestBody List<MultipartFile> petPhotoList) {
        try {
            petPhotoService.createCosClient();
            petPhotoService.createPetPhotos(petId, petPhotoList);
            petPhotoService.shutDownCosClient();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // TODO: update pet photos for a pet

    // delete pet photos for a pet
    @DeleteMapping(value = "/{petId}")
    public ResponseEntity deletePetPhotosByPetId(@PathVariable String petId) {
        try {
            petPhotoService.createCosClient();
            petPhotoService.deletePetPhotos(petId);
            petPhotoService.shutDownCosClient();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
