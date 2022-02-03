package com.dmt.todo.model;

public class ToDoItem {
    private Long ID;
    private String Name;
    private String description;
    private int priority;
    private String dueDate;
    private String completionStatus;
    private String completionDate;

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCompletionDate() {
        return completionDate;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setCompletionDate(String completionDate) {
        this.completionDate = completionDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(String completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "ID='" + ID+ "\'', Name='" + Name + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", completionStatus=" + completionStatus +
                ", completionDate='" + completionDate + '\'' +
                '}';
    }
}
