package furiends.backend.controller;

import com.fasterxml.uuid.Generators;
import furiends.backend.model.Organization;
import furiends.backend.service.OrganizationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class OrganizationControllerTest {
    @InjectMocks
    private OrganizationController organizationController;

    @Mock
    private OrganizationService organizationService;

    private Organization mockOrganization = new Organization();

    private ArrayList<Organization> mockOrganizationList = new ArrayList<Organization>() {{
        add(mockOrganization);
    }};

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetOrganization() {
        String testOrganizationId = Generators.timeBasedGenerator().generate().toString();
        mockOrganization.setId(testOrganizationId );
        when(organizationService.findOrganizationById(testOrganizationId )).thenReturn(Optional.ofNullable(mockOrganization));
        ResponseEntity<Organization> response = organizationController.getOrganization(testOrganizationId );
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().getId(), mockOrganization.getId());
    }

    @Test
    void testGetAllOrganization() {
        when(organizationService.findAllOrganizations()).thenReturn(mockOrganizationList);
        ResponseEntity<List<Organization>> response = organizationController.getAllOrganization();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(response.getBody().get(0).getId(), mockOrganizationList.get(0).getId());
    }
}
