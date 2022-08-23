package furiends.backend.transformer;

import furiends.backend.dto.OrganizationRequest;
import furiends.backend.model.Organization;
import org.springframework.stereotype.Component;

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
        newOrganization.setWelfare(organizationRequest.getWelfare());
        newOrganization.setWechatOfficialAccountId(organizationRequest.getWechatOfficialAccountId());
    }
}
