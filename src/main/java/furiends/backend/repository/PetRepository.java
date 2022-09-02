package furiends.backend.repository;

import furiends.backend.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, String> {

    @Query("SELECT p from pet p WHERE p.organizationId = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByOrganization(String organizationId);

    @Query("SELECT p FROM pet p WHERE p.organizationId = ?2 AND p.isPublished = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByPostStatus(boolean isPublished, String organizationId);

    @Query("SELECT p FROM pet p WHERE p.organizationId = ?2 AND p.isAdopted = ?1 ORDER BY p.postUpdateTime")
    List<Pet> findAllByAdoptionStatus(boolean isAdopted, String organizationId);

}