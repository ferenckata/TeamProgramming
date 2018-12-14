package backend.IO;

import backend.Project;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONReader {

    private ObjectMapper objectMapper;

    public JSONReader(){
        objectMapper = new ObjectMapper();
    }

   public void writeProject(Project project,String path) throws IOException {
        objectMapper.writeValue(new File(path), project);
    }

    public Project readProject(String path) throws IOException {
        Project project = objectMapper.readValue(new File(path),Project.class);
        return project;
    }

}
