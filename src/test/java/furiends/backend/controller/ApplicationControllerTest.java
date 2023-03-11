package furiends.backend.controller;

import furiends.backend.model.Application;
import furiends.backend.model.Pet;
import furiends.backend.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApplicationControllerTest {

    @InjectMocks
    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;


    private Pet mockPet = new Pet();

    private Application mockApplication = new Application();

    private Application mockApplication1 = new Application();
    private Application mockApplication2 = new Application();
    private List<Application> mockApplicationList = new ArrayList<>() {{
        add(mockApplication1);
        add(mockApplication2);
    }};


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllApplicantsByOrganization() {
        String orgId = "testId";
        when(applicationService.findAllApplicantsByOrganization(orgId)).thenReturn(mockApplicationList);
        ResponseEntity<List<Application>> responseEntity = applicationController.findAllApplicantsByOrganization(orgId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockApplicationList.size(), responseEntity.getBody().size());
    }

    @Test
    void findApplicationById_ReturnsOkWithApplication() {
        String applicationId = "testId1";
        mockApplication.setApplicationId(applicationId);
        when(applicationService.findApplicationById(applicationId)).thenReturn(Optional.of(mockApplication));
        ResponseEntity<Application> responseEntity = applicationController.findApplicationById(applicationId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockApplication, responseEntity.getBody());
    }

    @Test
    void findApplicationById_ReturnsInternalServerErrorWhenExceptionThrown() {
        String applicationId = "testId";
        when(applicationService.findApplicationById(applicationId)).thenThrow(new RuntimeException("Test Exception"));
        ResponseEntity<Application> responseEntity = applicationController.findApplicationById(applicationId);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateApplicationStatus() {
        String applicationId = "testApplicationId";
        int status = 2;
        mockApplication.setApplicationId(applicationId);
        mockApplication.setApplicationStatus(0);

        mockPet.setId("testPetId");
        mockApplication.setPetId(mockPet.getId());

        when(applicationService.findApplicationById(applicationId)).thenReturn(Optional.ofNullable(mockApplication));
        when(applicationService.updateApplicationStatus(applicationId, status)).thenReturn(mockApplication);



        ResponseEntity responseEntity = applicationController.updateApplicationStatus(applicationId, status);

        Application app = (Application) responseEntity.getBody();
        System.out.println(app.getApplicationId());

        System.out.println(app.getApplicationStatus());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        System.out.println(mockApplication.getApplicationStatus());

        assertEquals(mockApplication, responseEntity.getBody());

    }
}
