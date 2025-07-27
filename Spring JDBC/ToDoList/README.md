# 📝 To-Do Management System – Spring JDBC Based

A feature-rich **console-based To-Do Task Manager** application built using **Java**, **Spring Core**, and **Spring JDBC**, allowing users to manage daily tasks with deadlines, priorities, and status updates. Ideal for beginners to demonstrate Spring Framework skills and layered architecture.

---

## 📌 Features

- ✅ **Add a Task** – Create a new task with name, description, priority, and due time
- 🗑 **Delete a Task** – Remove a task by ID
- ♻️ **Update Task** – Modify task details
- 🔍 **Get Task by ID** – View specific task information
- 📋 **View All Tasks** – See all tasks in tabular form
- ✔️ **Remove Completed Tasks** – Clean the completed ones in one click
- ⏰ **View Expired Tasks** – List tasks past their due date but not completed
- 🧹 **Delete Expired Tasks** – Bulk delete all overdue incomplete tasks
- 📅 **Sort by Deadline** – Show ongoing tasks sorted by due time

---

## ⚙️ Tech Stack

| Layer         | Technology                |
|---------------|----------------------------|
| Language      | Java 17+                   |
| Framework     | Spring Core + Spring JDBC  |
| Database      | MySQL                      |
| Build Tool    | Maven                      |
| ORM/Access    | JdbcTemplate + DAO Layer   |
| Config Style  | Java-Based (`@Configuration`, `@Bean`) |
| Dependency Injection | `@Autowired`, Constructor Injection |

---

## 🗃️ Project Structure

src/
└── com.guru/
├── Configurations/ # Java config (SpringConfig.java)
├── Controller/ # Handles user interaction logic
├── Service/ # Business logic layer
├── DAO/ # Data access using JdbcTemplate
└── Model/ # Task POJO (Task.java)
resources/
└── Application.properties # DB credentials