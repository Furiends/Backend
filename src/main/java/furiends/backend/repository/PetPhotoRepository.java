package furiends.backend.repository;

import furiends.backend.model.PetPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetPhotoRepository extends JpaRepository<PetPhoto, String> {

}
