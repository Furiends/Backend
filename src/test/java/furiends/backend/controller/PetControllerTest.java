package furiends.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.service.PetService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @InjectMocks
    private PetController petController;

    @Mock
    private PetService petService;

    ObjectMapper mapper = new ObjectMapper();

    private Pet mockPetA = new Pet();
    private Pet mockPetB = new Pet();
    private List<Pet> mockPetList = new ArrayList<>() {{
        add(mockPetA);
        add(mockPetB);
    }};


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllPets() {
        when(petService.findAllPets()).thenReturn(mockPetList);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPets();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPetList.size(), responseEntity.getBody().size());
    }

    @Test
    void getAllPetsWithinOrganization() {
        String orgIdA = "OrgA";
        mockPetA.setOrganizationId(orgIdA);
        List<Pet> result = new ArrayList<>(){{add(mockPetA);}};

        when(petService.findAllPetsWithinOrganization(orgIdA)).thenReturn(result);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPetsWithinOrganization(orgIdA);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result.size(), responseEntity.getBody().size());
    }

    @Test
    void getPetById() {
        String id = "123456";
        mockPetA.setId(id);
        when(petService.findPetById(id)).thenReturn(java.util.Optional.ofNullable(mockPetA));
        ResponseEntity<Pet> responseEntity = petController.getPetById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody().getId());
    }

    @Test
    void getAllPetsByPublishStatus() {
        mockPetA.setIsPublished(true);
        mockPetB.setIsPublished(false);
        List<Pet> result = new ArrayList<>(){{add(mockPetA);}};

        when(petService.findAllByPublishStatus(true)).thenReturn(result);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPetsByPublishStatus(true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result.size(), responseEntity.getBody().size());
    }

    @Test
    void getAllPetsByPublishStatusOrg() {
        String orgIdA = "OrgA";
        mockPetA.setOrganizationId(orgIdA);
        mockPetA.setIsPublished(true);
        List<Pet> result = new ArrayList<>(){{add(mockPetA);}};

        when(petService.findAllByPublishStatusOrg(orgIdA, true)).thenReturn(result);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPetsByPublishStatusOrg(orgIdA,true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result.size(), responseEntity.getBody().size());
    }

    @Test
    void getAllPetsByAdoptionStatus() {
        mockPetA.setIsAdopted(true);
        List<Pet> result = new ArrayList<>(){{add(mockPetA);}};

        when(petService.findAllByAdoptionStatus(true)).thenReturn(result);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPetsByAdoptionStatus(true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result.size(), responseEntity.getBody().size());
    }

    @Test
    void getAllPetsByAdoptionStatusOrg() {
        String orgIdA = "OrgA";
        mockPetA.setOrganizationId(orgIdA);
        mockPetA.setIsAdopted(true);
        List<Pet> result = new ArrayList<>(){{add(mockPetA);}};

        when(petService.findAllByAdoptionStatusOrg(orgIdA, true)).thenReturn(result);
        ResponseEntity<List<Pet>> responseEntity = petController.getAllPetsByAdoptionStatusOrg(orgIdA,true);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(result.size(), responseEntity.getBody().size());
    }

    @Test
    void createPet() {
        try {
            String name = "Kit";
            int age = 2;
            String breed = "German Shepherd";
            String jsonInString = "{\"name\":\"" + name + "\",\"age\":\"" + age + "\",\"breed\":\"" + breed + "\"}";
            PetRequest petRequest = mapper.readValue(jsonInString, PetRequest.class);

            when(petService.createPet(petRequest)).thenReturn(mockPetA);
            ResponseEntity responseEntity = petController.createPet(petRequest);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            assertEquals(mockPetA, responseEntity.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    void updatePet() {
        try {
            String id = "123456";
            String name = "Kit";
            int age = 2;
            String breed = "German Shepherd";
            String jsonInString = "{\"name\":\"" + name + "\",\"age\":\"" + age + "\",\"breed\":\"" + breed + "\"}";
            PetRequest petRequest = mapper.readValue(jsonInString, PetRequest.class);

            when(petService.updatePet(petRequest, id)).thenReturn(mockPetA);
            ResponseEntity responseEntity = petController.updatePet(petRequest, id);
            assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Test
    void deletePet() {
        String id = "123456";
        ResponseEntity responseEntity = petController.deletePet(id);
        verify(petService, times(1)).deletePet(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}