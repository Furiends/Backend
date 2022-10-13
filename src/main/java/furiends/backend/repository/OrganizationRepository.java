package furiends.backend.repository;

import furiends.backend.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, String> {
    boolean existsOrganizationByWechatOfficialAccountId(String wechatOfficialAccountId);

    Organization findOrganizationByWechatOfficialAccountId(String wechatOfficialAccountId);
    Organization findOrganizationByUnion_idAndOpen_id(String union_id, String open_id);


}
