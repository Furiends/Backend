package furiends.backend.repository;

import furiends.backend.model.Application;
import furiends.backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, String> {
    List<Application> findApplicationByPetId(String PetId);
    List<Application> findApplicationByUserId(String UserId);

    @Query("SELECT a FROM application a WHERE a.userId = ?1 AND a.applicationStatus = ?2")
    List<Application> listApplicationWithStatus(String userId,int status);

}
