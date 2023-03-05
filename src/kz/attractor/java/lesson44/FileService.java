package kz.attractor.java.lesson44;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileService {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//    public static List<Day> readDaysFile() {
//        String json = "";
//        List<Day> days = new ArrayList<>();
//        try{
//            Path path = Paths.get("base/Days.json");
//            json = Files.readString(path);
//            for(int i = 0; i < GSON.fromJson(json, Day[].class).length;i++){
//                days.add(GSON.fromJson(json, Day[].class)[i]);
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        return days;
//    }
//    public static void  writeDaysFile(List<Day> days){
//        String json = GSON.toJson(days);
//        try{
//            Path path = Paths.get("base/Days.json");
//            Files.write(path, json.getBytes());
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    public static List<Candidate> readCandidatesFile() {
        String json = "";
        List<Candidate> candidates = new ArrayList<>();
        try{
            Path path = Paths.get("data/candidates.json");
            json = Files.readString(path);
            candidates.addAll(Arrays.asList(GSON.fromJson(json, Candidate[].class)));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return candidates;
    }
    public static void writeCandidatesFile(List<Candidate> candidates){
        String json = GSON.toJson(candidates);
        try{
            Path path = Paths.get("data/candidates.json");
            Files.write(path, json.getBytes());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
