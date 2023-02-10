package furiends.backend.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import furiends.backend.dto.AdoptionProcedure;
import furiends.backend.dto.AdoptionProcedureStep;
import furiends.backend.dto.OrganizationBenefits;
import furiends.backend.dto.OrganizationRequest;
import furiends.backend.model.Organization;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationTransformer {

    public void fromOrganizationRequestToOrganization(OrganizationRequest organizationRequest, Organization newOrganization) {
        newOrganization.setName(organizationRequest.getName());
        newOrganization.setDescription(organizationRequest.getDescription());
        newOrganization.setIcon(organizationRequest.getIcon());
        newOrganization.setRepresentativeUserId(organizationRequest.getRepresentativeUserId());
        newOrganization.setProvince(organizationRequest.getProvince());
        newOrganization.setCity(organizationRequest.getCity());
        newOrganization.setDistrict(organizationRequest.getDistrict());
        newOrganization.setAddress(organizationRequest.getAddress());
        newOrganization.setPhoneNumber(organizationRequest.getPhoneNumber());
        newOrganization.setStatusFlag(organizationRequest.getStatusFlag());
        newOrganization.setEstablishDate(organizationRequest.getEstablishDate());
        newOrganization.setRescuePetCounts(organizationRequest.getRescuePetCounts());
        newOrganization.setVolunteerCounts(organizationRequest.getVolunteerCounts());
        newOrganization.setAdoptionPetCounts(organizationRequest.getAdoptionPetCounts());
        newOrganization.setLastPostPlacementVisitPetId(organizationRequest.getLastPostPlacementVisitPetId());
        newOrganization.setLastPostPlacementVisitDate(organizationRequest.getLastPostPlacementVisitDate());
        newOrganization.setOrganizationFromAddress(organizationRequest.getOrganizationFromAddress());
        newOrganization.setPostPlacementVisitCounts(organizationRequest.getPostPlacementVisitCounts());
        newOrganization.setWechatOfficialAccountId(organizationRequest.getWechatOfficialAccountId());
    }

    public List<AdoptionProcedureStep> fromJsonStringToAdoptionProcedureStepList(String adoptionProcedureString) {
        List<AdoptionProcedureStep> adoptionProcedureStepList = new ArrayList<>();
        if (adoptionProcedureString == null || adoptionProcedureString.length() == 0) return adoptionProcedureStepList;
        AdoptionProcedure adoptionProcedureInstance;
        ObjectMapper mapper = new ObjectMapper();
        try {
            adoptionProcedureInstance = mapper.readValue(adoptionProcedureString, new TypeReference<>() {
            });
            adoptionProcedureStepList = adoptionProcedureInstance.getSteps();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return adoptionProcedureStepList;
    }

    public String fromAdoptionProcedureToJsonString(AdoptionProcedure adoptionProcedure) {
        if (adoptionProcedure == null) return "";
        String adoptionProcedureString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            adoptionProcedureString = mapper.writeValueAsString(adoptionProcedure);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return adoptionProcedureString;
    }

    public List<String> fromJsonStringToBenefitsList(String benefitsString) {
        List<String> benefitsList = new ArrayList<>();
        if (benefitsString == null || benefitsString.length() == 0) return benefitsList;
        OrganizationBenefits benefitsInstance;
        ObjectMapper mapper = new ObjectMapper();
        try {
            benefitsInstance = mapper.readValue(benefitsString, new TypeReference<>() {
            });
            benefitsList = benefitsInstance.getBenefits();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return benefitsList;
    }

    public String fromOrganizationBenefitsToJsonString(OrganizationBenefits benefits) {
        if (benefits == null) return "";
        String benefitsString = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            benefitsString = mapper.writeValueAsString(benefits);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return benefitsString;
    }
}
