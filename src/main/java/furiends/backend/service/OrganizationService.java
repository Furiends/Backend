package furiends.backend.service;

import com.alibaba.fastjson.JSONObject;
import furiends.backend.dto.AdoptionProcedure;
import furiends.backend.dto.AdoptionProcedureStep;
import furiends.backend.dto.OrganizationRequest;
import furiends.backend.model.Organization;
import furiends.backend.repository.OrganizationRepository;
import furiends.backend.transformer.OrganizationTransformer;
import furiends.backend.utils.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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
        organizationRepository.save(organizationInstance);
    }

    public void deleteOrganization(String id) {
        if (organizationRepository.existsById(id)) {
            organizationRepository.deleteById(id);
        }
    }

    public void registerOrganizationByWechat(OrganizationRequest organizationRequest, String code){

        createOrganization(organizationRequest);

        String wechatOfficialAccountId = organizationRequest.getWechatOfficialAccountId();
        Organization organization = organizationRepository.findOrganizationByWechatOfficialAccountId(wechatOfficialAccountId);

        JSONObject jsonObject = WeChatUtil.getJSONObject(code);
        organization.setOpen_id(WeChatUtil.getOpenId(jsonObject));
        organization.setUnion_id(WeChatUtil.getUnionId(jsonObject));
        organizationRepository.save(organization);
    }

    public Organization loginOrganizationByWechat(String code){
        JSONObject jsonObject = WeChatUtil.getJSONObject(code);
        String open_id = jsonObject.getString("open_id");
        String union_id = jsonObject.getString("union_id");

        if (open_id.isBlank() || union_id.isBlank()) {
            throw new RuntimeException();
        }
        Organization organization =  organizationRepository.findOrganizationByUnion_idAndOpen_id(open_id, union_id);
        if(organization == null){
            throw new RuntimeException();
        }

        organization.setUpdatedTime(new Date());
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
        if (organization == null) return;
        String adoptionProcedureString = organizationTransformer.fromAdoptionProcedureToJsonString(adoptionProcedureRequest);
        organization.setAdoptionProcedure(adoptionProcedureString);
        organizationRepository.save(organization);
    }
}

