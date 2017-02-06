package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "s_cases")
public class Case {

    public Case() {
    }

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "case_id")
    private Long id;

    @Column(name = "user_manager_id")
    private Long userManagerId;

    @Column(name = "title")
    private String projectTitle;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "date_create")
    private Date creationDate;

    @OneToMany(mappedBy = "aCase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Message> messages;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserManagerId() {
        return userManagerId;
    }

    public void setUserManagerId(Long userManagerId) {
        this.userManagerId = userManagerId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Case(Project project, Long userManagerId, String projectTitle, Status status, Date creationDate, Set<Message> messages) {
        this.project = project;
        this.userManagerId = userManagerId;
        this.projectTitle = projectTitle;
        this.status = status;
        this.creationDate = creationDate;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Case{" +
                "project=" + project +
                ", id=" + id +
                ", userManagerId=" + userManagerId +
                ", projectTitle='" + projectTitle + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", messages=" + messages +
                '}';
    }
}
