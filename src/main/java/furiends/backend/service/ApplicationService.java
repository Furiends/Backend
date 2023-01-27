package furiends.backend.service;

import furiends.backend.model.Application;
import furiends.backend.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ApplicationService {
    @Resource
    private ApplicationRepository applicationRepository;

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
}
