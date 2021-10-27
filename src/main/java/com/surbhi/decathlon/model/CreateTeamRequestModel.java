package com.surbhi.decathlon.model;

import lombok.Data;

import java.util.List;

@Data
public class CreateTeamRequestModel {

    String teamName;
    List<DevelopersRequestModel> developers;

}
