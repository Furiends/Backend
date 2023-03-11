package furiends.backend.repository;

import furiends.backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    Application findApplicationByApplicationId(String ApplicationId);
    List<Application> findApplicationByPetId(String PetId);

    List<Application> findApplicationByUserId(String UserId);

    @Query("SELECT a FROM application a WHERE a.organizationId = ?1 ORDER BY a.createdTime")
    List<Application> findAllApplicantsByOrganization(String organizationId);

    @Query("SELECT a FROM application a WHERE a.userId = ?1 AND a.applicationStatus = ?2")
    List<Application> listApplicationWithStatus(String userId,int status);
}
