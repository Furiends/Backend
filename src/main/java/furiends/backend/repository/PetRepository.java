package furiends.backend.repository;

import furiends.backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("SELECT p from pet p WHERE p.organizationId = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByOrganization(String organizationId);

    @Query("SELECT p FROM pet p WHERE p.isPublished = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByPublishStatus(boolean isPublished);

    @Query("SELECT p FROM pet p WHERE p.organizationId = ?1 AND p.isPublished = ?2 ORDER BY p.postUpdateTime")
    List<Pet> findAllByPublishStatusOrg(String organizationId, boolean isPublished);

    @Query("SELECT p FROM pet p WHERE p.isAdopted = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByAdoptionStatus(boolean isAdopted);

    @Query("SELECT p FROM pet p WHERE p.organizationId = ?1 AND p.isAdopted = ?2 ORDER BY p.postUpdateTime")
    List<Pet> findAllByAdoptionStatusOrg(String organizationId, boolean isAdopted);

}