package com.task_management.task;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

@Entity
@Table(name = "users")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 45)
    private String assignees;

    @Column(length = 15, nullable = false)
    private String projects;

    @Column(length = 45, nullable = false, name = "priority")
    private String priority;

    @Column(length = 100, nullable = false, name = "description")
    private String description;


    @Column(length = 45, nullable = false, name = "last_name")
    private File fileAttachment;

    private Date startDate;

    private Date endDate;

    private boolean enabled;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssignees() {
        return assignees;
    }

    public void setAssignees(String assignees) {
        this.assignees = assignees;
    }

    public String getProjects() {
        return projects;
    }

    public void setProjects(String projects) {
        this.projects = projects;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getFileAttachment() {
        return fileAttachment;
    }

    public void setFileAttachment(File fileAttachment) {
        this.fileAttachment = fileAttachment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", assignees='" + assignees + '\'' +
                ", projects='" + projects + '\'' +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                ", fileAttachment=" + fileAttachment +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", enabled=" + enabled +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
