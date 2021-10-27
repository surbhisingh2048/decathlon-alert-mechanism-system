package com.surbhi.decathlon.model.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "team")
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int teamId;
    String teamName;
//    @OneToMany(targetEntity=Developer.class, mappedBy="college", fetch= FetchType.EAGER)
//    private List<Developer> listOfDevs;

    String listOfDevs;

}
