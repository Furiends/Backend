package furiends.backend.controller;

import furiends.backend.dto.*;
import furiends.backend.model.Organization;
import furiends.backend.service.OrganizationService;
import furiends.backend.utils.CloudAPI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/organizations")
public class OrganizationController {
    String bucket = "";
    String secretId = "";
    String secretKey = "";
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

    // get benefits by organization id
    @GetMapping("{id}/benefits")
    public ResponseEntity<List<String>> getOrganizationBenefits(@PathVariable("id") String id) {
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
    public ResponseEntity updateOrganizationBenefits(@RequestBody OrganizationBenefits organizationBenefitsRequest, @PathVariable("id") String id) {
        try {
            organizationService.updateOrganizationBenefits(organizationBenefitsRequest, id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


    // get all adoption agreements by organization id
    // When only the current effective agreement is requested, onlyLatest should be True
    @GetMapping("{id}/adoptionAgreement/onlyLatest={onlyLatest}")
    public ResponseEntity<List<AdoptionAgreement>> getOrganizationAdoptionAgreement(@PathVariable("id") String id, @PathVariable("onlyLatest") Boolean onlyLatest) {
        List<AdoptionAgreement> adoptionAgreementList;
        try {
            CloudAPI cloudAPI = new CloudAPI();
            cloudAPI.createCosClient(bucket, secretId, secretKey);
            adoptionAgreementList = organizationService.findAdoptionAgreementByOrgId(id, onlyLatest, cloudAPI);
            cloudAPI.shutDownCosClient();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(adoptionAgreementList);
    }


    // add a new adoption agreement
    @PostMapping("{id}/adoptionAgreement")
    public ResponseEntity<List<AdoptionAgreement>> addOrganizationAdoptionAgreement(@PathVariable("id") String id, @RequestBody MultipartFile newAgreement) {
        List<AdoptionAgreement> adoptionAgreementList;
        try {
            CloudAPI cloudAPI = new CloudAPI();
            cloudAPI.createCosClient(bucket, secretId, secretKey);
            adoptionAgreementList = organizationService.addOrganizationAdoptionAgreement(id, newAgreement, cloudAPI);
            cloudAPI.shutDownCosClient();
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(adoptionAgreementList);
    }

    // update the order of adoption agreements (by pinning an existing agreement to the top)
    @PutMapping("{id}/adoptionAgreement")
    public ResponseEntity updateOrganizationAdoptionAgreement(@PathVariable("id") String id, @RequestBody List<AdoptionAgreement> updatedAdoptionAgreementList ) {
        try {
            organizationService.updateOrganizationAdoptionAgreement(id, updatedAdoptionAgreementList);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // delete one adoption agreement
    @DeleteMapping("{id}/adoptionAgreement/{key}")
    public ResponseEntity deleteOrganizationAdoptionAgreement(@PathVariable("id") String id, @PathVariable("key") String key) {
        try {
            CloudAPI cloudAPI = new CloudAPI();
            cloudAPI.createCosClient(bucket, secretId, secretKey);
            organizationService.deleteOrganizationAdoptionAgreement(id, key, cloudAPI);
            cloudAPI.shutDownCosClient();
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










}
