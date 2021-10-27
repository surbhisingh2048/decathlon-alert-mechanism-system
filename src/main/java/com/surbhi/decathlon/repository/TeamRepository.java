package com.surbhi.decathlon.repository;

import com.surbhi.decathlon.model.repository.Team;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeamRepository extends JpaRepository<Team,String> {
    Team getByTeamId(int id);
}
