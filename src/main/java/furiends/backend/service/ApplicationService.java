package furiends.backend.service;

import furiends.backend.model.Application;
import furiends.backend.model.Pet;
import furiends.backend.repository.ApplicationRepository;
import furiends.backend.repository.PetRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    public Application updateApplicationStatus(String ApplicationId, int status){
        Application application = findApplicationById(ApplicationId).get();

        HashSet<Integer> allStatus = new HashSet<>(Arrays.asList(0, 1, 2, 3));
        if (!allStatus.contains(status)){
            throw new RuntimeException("invalid status input");
        }

        if (status == 2) {
            String petId = application.getPetId();
            Pet pet = petRepository.findById(petId).get();
            List<Application> applications = findApplicationByPetId(petId);
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

            application.setApplicationStatus(2);
            applicationRepository.save(application);
        } else {
            System.out.println(status);
            application.setApplicationStatus(status);
            applicationRepository.save(application);
        }

        return application;
    }

    public Application updateRejectReason(String ApplicationId,  String rejectReason){
        Application application = applicationRepository.findApplicationByApplicationId(ApplicationId);
        application.setRejectionReason(rejectReason);
        application.setUpdatedTime();
        return applicationRepository.save(application);
    }

}
