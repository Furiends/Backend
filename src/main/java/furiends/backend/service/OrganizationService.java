package furiends.backend.service;

import furiends.backend.dto.*;
import furiends.backend.model.Organization;
import furiends.backend.repository.OrganizationRepository;
import furiends.backend.transformer.OrganizationTransformer;
import furiends.backend.utils.CloudAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrganizationService {

    @Resource
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationTransformer organizationTransformer;

    public List<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> findOrganizationById(String id) {
        return organizationRepository.findById(id);
    }

    public void createOrganization(OrganizationRequest organizationRequest) {
        Organization newOrganization = new Organization();
        newOrganization.setId();
        newOrganization.setCreatedTime();
        organizationTransformer.fromOrganizationRequestToOrganization(organizationRequest, newOrganization);
        organizationRepository.save(newOrganization);
    }

    public void updateOrganization(OrganizationRequest organizationRequest, String id) {
        Organization organizationInstance = findOrganizationById(id).get();
        organizationTransformer.fromOrganizationRequestToOrganization(organizationRequest, organizationInstance);
        // TODO: add org photo upload; update new url for org icon
        organizationRepository.save(organizationInstance);
    }

    public void deleteOrganization(String id) {
        if (organizationRepository.existsById(id)) {
            organizationRepository.deleteById(id);
        }
    }

    public List<AdoptionProcedureStep> listAdoptionProcedureSteps(String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        String adoptionProcedureString = organization.getAdoptionProcedure();
        List<AdoptionProcedureStep> adoptionProcedureStepList = organizationTransformer.fromJsonStringToAdoptionProcedureStepList(adoptionProcedureString);
        return adoptionProcedureStepList;
    }

    public void updateOrganizationAdoptionProcedure(AdoptionProcedure adoptionProcedureRequest, String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        if (organization == null) return;
        String adoptionProcedureString = organizationTransformer.fromAdoptionProcedureToJsonString(adoptionProcedureRequest);
        organization.setAdoptionProcedure(adoptionProcedureString);
        organizationRepository.save(organization);
    }

    public List<String> listOrganizationBenefits(String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        String benefitsString = organization.getBenefits();
        List<String> benefitsList = organizationTransformer.fromJsonStringToBenefitsList(benefitsString);
        return benefitsList;
    }

    public void updateOrganizationBenefits(OrganizationBenefits benefitsRequest, String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        if (organization == null) return;
        String benefitsString = organizationTransformer.fromOrganizationBenefitsToJsonString(benefitsRequest);
        organization.setBenefits(benefitsString);
        organizationRepository.save(organization);
    }

    // find adoption agreements by organization id
    // if onlyLatest is true, only return the first agreement; otherwise return all
    public List<AdoptionAgreement> findAdoptionAgreementByOrgId (String organizationId, Boolean onlyLatest, CloudAPI cloudAPI) {
        Organization organization = findOrganizationById(organizationId).get();
        String adoptionAgreementsString = organization.getAdoptionAgreements();
        List<AdoptionAgreement> adoptionAgreementList = (organizationTransformer.fromJsonStringToAdoptionAgreementList(adoptionAgreementsString));
        List<AdoptionAgreement> response = new ArrayList<AdoptionAgreement>();
        if (onlyLatest) {
            response.add(adoptionAgreementList.get(0));
        } else {
            for (AdoptionAgreement agreement : adoptionAgreementList) {
                agreement.setUrl(cloudAPI.readFromCloud(agreement.getKey()));
                response.add(agreement);
            }
        }
        return response;
    }

    public List<AdoptionAgreement> addOrganizationAdoptionAgreement (String organizationId, MultipartFile newAgreement, CloudAPI cloudAPI) throws IOException {
        String category = "AdoptionAgreement";
        // TODO: add error handling
        Organization organization = findOrganizationById(organizationId).get();
        String adoptionAgreementString = organization.getAdoptionAgreements();
        List<AdoptionAgreement> adoptionAgreementList = organizationTransformer.fromJsonStringToAdoptionAgreementList(adoptionAgreementString);
        // upload to cloud
        Map<String, String> agreementData = cloudAPI.uploadToCloud(newAgreement, organizationId, category);
        AdoptionAgreement newAdoptionAgreement = new AdoptionAgreement(agreementData.get("key"), agreementData.get("fileName"), agreementData.get("uploadDate"));
        // add the new agreement to the start of the agreement list
        adoptionAgreementList.add(0, newAdoptionAgreement);
        // update adoption agreements of the current organization
        String newAdoptionAgreementString = organizationTransformer.fromAdoptionAgreementListToJsonString(adoptionAgreementList);
        organization.setAdoptionAgreements(newAdoptionAgreementString);
        organizationRepository.save(organization);

        return findAdoptionAgreementByOrgId(organizationId, false, cloudAPI);
    }

    // update: change agreements order or delete one agreement
    public void updateOrganizationAdoptionAgreement(String organizationId, String key, Boolean toDelete, CloudAPI cloudAPI) {
        Organization organization =  findOrganizationById(organizationId).get();
        List<AdoptionAgreement> adoptionAgreementList = organizationTransformer.fromJsonStringToAdoptionAgreementList(organization.getAdoptionAgreements());
        AdoptionAgreement temp = null;
        for (AdoptionAgreement agreement : adoptionAgreementList) {
            if (agreement.getKey().equals(key)) {
                temp = agreement;
                adoptionAgreementList.remove(agreement);
            }
        }
        if (toDelete) {
            List<String> toDeleteList = new ArrayList<>();
            toDeleteList.add(key);
            cloudAPI.deleteFile(toDeleteList);
        } else {
            // move the agreement to the top(start) of the list
            adoptionAgreementList.add(0, temp);
        }
        String adoptionAgreementString = organizationTransformer.fromAdoptionAgreementListToJsonString(adoptionAgreementList);
        organization.setAdoptionAgreements(adoptionAgreementString);
        organizationRepository.save(organization);

    }
}

