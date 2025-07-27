package com.guru;

import com.guru.Controller.TaskController;
import com.guru.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ToDoApp {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.guru.Configurations");
        TaskController controller = context.getBean(TaskController.class);

        int choice;
        do {
            System.out.println("\n===== TO-DO MANAGEMENT MENU =====");
            System.out.println("1. Add Task");
            System.out.println("2. Delete Task");
            System.out.println("3. Update Tasks");
            System.out.println("4. Get Task by ID");
            System.out.println("5.See All Tasks");
            System.out.println("6. Remove Completed Tasks");
            System.out.println("7. View Expired tasks");
            System.out.println("8. Delete Expired Tasks");
            System.out.println("9. Order incomplete/onGoing task by DeadLine ");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    sc.nextLine(); // consume newline
                    Task task = new Task();
                    System.out.print("Enter Task Name: ");
                    task.setTaskName(sc.nextLine());
                    System.out.print("Enter Priority (Low/Medium/High): ");
                    task.setTaskPriority(sc.nextLine());
                    System.out.println("Enter Description: ");
                    task.setTaskDescription(sc.nextLine());
                    System.out.println("Enter Due Date:[YYYY-MM-DD HH:MM]: ] ");
                    task.setTaskDueDate(Timestamp.valueOf(LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    System.out.println("enter Status [Completed/OnGoing/NotStarted]");
                    task.setTaskStatus(sc.nextLine());
                    controller.addTask(task);
                }
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Enter Task ID to Delete: ");
                    int taskID = sc.nextInt();
                    controller.deleteTask(taskID);
                }
                case 3 -> {
                    sc.nextLine();
                    System.out.println("Enter Task ID to Update: ");
                    int taskID = sc.nextInt();
                    sc.nextLine();
                    Task task = new Task();
                    System.out.print("Enter new Task Name: ");
                    task.setTaskName(sc.nextLine());
                    System.out.print("Enter new Priority (Low/Medium/High): ");
                    task.setTaskPriority(sc.nextLine());
                    System.out.println("Enter new Description: ");
                    task.setTaskDescription(sc.nextLine());
                    System.out.println("Enter new Due Date:[YYYY-MM-DD HH:MM]: ");
                    task.setTaskDueDate(Timestamp.valueOf(LocalDateTime.parse(sc.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                    System.out.println("enter new Status [Completed/OnGoing/NotStarted]");
                    task.setTaskStatus(sc.nextLine());
                    controller.updateTask(task,taskID);
                }
                case 4 -> {
                    sc.nextLine();
                    System.out.println("Enter Task ID to Get that task: ");
                    int taskID = sc.nextInt();
                    controller.getTaskbyID(taskID);
                }
                case 5 -> {
                    sc.nextLine();
                    System.out.println("Fetching Data .... please wait...");
                    List<Task> tasklist=controller.getAllTasks();
                    if(tasklist.size()>0){
                        for (Task task : tasklist) {
                            System.out.println(task);
                        }
                        System.out.println("Data Fetched Successfully...!");
                    }else{
                        System.out.println("data not found!");
                    }

                }
                case 6 -> {
                    sc.nextLine();
                    System.out.println("Deleting Completed Tasks... ");
                    Boolean result=controller.deleteCompletedTask();
                    if(result){
                        System.out.println("Data Deleted Successfully!");
                    }else{
                        System.out.println("Something went wrong!/ NO Completed tasks..!");
                    }
                }
                case 7 -> {
                    sc.nextLine();
                    System.out.println("Fetching Expired Tasks... ");
                    List<Task> result=controller.getExpiredTask();
                    if(result.isEmpty()){
                        System.out.println("Something went wrong!/ NO Completed tasks..!");
                    }else{
                        for(Task task : result){
                            System.out.println(task);
                        }
                    }
                }
                case 8 -> {
                    sc.nextLine();
                    System.out.println("Deleting Expired Tasks... ");
                    Boolean result=controller.deleteExpiredTask();
                    if(result){
                        System.out.println("Data Deleted Successfully!");
                    }else{
                        System.out.println("Something went wrong!/ NO Expired tasks..!");
                    }
                }
                case 9 -> {
                    sc.nextLine();
                    System.out.println("Fetching incomplete/ongoing Tasks by Order... ");
                    List<Task> result=controller.getIncompleteTaskByOrder();
                    if(!result.isEmpty()){
                        for(Task task : result){
                            System.out.println(task);
                        }
                    }else{
                        System.out.println("Something went wrong!/ NO incomplete tasks..!");
                    }
                }
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 10);
    }
}
