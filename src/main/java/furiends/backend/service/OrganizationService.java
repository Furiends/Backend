package furiends.backend.service;

import com.alibaba.fastjson.JSONObject;
import furiends.backend.dto.AdoptionProcedure;
import furiends.backend.dto.AdoptionProcedureStep;
import furiends.backend.dto.OrganizationBenefits;
import furiends.backend.dto.OrganizationRequest;
import furiends.backend.dto.*;
import furiends.backend.model.Organization;
import furiends.backend.repository.OrganizationRepository;
import furiends.backend.repository.UserRepository;
import furiends.backend.transformer.OrganizationTransformer;
import furiends.backend.utils.WeChatUtil;
import furiends.backend.utils.CloudAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.*;


@Service
public class OrganizationService {

    @Resource
    private OrganizationRepository organizationRepository;

    @Resource
    private UserRepository userRepository;

    @Autowired
    private OrganizationTransformer organizationTransformer;

    public List<Organization> findAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> findOrganizationById(String id) {
        return organizationRepository.findById(id);
    }


    public Organization createOrganization(OrganizationRequest organizationRequest) {
        Organization newOrganization = new Organization();
        newOrganization.setId();
        newOrganization.setCreatedTime();
        organizationTransformer.fromOrganizationRequestToOrganization(organizationRequest, newOrganization);
        return  newOrganization;
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

    public void registerOrganizationByWechat(OrganizationRequest organizationRequest){
        Organization organization = createOrganization(organizationRequest);
        organization.setUpdatedTime();
        organizationRepository.save(organization);
    }

    public Organization loginOrganizationByWechat(String code){
        JSONObject jsonObject = WeChatUtil.getJSONObject(code);
        String open_id = jsonObject.getString("open_id");
        String union_id = jsonObject.getString("union_id");
        if (open_id.isBlank() || union_id.isBlank()) {
            throw new RuntimeException();
        }

        String representativeUserId = userRepository.findUserByOpenIdAndUnionId(open_id,union_id).getWechatId();
        Organization organization = organizationRepository.findOrganizationByRepresentativeUserId(representativeUserId);
        organization.setUpdatedTime();
        organizationRepository.save(organization);
        return organization;
    }



    public List<AdoptionProcedureStep> listAdoptionProcedureSteps(String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        String adoptionProcedureString = organization.getAdoptionProcedure();
        List<AdoptionProcedureStep> adoptionProcedureStepList = organizationTransformer.fromJsonStringToAdoptionProcedureStepList(adoptionProcedureString);
        return adoptionProcedureStepList;
    }

    public void updateOrganizationAdoptionProcedure(AdoptionProcedure adoptionProcedureRequest, String organizationId) {
        Organization organization = findOrganizationById(organizationId).get();
        String adoptionProcedureString = organizationTransformer.fromAdoptionProcedureToJsonString(adoptionProcedureRequest);
        organization.setAdoptionProcedure(adoptionProcedureString);
        organizationRepository.save(organization);
    }


    public boolean verifyInvitationCode(String invitationCode) {
        //TODO replace INVITATION_CODE
        String INVITATION_CODE = "FuriendsCodeForOrg";
        return invitationCode.equals(INVITATION_CODE);

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
        Organization organization =  findOrganizationById(organizationId).get();
        List<AdoptionAgreement> adoptionAgreementList = organizationTransformer.fromJsonStringToAdoptionAgreementList(organization.getAdoptionAgreements());
        adoptionAgreementList.removeIf(agreement -> agreement.getKey().equals(key));
        List<String> toDeleteList = new ArrayList<>();
        toDeleteList.add(key);
        cloudAPI.deleteFile(toDeleteList);
        String adoptionAgreementString = organizationTransformer.fromAdoptionAgreementListToJsonString(adoptionAgreementList);
        organization.setAdoptionAgreements(adoptionAgreementString);
        organizationRepository.save(organization);
    }
}

