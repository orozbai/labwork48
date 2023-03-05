package kz.attractor.java.lesson44;

public class SingleDataModel {
    private Candidate candidate;

    public SingleDataModel(Candidate candidate) {
        this.candidate = candidate;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
