package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "s_projects")
public class Project {

    public Project() {
    }

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "project_id")
    private Long id;

    @Column(name = "name")
    private String projectName;


    @Column(name = "date_create")
    private Date createDate;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;


    @ManyToOne
    @JoinColumn(name = "user_customer_id")
    private CustomerInfo customerInfo;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Case> cases;

    @Column(name = "technology_type")
    private String technologyType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Set<Case> getCases() {
        return cases;
    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Project(String projectName, Date createDate, Status status, CustomerInfo customerInfo, Set<Case> cases, String technologyType) {
        this.projectName = projectName;
        this.createDate = createDate;
        this.status = status;
        this.customerInfo = customerInfo;
        this.cases = cases;
        this.technologyType = technologyType;
    }


/*    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", createDate=" + createDate +
                ", status=" + status +
                ", customerInfo=" + customerInfo +
                ", cases=" + cases +
                ", technologyType='" + technologyType + '\'' +
                '}';
    }*/


}
