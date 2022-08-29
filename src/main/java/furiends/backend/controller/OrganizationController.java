package furiends.backend.controller;

import furiends.backend.dto.OrganizationRequest;
import furiends.backend.model.Organization;
import furiends.backend.service.OrganizationService;
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

@RestController()
@RequestMapping(path = "api/v1/organizations")
@Api(value = "机构", tags = "机构")
public class OrganizationController {
    private static final Logger logger = LogManager.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService organizationService;

    // get organization by id
    @GetMapping("{id}")
    @ApiOperation(value="根据id获取机构")
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
    @ApiOperation(value="获取所有机构列表")
    public ResponseEntity<List<Organization>> getAllOrganization() {
        return ResponseEntity.ok(organizationService.findAllOrganizations());
    }

    // create an organization
    @PostMapping("")
    @ApiOperation(value="创建机构")
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
    @ApiOperation(value="更新机构信息")
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
    @ApiOperation(value="删除机构")
    public ResponseEntity deleteOrganization(@PathVariable String id) {
        try {
            organizationService.deleteOrganization(id);
        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
