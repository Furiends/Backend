package furiends.backend.controller;

import furiends.backend.model.Application;
import furiends.backend.model.Organization;
import furiends.backend.service.ApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/application")

public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class);
    @Autowired
    private ApplicationService applicationService;

    // list all applicants to one specific organization
    @GetMapping("application/organizationId={organizationId}")
    public ResponseEntity<List<Application>> findAllApplicantsByOrganization(@PathVariable("organizationId") String organizationId) {
        try {
            return ResponseEntity.ok(applicationService.findAllApplicantsByOrganization(organizationId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("application/pet={petId}")
    public ResponseEntity<List<Application>> findApplicationByPetId(@PathVariable("petId") String petId){
        try {
            return ResponseEntity.ok(applicationService.findApplicationByPetId(petId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("application/user={userId}")
    public ResponseEntity<List<Application>> findApplicationByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseEntity.ok(applicationService.findApplicationByUserId(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("application/process={userId}/status = {status}")
    public ResponseEntity<List<Application>>findApplicationWithStatus(@PathVariable("userId") String userId, @PathVariable("status") int status) {
        try {
            return ResponseEntity.ok(applicationService.listApplicationWithStatus(userId, status));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
