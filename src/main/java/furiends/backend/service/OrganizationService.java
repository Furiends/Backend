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
import java.net.URL;
import java.util.*;

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
        for (AdoptionAgreement agreement : adoptionAgreementList) {
            agreement.setUrl(cloudAPI.readFromCloud(agreement.getKey()));
            response.add(agreement);
            if (onlyLatest) {
                return response;
            }
        }
        return response;
    }

    public List<AdoptionAgreement> addOrganizationAdoptionAgreement (String organizationId, MultipartFile newAgreement, CloudAPI cloudAPI) throws IOException {
        String category = "AdoptionAgreement";
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


    // update: change agreements order by topping one of them
    public void updateOrganizationAdoptionAgreement(String organizationId, List<AdoptionAgreement> updatedAdoptionAgreementList) {
        Organization organization =  findOrganizationById(organizationId).get();
        String adoptionAgreementString = organizationTransformer.fromAdoptionAgreementListToJsonString(updatedAdoptionAgreementList);
        organization.setAdoptionAgreements(adoptionAgreementString);
        organizationRepository.save(organization);
    }


    // delete one agreement
    public void deleteOrganizationAdoptionAgreement(String organizationId, String key, CloudAPI cloudAPI){
        // delete the agreement from the cloud database
        List<String> toDeleteList = new ArrayList<>();
        toDeleteList.add(key);
        cloudAPI.deleteFile(toDeleteList);
        // update the organization
        Organization organization =  findOrganizationById(organizationId).get();
        List<AdoptionAgreement> adoptionAgreementList = organizationTransformer.fromJsonStringToAdoptionAgreementList(organization.getAdoptionAgreements());
        adoptionAgreementList.removeIf(agreement -> agreement.getKey().equals(key));
        String adoptionAgreementString = organizationTransformer.fromAdoptionAgreementListToJsonString(adoptionAgreementList);
        organization.setAdoptionAgreements(adoptionAgreementString);
        organizationRepository.save(organization);
    }

    // get the photos of an organization by id
    public PhotoResponse getOrganizationPhotos(String id, CloudAPI cloudAPI) {
        Organization organization = findOrganizationById(id).get();
        String photoKeyListString = organization.getOrgPhotoKeyList();
        List<String> photoKeyList = organizationTransformer.fromJsonStringToPhotoKeyList(photoKeyListString);
        PhotoResponse photoResponse = new PhotoResponse();
        photoResponse.setId(id);
        List<String> urlList = new ArrayList<>();
        for (String key : photoKeyList) {
            urlList.add(String.valueOf(cloudAPI.readFromCloud(key)));
        }
        photoResponse.setPhotoUrlList(urlList);
        return photoResponse;
    }


    // add photos of an organization
    public void addOrganizationPhotos (String organizationId, List<MultipartFile> photos, CloudAPI cloudAPI) throws IOException {
        String category = "OrgPhoto";
        Organization organization = findOrganizationById(organizationId).get();
        List<String> photoKeyList = new ArrayList<>();
        // upload to cloud
        for (MultipartFile photo : photos) {
            Map<String, String> photoData = cloudAPI.uploadToCloud(photo, organizationId, category);
            photoKeyList.add(photoData.get("key"));
        }
        // update photoKeyList of the organization
        String newPhotoKeyList = organizationTransformer.fromPhotoKeyListToJsonString(photoKeyList);
        organization.setOrgPhotoKeyList(newPhotoKeyList);
        organizationRepository.save(organization);
    }


    // update photos of an organization
    public void updateOrganizationPhotos(String organizationId, List<MultipartFile> photos, CloudAPI cloudAPI) throws IOException {
        // delete all the existing photos
        deleteOrganizationPhotos(organizationId, cloudAPI);
        // upload the new photos
        addOrganizationPhotos(organizationId, photos, cloudAPI);
    }


    // delete photos of an organization
    public void deleteOrganizationPhotos(String organizationId, CloudAPI cloudAPI) {
        Organization organization =  findOrganizationById(organizationId).get();
        List<String> photoKeyList = organizationTransformer.fromJsonStringToPhotoKeyList(organization.getOrgPhotoKeyList());
        // delete the organization's photos from cloud
        cloudAPI.deleteFile(photoKeyList);
        // update the organization
        organization.setOrgPhotoKeyList("");
        organizationRepository.save(organization);
    }

    public URL getOrganizationIcon(String id, CloudAPI cloudAPI) {
        Organization organization = findOrganizationById(id).get();
        String iconKey = organization.getIcon();
        return cloudAPI.readFromCloud(iconKey);
    }

    public URL addOrganizationIcon(String id, MultipartFile icon, CloudAPI cloudAPI) throws IOException {
        String category = "OrgIcon";
        Organization organization = findOrganizationById(id).get();
        // upload to cloud
        Map<String, String> iconData = cloudAPI.uploadToCloud(icon, id, category);
        // update organization
        organization.setIcon(iconData.get("key"));
        organizationRepository.save(organization);
        return getOrganizationIcon(id, cloudAPI);
    }

    public URL updateOrganizationIcon(String id, MultipartFile icon, CloudAPI cloudAPI) throws IOException {
        deleteOrganizationIcon(id, cloudAPI);
        return addOrganizationIcon(id, icon, cloudAPI);
    }

    public void deleteOrganizationIcon(String id, CloudAPI cloudAPI) {
        Organization organization = findOrganizationById(id).get();
        List<String> toDelete = new ArrayList<>();
        toDelete.add(organization.getIcon());
        // delete the organization's icon from cloud
        cloudAPI.deleteFile(toDelete);
        // update the organization
        organization.setIcon("");
        organizationRepository.save(organization);
    }
}

