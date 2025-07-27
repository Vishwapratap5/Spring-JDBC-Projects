package com.guru.Controller;

import com.guru.Model.Task;
import com.guru.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService){
        this.taskService=taskService;
    } ;
    public void addTask(Task task){
        Boolean result=taskService.addtask(task);
        if(result){
            System.out.println("Added task successfully!");
        }else{
            System.out.println("Failed to add task!");
        }
    }

    public void deleteTask(int taskID) {
        Boolean result=taskService.deleteTask(taskID);
        if(result){
            System.out.println("Task Deleted successfully!");
        }else{
            System.out.println("Failed to Delete task!");
        }
    }

    public void updateTask(Task task,int taskID) {
        Boolean result=taskService.updateTask(task,taskID);
        if(result){
            System.out.println("Task Updated successfully!");
        }else{
            System.out.println("Failed to Update task!");
        }
    }

    public void getTaskbyID(int taskID) {
        Task gotTask=taskService.getTaskByID(taskID);
        System.out.println("Task Details:");
        System.out.println(gotTask);
    }

    public List<Task> getAllTasks() {
        List<Task> tasklist=taskService.getAllTasks();
        if(tasklist.size()==0){
            System.out.println("No tasks found!");
            return null;
        }
        return tasklist;
    }

    public Boolean deleteCompletedTask() {
        return taskService.deleteCompletedTask();
    }

    public List<Task> getExpiredTask() {
        return taskService.getExpiredTask();
    }

    public Boolean deleteExpiredTask() {
        return taskService.deleteExpiredTask();
    }

    public List<Task> getIncompleteTaskByOrder() {
        return taskService.getIncompleteTaskByOrder();
    }
}
