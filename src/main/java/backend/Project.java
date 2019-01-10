package backend;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
    private String name;
    private ArrayList<Member> members;
    private ArrayList<Task> tasks;
    private ArrayList<Risk> risks;
    private Calculations schedule;
    private Factory factory;
    private double budget;
    private Calendar calendar;
    protected static int currentWeek;

    public Project() {
        this.name = "";
        this.budget = 0;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
        this.calendar = new GregorianCalendar();
    }

    public Project(String name, double budget) {
        this.budget = budget;
        this.name = name;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
        this.calendar = new GregorianCalendar();
    }

    // Create and Remove methods ------------------------------------------

<<<<<<< HEAD
    public void createMember(String name, String Id, double salary) {
        for(Member member : members){
            if(Id.equals(member.getID())){
                return;
            }
        }
        members.add(this.factory.createMember(name, Id, salary));
=======
    public void createMember(String name, int ID, double salary) {
        members.add(this.factory.createMember(name, ID, salary));
>>>>>>> taskRealTiming
    }

    // when a member is removed, remove their name from all tasks they were involved in
    public void removeMember(Member memberToRemove) {

        // remove the member from the list
        members.remove(memberToRemove);

        // remove the member id from the task they were involved
        // find the task IDs
        ArrayList<Integer> removedMembersTasks = memberToRemove.getTasks();
        // find the task with the ID
        for (Integer task: removedMembersTasks) {
            Task taskWithLessMember = searchTask(task);
            if(taskWithLessMember != null){
                taskWithLessMember.removeMember(memberToRemove.getID(), memberToRemove.getName());
            }
        }
    }
    public int getCurrentWeek(){
        return currentWeek;
    }
    public void setCurrentWeek(int week){
        currentWeek = week;
    }

    public void createTask(int taskID,String name, int startWeek, int endWeek, double cost, double budget, boolean completed) {
        tasks.add(factory.createTask(taskID,name, startWeek, endWeek, cost,budget, completed));
    }

    public void createTask(int taskID , String name, int memberID, String memberName, int startWeek, int endWeek, double cost, double budget, boolean completed) {
        Task newTask = factory.createTask(taskID, name, memberID, memberName, startWeek, endWeek, cost, budget, completed);
        tasks.add(newTask);
        // the section below could be rewritten with search through member ID
        for (Member member: this.members) {
            if(member.compare(memberID)){
                member.addTask(taskID);
            }
        }
    }

    public void createTask() {
        tasks.add(factory.createTask());
    }

    public void removeTask(Task taskToRemove) {
        // find the people who had this task
        ArrayList<Integer> membersToAdjust = taskToRemove.getListOfMemberIDs();
        for (int memberToRemoveID: membersToAdjust) {
            for (Member member: members) {
                if(member.compare(memberToRemoveID)){
                    // remove the taskID from the people who were involved
                    member.removeTask(taskToRemove.getID());
                }
            }
        }
        // remove the task fromt he task list
        tasks.remove(taskToRemove);
    }


    public void createRisk(String name, double probability, double impact) {
        risks.add(factory.createRisk(name, probability, impact));
    }

    public void removeRisk(Risk riskToRemove) {
        risks.remove(riskToRemove);
    }

    // Search methods ---------------------------------------------
    public Task searchTask(int taskID){
        for (Task task:tasks) {
            if(task.compare(taskID)){
                return task;
            }
        }
        return null;
    }

    public Member searchMember(int memberID){
        for (Member member:members) {
            if(member.compare(memberID)){
                return member;
            }
        }
        return null;
    }

    // Getters and Setters -------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Risk> getRisks() {
        return risks;
    }

    public void setRisks(ArrayList<Risk> risks) {
        this.risks = risks;
    }

    public Calculations getSchedule() {
        return schedule;
    }

    public void setSchedule(Calculations schedule) {
        this.schedule = schedule;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    // Create new task and member ID -----------------------------------------------


    @JsonIgnore
    public int getHighestTaskID(){
        int maxTaskID = 0;
        for (Task task: this.tasks) {
            if(task.getID() > maxTaskID){
                maxTaskID = task.getID();
            }
        }
        return maxTaskID;
    }


    @JsonIgnore
    public int getHighestMemberID(){
        int maxMemberID = 0;
        for (Member member: members) {
            if(member.getID() > maxMemberID){
                maxMemberID = member.getID();
            }
        }
        return maxMemberID;
    }

}