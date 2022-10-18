package furiends.backend.controller;

import furiends.backend.dto.AdoptionProcedure;
import furiends.backend.dto.AdoptionProcedureStep;
import furiends.backend.dto.OrganizationBenefits;
import furiends.backend.dto.OrganizationRequest;
import furiends.backend.model.Organization;
import furiends.backend.service.OrganizationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/organizations")
public class OrganizationController {
    private static final Logger logger = LogManager.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService organizationService;

    // get organization by id
    @GetMapping("{id}")
    public ResponseEntity<Organization> getOrganization(@PathVariable("id") String id) {
        Optional<Organization> organization = null;
        try {
            organization = organizationService.findOrganizationById(id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(organization.get());
    }

    // list all organizations
    @GetMapping("")
    public ResponseEntity<List<Organization>> getAllOrganization() {
        return ResponseEntity.ok(organizationService.findAllOrganizations());
    }

    // create an organization
    @PostMapping("")
    public ResponseEntity createOrganization(@RequestBody OrganizationRequest organizationRequest) {
        try {
            organizationService.createOrganization(organizationRequest);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // update an organization's info
    @PutMapping("{id}")
    public ResponseEntity updateOrganization(@RequestBody OrganizationRequest organizationRequest, @PathVariable String id) {
        try {
            organizationService.updateOrganization(organizationRequest, id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // delete an organization
    @DeleteMapping("{id}")
    public ResponseEntity deleteOrganization(@PathVariable String id) {
        try {
            organizationService.deleteOrganization(id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    // register an organization by WeChat
    @PostMapping("orgRegisterByWechat/{code}")
    public ResponseEntity orgRegisterByWechat(@RequestBody OrganizationRequest organizationRequest, @PathVariable String code) {
        try {
            organizationService.registerOrganizationByWechat(organizationRequest);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //login an organization by WeChat
    @PostMapping("orgLoginByWechat/{code}")
    public ResponseEntity<Organization> orgLoginByWechat(@PathVariable String code) {
        Organization organization;
        try {
            organization = organizationService.loginOrganizationByWechat(code);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(organization);
    }

    // get adoption procedure by organization id
    @GetMapping("{id}/adoptionProcedure")
    public ResponseEntity<List<AdoptionProcedureStep>> getOrganizationAdoptionProcedure(@PathVariable("id") String id) {
        List<AdoptionProcedureStep> adoptionProcedureStepList;
        try {
            adoptionProcedureStepList = organizationService.listAdoptionProcedureSteps(id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(adoptionProcedureStepList);
    }

    // update all adoption procedure by organization id (create and delete operations can also use this API to update)
    @PostMapping("{id}/adoptionProcedure")
    public ResponseEntity updateOrganizationAdoptionProcedure(@RequestBody AdoptionProcedure adoptionProcedureRequest, @PathVariable("id") String id) {
        try {
            organizationService.updateOrganizationAdoptionProcedure(adoptionProcedureRequest, id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("verifyInvitationCode/{code}")
    public ResponseEntity verifyInvitationCode(@PathVariable("code") String code) {
        boolean flag = organizationService.verifyInvitationCode(code);
        if (!flag) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}/benefits")
        public ResponseEntity<List<String>> getOrganizationBenefits (@PathVariable("id") String id){
            List<String> benefitsList;
            try {
                benefitsList = organizationService.listOrganizationBenefits(id);
            } catch (Exception e) {
                logger.error(e.toString());
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok(benefitsList);
        }

        // update all benefits by organization id (create and delete operations can also use this API to update)
        @PostMapping("{id}/benefits")
        public ResponseEntity updateOrganizationBenefits (@RequestBody OrganizationBenefits
        organizationBenefitsRequest, @PathVariable("id") String id){
            try {
                organizationService.updateOrganizationBenefits(organizationBenefitsRequest, id);
            } catch (Exception e) {
                logger.error(e.toString());
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity(HttpStatus.OK);

        }
    }
