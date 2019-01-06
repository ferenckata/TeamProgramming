package backend;

import java.util.ArrayList;

public class Task {

    private ArrayList<Integer> memberIDs;
    private int ID;
    private String name;
    private int startWeek;
    private int endWeek;
    private double cost;
    private double budget;
    private boolean isCompleted;
    private String status;

    public Task(){
        this.ID = 0;
        this.memberIDs = new ArrayList<>();
        this.startWeek = 0;
        this.endWeek = 0;
        this.cost = 0;
        this.budget = 0;
        this.isCompleted = false;
        this.status = "In progress";
    }


    public Task(int ID,String name, int startWeek, int endWeek, double cost, double budget, boolean isCompleted) {
        this.ID = ID;
        this.memberIDs = new ArrayList<>();
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.budget = budget;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }
    }


    // task has only the name of the memberNames not the memberNames themselves, otherwise infinite recursion
    public Task(int ID,int memberID,String name, int startWeek, int endWeek, double cost, double budget, boolean isCompleted){
        this.ID = ID;
        this.memberIDs = new ArrayList<>();
        this.memberIDs.add(memberID);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.budget = budget;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }

    }

    public void addMember(int memberID){
        this.memberIDs.add(memberID);
    }
    public void removeMember(int memberID){
        this.memberIDs.remove(memberID);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (completed){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
    public String getStatus(){
        return this.status;
    }

    public ArrayList<Integer> getListOfMemberIDs(){
        return this.memberIDs;
    }


    public boolean compare(Integer taskID){
        if(this.ID ==taskID){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Task){
            Task otherTask  = (Task) obj;
            if(otherTask.getID() == this.ID){
                return true;
            }
        }
        return false;
    }
}
