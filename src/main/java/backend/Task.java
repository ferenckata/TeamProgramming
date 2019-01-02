package backend;

import java.util.ArrayList;

public class Task {

    private ArrayList<Member> members;
    private String name;
    private int startWeek;
    private int endWeek;
    private double cost;
    private boolean isCompleted;
    private String status;

    public Task(){
        this.members = new ArrayList<>();
        this.startWeek = 0;
        this.endWeek = 0;
        this.cost = 0;
        this.isCompleted = false;
        this.status = "In progress";
    }


    public Task(String name, int startWeek, int endWeek, double cost, boolean isCompleted) {
        this.members = new ArrayList<>();
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }
    }


    public Task(Member member,String name, int startWeek, int endWeek, double cost, boolean isCompleted){
        this.members.add(member);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }

    }
    public void addMember(Member member){
        this.members.add(member);
    }
    public void removeMember(Member member){
        this.members.remove(member);
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

    public String getMembers(){
        String nameList = "";
        int memberNum = 0;
        for (Member member: this.members) {
            if (memberNum >0){
                nameList += ", ";
            }
            nameList += member.getName() ;
            memberNum += 1 ;
        }
        return nameList;
    }

    public ArrayList<Member> getListOfMembers(){
        return this.members;
    }
}
