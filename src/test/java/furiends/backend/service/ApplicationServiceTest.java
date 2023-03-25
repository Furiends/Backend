package furiends.backend.service;

import furiends.backend.model.Pet;
import furiends.backend.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import furiends.backend.model.Application;
import furiends.backend.repository.ApplicationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApplicationServiceTest {
    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;

    @Mock
    private PetRepository petRepository;

    private Pet mockPet = new Pet();

    private Application mockApplication = new Application();
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

    @Test
    void findApplicationById(){
        String testId = "testId1234567";
        mockApplication.setApplicationId(testId);
        when(applicationRepository.findById(testId)).thenReturn(Optional.of(mockApplication));
        Optional<Application> applicationOptional = applicationService.findApplicationById(testId);
        assertTrue(applicationOptional.isPresent());
        assertEquals(applicationOptional, Optional.of(mockApplication));
        assertEquals(applicationOptional.get().getApplicationId(), mockApplication.getApplicationId());
    }

    @Test
    void updateApplicationStatus_testInvalidStatusInput() {
        String applicationId = "testApplicationId";
        mockApplication.setApplicationId(applicationId);
        int invalidStatus = 4;
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(mockApplication));
        assertThrows(RuntimeException.class, () -> {
            applicationService.updateApplicationStatus(applicationId, invalidStatus);
        });
    }

    @Test
    void updateApplicationStatus_withStatus3_Rejected(){
        mockApplication1.setApplicationId("testId");
        mockApplication1.setPetId("petId");
        mockApplication1.setApplicationStatus(0);
        mockPet.setId("petId");
        mockPet.setIsAdopted(false);

        String testId = "testId";
        int status = 3;
        when(applicationRepository.findById(testId)).thenReturn(Optional.of(mockApplication1));
        applicationService.updateApplicationStatus(testId, status);
        assertEquals(mockApplication1.getApplicationStatus(), status);
        assertTrue(applicationService.findApplicationById(testId).isPresent());
    }

    @Test
    void updateApplicationStatus_withStatus2() {
        String applicationId1 = "testId1";
        String applicationId2 = "testId2";
        String petId = "testPetId";
        mockApplication1.setApplicationId(applicationId1);
        mockApplication1.setPetId(petId);
        mockApplication1.setApplicationStatus(1);
        mockApplication2.setApplicationId(applicationId2);
        mockApplication2.setPetId(petId);
        mockApplication2.setApplicationStatus(1);
        mockPet.setId(petId);
        mockPet.setIsAdopted(false);

        String testId = "testId1";
        int status = 2;

        when(applicationRepository.findById(testId)).thenReturn(Optional.of(mockApplication1));
        when(petRepository.findById(mockApplication1.getPetId())).thenReturn(Optional.of(mockPet));

        when(applicationRepository.findApplicationByPetId(mockApplication1.getPetId()))
                .thenReturn(mockApplicationList);

        //System.out.println(mockApplication1.getApplicationStatus() + "-----1print");
        Application app = applicationService.updateApplicationStatus(testId, status);
        //System.out.println(mockApplication1.getApplicationStatus() + "-----2p");
        System.out.println(app.getApplicationStatus());

        assertEquals(mockApplication1.getApplicationStatus(), status);
        assertTrue(mockPet.getIsAdopted());

        for (Application application : applicationService.findApplicationByPetId(mockPet.getId())) {
            if (!application.getApplicationId().equals(mockApplication1.getApplicationId())) {
                assertEquals(application.getApplicationId(), applicationId2);
                //System.out.println(application.getApplicationId());
                //System.out.println(application.getApplicationStatus());
                assertEquals(application.getApplicationStatus(), 3);
            }
        }
    }




}