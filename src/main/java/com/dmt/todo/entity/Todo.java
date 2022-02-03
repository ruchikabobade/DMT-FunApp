package com.dmt.todo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="todo_id")
    Long TodoId;

    @Column(name="name")
    String name;

    @Column(name="description")
    String description;

    @Column(name="priority")
    int priority;

    @Column(name="due_date")
    Date dueDate;

    @Column(name="completion_status")
    String completionStatus;

    @Column(name="completion_date")
    Date completionDate;

    public Todo() {};

    public Todo(String name, String description, int priority, Date dueDate, String completionStatus, Date completionDate) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completionStatus = completionStatus;
        this.completionDate = completionDate;
    }

    public String getName() {
        return name;
    }

    public Long getTodoId() {
        return TodoId;
    }

    public String getDescription() {
        return description;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public void setCompletionStatus(String completed) {
        this.completionStatus = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTodoId(Long todoId) {
        TodoId = todoId;
    }
}
