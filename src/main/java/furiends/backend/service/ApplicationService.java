package furiends.backend.service;

import furiends.backend.model.Application;
import furiends.backend.model.Pet;
import furiends.backend.repository.ApplicationRepository;
import furiends.backend.repository.PetRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Resource
    private ApplicationRepository applicationRepository;
    @Resource
    private PetRepository petRepository;

    public List<Application> findApplicationByPetId(String PetId){
        return applicationRepository.findApplicationByPetId(PetId);
    }

    public List<Application> findApplicationByUserId(String UserId){
        return applicationRepository.findApplicationByUserId(UserId);
    }

    public List<Application> listApplicationWithStatus(String UserId, int status){
        return applicationRepository.listApplicationWithStatus(UserId, status);
    }

    public List<Application> findAllApplicantsByOrganization(String organizationId) {
        return applicationRepository.findAllApplicantsByOrganization(organizationId);
    }

    public Optional<Application> findApplicationById(String id) {
        return applicationRepository.findById(id);
    }

    // 2: Complete; 3: Rejected;
    public void updateApplicationStatus(String ApplicationId, int status){
        Application application = findApplicationById(ApplicationId).get();

        if (status == 3) {
            application.setApplicationStatus(3);
            applicationRepository.save(application);
        } else if (status == 2) {
            String petId = application.getPetId();
            Pet pet = petRepository.findById(petId).get();
            List<Application> applications = findApplicationByPetId(petId);

            application.setApplicationStatus(2);
            applicationRepository.save(application);
            //update pet isAdopted
            pet.setIsAdopted(true);
            petRepository.save(pet);
            //update other applications status to rejected
            for(Application app: applications){
                if (app.getApplicationId() == ApplicationId){
                    continue;
                }
                app.setApplicationStatus(3);
                applicationRepository.save(app);
            }
        }
    }
}
