package com.guru.Service;

import com.guru.DAO.TaskDAO;
import com.guru.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    TaskDAO taskDAO;
    @Autowired
    public TaskService (TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
    public Boolean addtask(Task task) {
        if(task.getTaskPriority()==null||task.getTaskPriority()==""){
            return false;
        }
        if(task.getTaskStatus()==null||task.getTaskStatus()==""){
            return false;
        }
        return taskDAO.addTask(task);
    }

    public Boolean deleteTask(int taskID) {
        if(taskID<=0){
            return false;
        }
        return taskDAO.deleteTask(taskID);
    }

    public Boolean updateTask(Task task,int taskID) {
        if(task.getTaskPriority()==null||task.getTaskPriority()==""){
            return false;
        }
        if(task.getTaskStatus()==null||task.getTaskStatus()==""){
            return false;
        }
        if(taskID<=0){
            return false;
        }
        return taskDAO.updateTask(task,taskID);
    }

    public Task getTaskByID(int taskID) {
        if(taskID<=0){
            return null;
        }
        return taskDAO.getTaskByID(taskID);
    }

    public List<Task> getAllTasks() {
        List<Task> tasklist=taskDAO.getAllTasks();
        if(tasklist.size()==0){
            System.out.println("No tasks found!");
            return null;
        }
        return tasklist;
    }

    public Boolean deleteCompletedTask() {
        return taskDAO.deleteCompletedTask();
    }

    public List<Task> getExpiredTask() {
        return taskDAO.getExpiredTask();
    }

    public Boolean deleteExpiredTask() {
        return taskDAO.deleteExpiredTask();
    }

    public List<Task> getIncompleteTaskByOrder() {
        return taskDAO.getIncompleteTaskByOrder();
    }
}
