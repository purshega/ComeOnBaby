package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="status")
public class Status {

    public Status(){}



    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name="status_id")
    private Long id;

    @Column(name="type")
    private String statusType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Status(String statusType) {
        this.statusType = statusType;
    }

    public Status(Long id,String statusType ){
        this.id = id;
        this.statusType = statusType;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", statusType='" + statusType + '\'' +
                '}';
    }
}
