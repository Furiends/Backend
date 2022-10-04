package furiends.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import furiends.backend.dto.PetRequest;
import furiends.backend.model.Pet;
import furiends.backend.repository.PetRepository;
import furiends.backend.transformer.PetTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;
    @Mock
    private PetTransformer petTransformer;

    ObjectMapper mapper = new ObjectMapper();

    private Pet mockPetA = new Pet();
    private Pet mockPetB = new Pet();
    private List<Pet> mockPetList = new ArrayList<>() {{
        add(mockPetA);
        add(mockPetB);
    }};


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllPets() {
        when(petRepository.findAll()).thenReturn(mockPetList);
        List<Pet> allPets = petService.findAllPets();
        assertEquals(allPets.size(), mockPetList.size());
        if (allPets.size() > 0) {
            assertEquals(allPets.get(0).getId(), mockPetList.get(0).getId());
        }
    }

    @Test
    void findAllPetsWithinOrganization() {
        String orgId = "12345678";
        mockPetA.setOrganizationId("123456");
        mockPetB.setOrganizationId(orgId);
        List<Pet> mockPetByOrg = new ArrayList<>() {};
        for (Pet pet : mockPetList) {
            if (pet.getOrganizationId().equals(orgId)) {
                mockPetByOrg.add(pet);
            }
        }
        when(petRepository.findAllByOrganization(orgId)).thenReturn(mockPetByOrg);
        List<Pet> allPetsWithinOrganization = petService.findAllPetsWithinOrganization(orgId);
        assertEquals(allPetsWithinOrganization.size(), mockPetByOrg.size());
        if (allPetsWithinOrganization.size() > 0 && mockPetByOrg.size() > 0) {
            assertEquals(allPetsWithinOrganization.get(0).getId(), mockPetByOrg.get(0).getId());
        }
    }

    @Test
    void findAllByPublishStatus() {
        mockPetA.setIsPublished(false);
        mockPetB.setIsPublished(true);
        List<Pet> mockPetByPubList = new ArrayList<>() {};
        for (Pet pet : mockPetList) {
            if (pet.getIsPublished()) {
                mockPetByPubList.add(pet);
            }
        }
        when(petRepository.findAllByPublishStatus(true)).thenReturn(mockPetByPubList);
        List<Pet> allByPublishStatus = petService.findAllByPublishStatus(true);
        assertEquals(allByPublishStatus.size(), mockPetByPubList.size());
        if (allByPublishStatus.size() > 0 && mockPetByPubList.size() > 0) {
            assertEquals(allByPublishStatus.get(0).getId(), mockPetByPubList.get(0).getId());
        }
    }

    @Test
    void findAllByPublishStatusOrg() {
        String orgId = "12345678";
        mockPetA.setOrganizationId("123");
        mockPetB.setOrganizationId(orgId);
        mockPetA.setIsPublished(false);
        mockPetB.setIsPublished(true);
        List<Pet> mockPublishedPetOrgList = new ArrayList<>() {};
        for (Pet pet : mockPetList) {
            if (pet.getIsPublished() && pet.getOrganizationId().equals(orgId)) {
                mockPublishedPetOrgList.add(pet);
            }
        }
        when(petRepository.findAllByPublishStatusOrg(orgId, true)).thenReturn(mockPublishedPetOrgList);
        List<Pet> allByPublishStatusOrg = petService.findAllByPublishStatusOrg(orgId, true);
        assertEquals(allByPublishStatusOrg.size(), mockPublishedPetOrgList.size());
        if (allByPublishStatusOrg.size() > 0 && mockPublishedPetOrgList.size() > 0) {
            assertEquals(allByPublishStatusOrg.get(0).getId(), mockPublishedPetOrgList.get(0).getId());
        }
    }

    @Test
    void findAllByAdoptionStatus() {
        mockPetA.setIsAdopted(true);
        mockPetB.setIsAdopted(false);
        List<Pet> mockAllPetsByAdoptionStatus = new ArrayList<>() {};
        for (Pet pet : mockPetList) {
            if (pet.getIsAdopted()) {
                mockAllPetsByAdoptionStatus.add(pet);
            }
        }
        when(petRepository.findAllByAdoptionStatus(true)).thenReturn(mockAllPetsByAdoptionStatus);
        List<Pet> allByAdoptionStatus = petService.findAllByAdoptionStatus(true);
        assertEquals(allByAdoptionStatus.size(), mockAllPetsByAdoptionStatus.size());
        if (allByAdoptionStatus.size() > 0 && mockAllPetsByAdoptionStatus.size() > 0) {
            assertEquals(allByAdoptionStatus.get(0).getId(), mockAllPetsByAdoptionStatus.get(0).getId());
        }
    }

    @Test
    void findAllByAdoptionStatusOrg() {
        String orgId = "12345678";
        mockPetA.setOrganizationId("123");
        mockPetB.setOrganizationId(orgId);
        mockPetA.setIsAdopted(true);
        mockPetB.setIsAdopted(false);
        List<Pet> mockAllPetsByAdoptionStatusOrg = new ArrayList<>() {};
        for (Pet pet : mockPetList) {
            if (!pet.getIsAdopted() && pet.getOrganizationId().equals(orgId)) {
                mockAllPetsByAdoptionStatusOrg.add(pet);
            }
        }
        when(petRepository.findAllByAdoptionStatusOrg(orgId, true)).thenReturn(mockAllPetsByAdoptionStatusOrg);
        List<Pet> allByAdoptionStatusOrg = petService.findAllByAdoptionStatusOrg(orgId, true);
        assertEquals(allByAdoptionStatusOrg.size(), mockAllPetsByAdoptionStatusOrg.size());
        if (allByAdoptionStatusOrg.size() > 0 && mockAllPetsByAdoptionStatusOrg.size() > 0) {
            assertEquals(allByAdoptionStatusOrg.get(0).getId(), mockAllPetsByAdoptionStatusOrg.get(0).getId());
        }
    }

    @Test
    void findPetById() {
        String petId = "pet123";
        mockPetA.setId(petId);
        when(petRepository.findById(petId)).thenReturn(Optional.ofNullable(mockPetA));
        Optional<Pet> pet = petService.findPetById(petId);
        assertEquals(pet, Optional.of(mockPetA));
    }

    @Test
    void createPet() throws JsonProcessingException {
            String name = "Kit";
            int age = 2;
            String breed = "German Shepherd";
            String jsonInString = "{\"name\":\"" + name +"\",\"age\":\"" + age + "\",\"breed\":\"" + breed + "\"}";
            PetRequest petRequest = mapper.readValue(jsonInString, PetRequest.class);

            petService.createPet(petRequest);

            // capture the parameter passed into petTransformer.fromPetRequestToPet() to verify if certain values were changed
            ArgumentCaptor<Pet> petCaptor = ArgumentCaptor.forClass(Pet.class);

            // verify petTransformer.fromPetRequestToPet was called once
            Mockito.verify(petTransformer, times(1)).fromPetRequestToPet(Mockito.any(PetRequest.class), petCaptor.capture());
            // verify petRepository.save was called once
            Mockito.verify(petRepository, times(1)).save(Mockito.any(Pet.class));

            // test if id, postUpdateTime and postCreateTime were created
            Pet petCaptorValue = petCaptor.getValue();
            assert(!petCaptorValue.getId().isEmpty());
            assert(petCaptorValue.getPostUpdateTime() != null);
            assert(petCaptorValue.getPostCreatedTime() != null);
    }

    @Test
    void updatePet() throws JsonProcessingException {
            String name = "Kit";
            int age = 2;
            String breed = "German Shepherd";
            String jsonInString = "{\"name\":\"" + name + "\",\"age\":\"" + age + "\",\"breed\":\"" + breed + "\"}";
            PetRequest petRequest = mapper.readValue(jsonInString, PetRequest.class);

            String petAId = "pet123";
            mockPetA.setId(petAId);
            when(petService.findPetById(petAId)).thenReturn(Optional.ofNullable(mockPetA));

            petService.updatePet(petRequest, petAId);

            ArgumentCaptor<Pet> petCaptor = ArgumentCaptor.forClass(Pet.class);
            // verify petTransformer.fromPetRequestToPet was called once
            Mockito.verify(petTransformer, times(1)).fromPetRequestToPet(any(PetRequest.class), petCaptor.capture());
            // verify petRepository.save was called once
            Mockito.verify(petRepository, times(1)).save(any(Pet.class));
            Pet petCaptorValue = petCaptor.getValue();
            assert (petCaptorValue.getPostUpdateTime() != null);
    }

    @Test
    void deletePet() {
        String petAId = "pet123";
        mockPetA.setId(petAId);
        when(petRepository.existsById(petAId)).thenReturn(true);
        petService.deletePet(petAId);

        Mockito.verify(petRepository, times(1)).existsById(any(String.class));
        Mockito.verify(petRepository, times(1)).deleteById(any(String.class));
    }
}