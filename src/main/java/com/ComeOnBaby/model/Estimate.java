package com.ComeOnBaby.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="s_estimates")
public class Estimate {

    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    private Long id;


    @Column(name="name", nullable=false)
    private String name;


    @Column(name="email", nullable=false)
    private String email;

    @Column(name="estimate_request", nullable=false)
    private String estimateRequest;


    @Column(name="state" , nullable=false)
    private int state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstimateRequest() {
        return estimateRequest;
    }

    public void setEstimateRequest(String estimateRequest) {
        this.estimateRequest = estimateRequest;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public  Estimate(){}

    public Estimate(String name, String email, String estimateRequest, int state) {
        this.name = name;
        this.email = email;
        this.estimateRequest = estimateRequest;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Estimate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", estimateRequest='" + estimateRequest + '\'' +
                ", state=" + state +
                '}';
    }
}
