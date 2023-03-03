package furiends.backend.controller;

import furiends.backend.model.Application;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApplicationControllerTest {

    @InjectMocks
    private ApplicationController applicationController;

    @Mock
    private ApplicationService applicationService;

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




}
