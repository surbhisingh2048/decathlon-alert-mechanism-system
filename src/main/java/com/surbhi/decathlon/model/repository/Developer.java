package com.surbhi.decathlon.model.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
//@AllArgsConstructor
@Table(name = "developer")
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int devId;
    String name;
    String phoneNumber;

    public String notifyResponseValue() {
        return "notified the below dev\n\nname='" + name + '\'' +
                ", phoneNumber='" + phoneNumber +"'";
    }

}
