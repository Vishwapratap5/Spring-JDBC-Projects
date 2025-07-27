package com.guru.DAO;

import com.guru.Model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Boolean addTask(Task task) {
        String sql="INSERT INTO TaskInfo (task_name,task_Description, priority, task_due_time, status) VALUES (?,?,?,?,?);";
        PreparedStatementSetter ps=pss->{
            pss.setString(1, task.getTaskName());
            pss.setString(2, task.getTaskDescription());
            pss.setString(3, task.getTaskPriority());
            pss.setTimestamp(4,task.getTaskDueDate());
            pss.setString(5,task.getTaskStatus());
        };
        int result=jdbcTemplate.update(sql,ps);
        if(result>0){
            return true;
        }
            return false;
    }

    public Boolean deleteTask(int taskID) {
        String sql="DELETE FROM TaskInfo WHERE task_id=?;";
        int result=jdbcTemplate.update(sql,taskID);
        if(result>0){
            return true;
        }
        return false;
    }

    public Boolean updateTask(Task task, int taskID) {
        String sql="update TaskInfo set task_name=?,task_Description=?, priority=?, task_due_time=?, status=? where task_id=?;";
        PreparedStatementSetter ps=pss->{
            pss.setString(1, task.getTaskName());
            pss.setString(2, task.getTaskDescription());
            pss.setString(3, task.getTaskPriority());
            pss.setTimestamp(4,task.getTaskDueDate());
            pss.setString(5,task.getTaskStatus());
            pss.setInt(6,taskID);
        };
        int result=jdbcTemplate.update(sql,ps);
        if(result>0){
            return true;
        }
        return false;
    }

    public Task getTaskByID(int taskID) {
        String sql="Select * from TaskInfo where task_id=?;";
        RowMapper<Task> taskRowMapper=(rs,rownum)->{
            Task task=new Task();
            task.setTaskID(rs.getInt("task_id"));
            task.setTaskName(rs.getString("task_name"));
            task.setTaskDescription(rs.getString("task_description"));
            task.setTaskPriority(rs.getString("priority"));
            task.setTaskDueDate(rs.getTimestamp("task_due_time"));
            task.setTaskStatus(rs.getString("status"));
            return task;
        };
        Task task=jdbcTemplate.queryForObject(sql,taskRowMapper,taskID);
        return task;
    }

    public List<Task> getAllTasks() {
        String sql = "SELECT * FROM TaskInfo";

        return jdbcTemplate.query(sql, (rs, rownum) -> {
            Task task = new Task();
            task.setTaskID(rs.getInt("task_id"));
            task.setTaskName(rs.getString("task_name"));
            task.setTaskDescription(rs.getString("task_description"));
            task.setTaskPriority(rs.getString("priority"));
            task.setTaskDueDate(rs.getTimestamp("task_due_time"));
            task.setTaskStatus(rs.getString("status"));
            return task;
        });
    }

    public Boolean deleteCompletedTask() {
        String sql="DELETE FROM TaskInfo WHERE  status=?;";
        int result=jdbcTemplate.update(sql,"Completed");
        if(result>0){
            return true;
        }
        return false;
    }

    public List<Task> getExpiredTask() {
        String sql = "SELECT * FROM TaskInfo WHERE task_due_time < CURRENT_TIMESTAMP AND status != 'Completed'";

        return jdbcTemplate.query(sql, (rs, rownum) -> {
            Task task = new Task();
            task.setTaskID(rs.getInt("task_id"));
            task.setTaskName(rs.getString("task_name"));
            task.setTaskDescription(rs.getString("task_description"));
            task.setTaskPriority(rs.getString("priority"));
            task.setTaskDueDate(rs.getTimestamp("task_due_time"));
            task.setTaskStatus(rs.getString("status"));
            return task;
        });
    }

    public Boolean deleteExpiredTask() {
        String sql="DELETE FROM TaskInfo WHERE  task_due_time < CURRENT_TIMESTAMP AND status != 'Completed'";
        int result=jdbcTemplate.update(sql);
        if(result>0){
            return true;
        }
        return false;
    }

    public List<Task> getIncompleteTaskByOrder() {
        String sql = "SELECT * FROM TaskInfo WHERE task_due_time > CURRENT_TIMESTAMP AND status != 'Completed' order by task_due_time ASC;";

        return jdbcTemplate.query(sql, (rs, rownum) -> {
            Task task = new Task();
            task.setTaskID(rs.getInt("task_id"));
            task.setTaskName(rs.getString("task_name"));
            task.setTaskDescription(rs.getString("task_description"));
            task.setTaskPriority(rs.getString("priority"));
            task.setTaskDueDate(rs.getTimestamp("task_due_time"));
            task.setTaskStatus(rs.getString("status"));
            return task;
        });
    }
}
