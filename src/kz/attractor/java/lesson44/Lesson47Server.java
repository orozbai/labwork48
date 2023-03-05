package kz.attractor.java.lesson44;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import kz.attractor.java.server.BasicServer;
import kz.attractor.java.server.ContentType;
import kz.attractor.java.server.ResponseCodes;
import kz.attractor.java.utils.Utils;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class Lesson47Server extends BasicServer {
    private final static Configuration freemarker = initFreeMarker();
    private static Candidate candidate;
    public Lesson47Server(String host, int port) throws IOException, SQLException {
        super(host, port);
        registerGet("/",this::candidatesGet);
        registerPost("/vote",this::candidatesPost);
        registerGet("/thankyou",this::thankyouHandler);
        registerGet("/votes",this::votesGet);
    }
    private void votesGet(HttpExchange exchange){
        renderTemplate(exchange, "votes.ftlh", new CandidatesDataModel());
    }
    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template temp = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
                temp.process(dataModel, writer);
                writer.flush();
                var data = stream.toByteArray();
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
    private void thankyouHandler(HttpExchange exchange) {
        renderTemplate(exchange, "thankyou.html", new SingleDataModel(candidate));
    }
    private static Configuration initFreeMarker() {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
            cfg.setDirectoryForTemplateLoading(new File("data"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
            return cfg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void candidatesPost(HttpExchange exchange) {
        try{
            String raw = getBody(exchange);
            List<Optional<String>> parsed = Utils.parseInputEncoded(raw,"&");
            List<String> stats = new ArrayList<>();
                for (Optional<String> s : parsed) {
                    stats.add(s.toString().substring(s.toString().indexOf("=") + 1, s.toString().indexOf("]")));
                }
                List<Candidate> candidates = FileService.readCandidatesFile();
            for(int i = 0; i < FileService.readCandidatesFile().size(); i++){
                if (FileService.readCandidatesFile().get(i).getName().equals(stats.get(0))){
                    candidates.get(i).setVotes();
                    FileService.writeCandidatesFile(candidates);
                    candidate = candidates.get(i);
                }
            }
            for (int i = 0; i < FileService.readCandidatesFile().size(); i++){
                candidates.get(i).setPercentage();
                FileService.writeCandidatesFile(candidates);
            }
            redirect303(exchange, "/thankyou");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void candidatesGet(HttpExchange exchange){
        renderTemplate(exchange, "candidates.ftlh", new CandidatesDataModel());
    }
}
