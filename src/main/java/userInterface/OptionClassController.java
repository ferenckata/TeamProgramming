package userInterface;

import backend.Member;
import backend.Project;
import backend.Risk;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OptionClassController implements Initializable {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private Navigation goBack = new Navigation();

    @FXML
    private TextField startWeekInput;

    @FXML
    private TextField cost;

    @FXML
    private TextField budget;

    @FXML
    private RadioButton completeRadioBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField endWeekInput;

    @FXML
    private RadioButton notCompleteRadioBtn;

    @FXML
    private TableView<Risk> table;

    @FXML
    private TextField budgetTextfield;

    @FXML
    private Button deleteRiskBtn;

    @FXML
    private TableColumn<Risk, String> probColumn;

    @FXML
    private TextField probInput;

    @FXML
    private Button backBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    private TableColumn<Risk, Double> impactColumn;

    @FXML
    private TextField riskInput;

    @FXML
    private TextField impactInput;

    @FXML
    private Button enterBtn;

    @FXML
    private TableColumn<Risk, Double> riskColumn;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, Integer> startWeekColumn;

    @FXML
    private  TableColumn<Task, Integer> endWeekColumn;

    @FXML
    private TextField taskNameInput;

    @FXML
    private Button deleteTaskBtn;

    @FXML
    private ChoiceBox<Member> memberChoice;

    @FXML
    private Label budgetLabel;

    private ArrayList<Risk> risks;

    private ArrayList<Task> tasks;

    private ArrayList<Member> members;

    public void initData(Project project){
        try {
            this.project = project;

            budgetLabel.setText(Double.toString(project.getBudget()));

            members = new ArrayList<Member>();
            members = project.getMembers();

            risks = new ArrayList<Risk>();
            risks = project.getRisks();

            tasks = new ArrayList<Task>();
            tasks = project.getTasks();

            for (Member member:members) {

                memberChoice.getItems().add(member);

            }


            riskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            impactColumn.setCellValueFactory(new PropertyValueFactory<>("impact"));
            probColumn.setCellValueFactory(new PropertyValueFactory<>("probability"));

            taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            startWeekColumn.setCellValueFactory(new PropertyValueFactory<>("startWeek"));
            endWeekColumn.setCellValueFactory(new PropertyValueFactory<>("endWeek"));


            taskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ObservableList<Risk> currentRisks = FXCollections.observableArrayList();
            for (Risk risk : risks) {
                currentRisks.add(risk);
            }

            ObservableList<Task> currentTasks = FXCollections.observableArrayList();
            for (Task task: tasks){
                currentTasks.add(task);
            }

            taskTable.setItems(currentTasks);
            table.setItems(currentRisks);

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void addRiskBtnClicked(ActionEvent event) throws IOException {

        String riskName = riskInput.getText();
        double prob = Double.parseDouble(probInput.getText());
        double impact = Double.parseDouble(impactInput.getText());


        project.createRisk(riskName, prob, impact);

        ObservableList<Risk> currentRisks = FXCollections.observableArrayList();
        for (Risk risk : risks) {
            currentRisks.add(risk);
        }
        table.setItems(currentRisks);

        riskInput.clear();
        probInput.clear();
        impactInput.clear();


    }
    @FXML
    void deleteRiskBtnClicked(ActionEvent event){
        try {
            ObservableList<Risk> riskSelected, allRisks;
            allRisks = table.getItems();
            riskSelected = table.getSelectionModel().getSelectedItems();

            project.removeRisk(table.getSelectionModel().getSelectedItem());

            riskSelected.forEach(allRisks::remove);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void confirmTask(ActionEvent event) {

        Member memberToAdd = memberChoice.getSelectionModel().getSelectedItem();

        // increase the task ID with one
        int taskID = project.getHighestTaskID() + 1;

        // adjust the task week to project week
        int startWeek = project.adjustWeek(Integer.parseInt(this.startWeekInput.getText()));
        int endWeek = project.adjustWeek(Integer.parseInt(this.endWeekInput.getText()));

        double cost = Double.parseDouble(this.cost.getText());
        double budget = Double.parseDouble(this.budget.getText());
        String taskName = taskNameInput.getText();
        boolean completed;

        ToggleGroup group = new ToggleGroup();

        completeRadioBtn.setToggleGroup(group);
        notCompleteRadioBtn.setToggleGroup(group);

        if (completeRadioBtn.isSelected()){
            completed = true;
        }else{
            completed = false;
        }

        if (memberToAdd == null) {
            project.createTask(taskID,taskName, startWeek, endWeek, cost, budget, completed);

        }else{
            project.createTask(taskID, taskName, memberToAdd.getID(), memberToAdd.getName(), startWeek, endWeek, cost, budget, completed);
        }
        ObservableList<Task> currentTasks = FXCollections.observableArrayList();

        for (Task task: tasks){
            currentTasks.add(task);
        }

        taskTable.setItems(currentTasks);

        this.taskNameInput.clear();
        this.startWeekInput.clear();
        this.endWeekInput.clear();
        this.cost.clear();
        this.budget.clear();
        this.completeRadioBtn.setSelected(false);
        this.notCompleteRadioBtn.setSelected(false);

    }

    @FXML
    void detailsBtnClicked(ActionEvent event) throws Exception{

        Task taskSelected;
        taskSelected = taskTable.getSelectionModel().getSelectedItem();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/detailsView.fxml")));
        Parent detailsView = loader.load();

        Scene detailsViewScene = new Scene(detailsView, 500, 300);
        DetailsViewController controller = loader.getController();

        controller.initData(taskSelected, project);
        Stage window = new Stage();
        window.setScene(detailsViewScene);
        window.showAndWait();


    }
    @FXML
    void deleteTask(ActionEvent event){
        try {
            ObservableList<Task> taskSelected, allTasks;
            allTasks = taskTable.getItems();
            taskSelected = taskTable.getSelectionModel().getSelectedItems();

            project.removeTask(taskTable.getSelectionModel().getSelectedItem());

            taskSelected.forEach(allTasks::remove);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException{
        goBack.toProjectOverview(event, project);
    }

    @FXML
    void enterBtnClicked(ActionEvent event) {
        project.setBudget(Double.parseDouble(budgetTextfield.getText()));
        budgetLabel.setText(Double.toString(project.getBudget()));
        Stage window = new Stage();
        window.setTitle("Budget Changed!");
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);

        Button okBtn = new Button("OK");
        Label label = new Label("Budget is now: " + project.getBudget() + " Riksdaler!");

        okBtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        budgetTextfield.clear();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}