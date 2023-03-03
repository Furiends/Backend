package furiends.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import furiends.backend.model.Application;
import furiends.backend.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    private Application mockApplication1 = new Application();
    private Application mockApplication2 = new Application();
    private List<Application> mockApplicationList = new ArrayList<>() {{
        add(mockApplication1);
        add(mockApplication2);
    }};

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllApplicantsByOrganization() {
        String orgId1 = "testId1";
        String orgId2 = "testId2";
        mockApplication1.setOrganizationId(orgId1);
        mockApplication2.setOrganizationId(orgId2);
        List<Application> mockApplicationsByOrg = new ArrayList<>() {};
        for (Application application : mockApplicationList) {
            if (application.getOrganizationId().equals(orgId1)) {
                mockApplicationsByOrg.add(application);
            }
        }
        when(applicationRepository.findAllApplicantsByOrganization(orgId1)).thenReturn(mockApplicationsByOrg);

        List<Application> allApplicationsWithinOrganization = applicationService.findAllApplicantsByOrganization(orgId1);
        assertEquals(allApplicationsWithinOrganization.size(), mockApplicationsByOrg.size());
        if (allApplicationsWithinOrganization.size() > 0 && mockApplicationsByOrg.size() > 0) {
            assertEquals(allApplicationsWithinOrganization.get(0).getApplicationId(),
                    mockApplicationsByOrg.get(0).getApplicationId());
        }
    }

}