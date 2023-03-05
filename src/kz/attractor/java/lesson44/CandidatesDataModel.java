package kz.attractor.java.lesson44;

import java.util.List;

public class CandidatesDataModel {
    private List<Candidate> candidates;

    public CandidatesDataModel() {
        this.candidates = FileService.readCandidatesFile();
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }
}
