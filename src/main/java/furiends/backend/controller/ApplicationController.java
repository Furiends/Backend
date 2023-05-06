package furiends.backend.controller;

import furiends.backend.model.Application;
import furiends.backend.service.ApplicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/application")
@Api(value = "领养申请", tags = "领养申请")
public class ApplicationController {

    private static final Logger logger = LogManager.getLogger(ApplicationController.class);
    @Autowired
    private ApplicationService applicationService;

    // list all applicants to one specific organization
    @GetMapping("/organizationId={organizationId}")
    @ApiOperation(value="根据机构organizationId获取领养申请")
    public ResponseEntity<List<Application>> findAllApplicantsByOrganization(@PathVariable("organizationId") String organizationId) {
        try {
            return ResponseEntity.ok(applicationService.findAllApplicantsByOrganization(organizationId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pet={petId}")
    @ApiOperation(value="根据petId获取领养申请")
    public ResponseEntity<List<Application>> findApplicationByPetId(@PathVariable("petId") String petId){
        try {
            return ResponseEntity.ok(applicationService.findApplicationByPetId(petId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user={userId}")
    @ApiOperation(value="根据userId获取领养申请")
    public ResponseEntity<List<Application>> findApplicationByUserId(@PathVariable("userId") String userId) {
        try {
            return ResponseEntity.ok(applicationService.findApplicationByUserId(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/process={userId}/status={status}")
    @ApiOperation(value="根据userId和status获取领养申请")
    public ResponseEntity<List<Application>>findApplicationWithStatus(@PathVariable("userId") String userId, @PathVariable("status") int status) {
        try {
            return ResponseEntity.ok(applicationService.listApplicationWithStatus(userId, status));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/applicationId={applicationId}")
    @ApiOperation(value="根据applicationId获取领养申请")
    public ResponseEntity<Application>findApplicationById(@PathVariable("applicationId") String applicationId){
        try {
            return ResponseEntity.ok(applicationService.findApplicationById(applicationId).get());
        } catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/applicationId={applicationId}/status={status}")
    @ApiOperation(value="根据applicationId更新领养申请状态")
    public ResponseEntity updateApplicationStatus(@PathVariable("applicationId") String applicationId, @PathVariable("status") int status){
        try {
            return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));
        } catch (Exception e){
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("applicationId={applicationId}/rejectReason={rejectReason}")
    @ApiOperation(value="根据applicationId更新拒接领养申请的原因")
    public ResponseEntity updateRejectReason(@PathVariable("applicationId") String applicationId, @PathVariable("rejectReason") String rejectReason){
        try {
            return ResponseEntity.ok(applicationService.updateRejectReason(applicationId, rejectReason));
        } catch (Exception e) {
            logger.error((e.toString()));
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
