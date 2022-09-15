package furiends.backend.dto;


import java.util.List;

public class AdoptionProcedure {
    private List<AdoptionProcedureStep> steps;

    public List<AdoptionProcedureStep> getSteps() {
        return steps;
    }

    public void setSteps(List<AdoptionProcedureStep> steps) {
        this.steps = steps;
    }
}
