package kz.attractor.java.lesson44;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Candidate {
    private String name;
    private String photo;
    private int votes;
    private int percentage;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage() {
        int sum = 0;
        for (int i = 0; i < FileService.readCandidatesFile().size(); i++){
            sum+=FileService.readCandidatesFile().get(i).getVotes();
        }
        System.out.println(sum);
        System.out.println(getVotes());
        this.percentage = Math.round(getVotes()/sum) * 100;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes() {
        this.votes += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Candidate(String name, String photo) {
        this.name = name;
        this.photo = photo;
        this.votes = 0;
        this.percentage = 0;
    }
}

