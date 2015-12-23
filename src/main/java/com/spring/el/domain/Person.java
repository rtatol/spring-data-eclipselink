package com.spring.el.domain;

import javax.persistence.Entity;

@Entity
public class Person extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
