package furiends.backend.controller;

import furiends.backend.dto.OrganizationRequest;
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

    @GetMapping("")
    public ResponseEntity<List<PetPhoto>> getAllPetPhotos() {
        return ResponseEntity.ok(petPhotoService.findAllPetPhotos());
    }

    // get pet photos for a pet by its pet id
    @GetMapping("{petId}")
    public ResponseEntity<PetPhotoResponse> getPetPhotosByPetId(@PathVariable("petId") String petId) {
        PetPhotoResponse petPhotoResponse = null;
        try {
            petPhotoService.createCosClient();
            petPhotoResponse = petPhotoService.findPetPhotosByPetId(petId);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(petPhotoResponse);
    }

    // create pet photos for a pet
    @PostMapping(value = "{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity createPetPhotosByPetId(@PathVariable String petId, @RequestBody List<MultipartFile> petPhotoList) {
        try {
            petPhotoService.createCosClient();
            petPhotoService.createPetPhotos(petId, petPhotoList);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
