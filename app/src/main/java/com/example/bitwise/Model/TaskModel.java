package com.example.bitwise.Model;

public class TaskModel {

    String taskName;
    String taskPriority;
    String taskStatus;
    String taskTag;

    String taskkey;

    private boolean isChecked;

    public TaskModel() {
    }

    public TaskModel(String taskName, String taskPriority, String taskStatus, String taskTag) {
        this.taskName = taskName;
        this.taskPriority = taskPriority;
        this.taskStatus = taskStatus;
        this.taskTag = taskTag;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskTag() {
        return taskTag;
    }

    public void setTaskTag(String taskTag) {
        this.taskTag = taskTag;
    }

    public String getKey() {
        return taskkey;
    }

    public void setTaskkey(String taskKey) {
        this.taskkey = taskKey;
    }



    // Constructor, getters, setters for existing fields...

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
