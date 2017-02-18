package com.ComeOnBaby.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by olegs on 17.02.2017.
 */

@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(generator = "increment2")
    @GenericGenerator(name = "increment2", strategy = "increment")
    @Column(name = "id")
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    public City(String name) {
        this.name = name;
    }

    public City() {
    }

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

    @Override
    public String toString() {
        return "id=" + id + ", name=" + name;
    }
}
