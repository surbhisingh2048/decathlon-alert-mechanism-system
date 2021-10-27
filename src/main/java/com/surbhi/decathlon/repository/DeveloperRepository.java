package com.surbhi.decathlon.repository;

import com.surbhi.decathlon.model.repository.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer,String> {
    Developer getByDevId(int id);
}
