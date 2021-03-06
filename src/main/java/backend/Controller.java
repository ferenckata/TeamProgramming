package backend;

import backend.IO.JSONReader;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private static ArrayList<Project> projects = new ArrayList<Project>();
    private static JSONReader JSONwr = new JSONReader();

    public static Project createNewProject(String name, double budget, int startWeek){
        Project newProject = new Project(name,budget,startWeek);
        projects.add(newProject);
        return newProject;
    }

    public static Project importProject(String path) throws IOException {
        Project readInProject = JSONwr.readProject(path);
        return readInProject;
    }

    public static void saveProject(Project project, String path) throws IOException {
        JSONwr.writeProject(project,path);
    }

    /*public static void main(String[] args) throws IOException {

        Project testProject = new Project();
        testProject.createMember("Ben","11", 200);
        testProject.createMember("John","10", 200);
        testProject.createTask(12,20,2000,false);
        testProject.setBudget(90000);

        saveProject(testProject,"target/testProject.json");
        Project readInProject = importProject("target/testProject.json");

        System.out.println(readInProject.getTasks());

    }*/

}
