package com.surbhi.decathlon.service;

import com.surbhi.decathlon.model.CreateTeamRequestModel;
import com.surbhi.decathlon.model.DevelopersRequestModel;
import com.surbhi.decathlon.model.repository.Developer;
import com.surbhi.decathlon.model.repository.Team;
import com.surbhi.decathlon.repository.DeveloperRepository;
import com.surbhi.decathlon.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MainService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DeveloperRepository developerRepository;

    @Autowired
    AlertService alertService;

    public int createTeam(CreateTeamRequestModel requestModel) throws Exception {
        validate(requestModel);
        String listOfDevs = "";
        for(DevelopersRequestModel dev : requestModel.getDevelopers()){
            Developer d = new Developer();
            d.setName(dev.getName());
            d.setPhoneNumber(dev.getPhoneNumber());
            d = developerRepository.save(d);
            listOfDevs += d.getDevId() +",";
        }
        Team t = new Team();
        t.setTeamName(requestModel.getTeamName());
        t.setListOfDevs(listOfDevs.substring(0,listOfDevs.length()-1));
        t = teamRepository.save(t);
        return t.getTeamId();
    }

    public Developer createAlert(String id) throws Exception {
        Team t = teamRepository.getByTeamId(Integer.parseInt(id));
        if(Objects.isNull(t)){
            returnErrorResponse("invalid team id");
        }
        List<String> listOfDev = Arrays.asList(t.getListOfDevs().split(","));
        int devIndex =  ThreadLocalRandom.current().nextInt(0, listOfDev.size() );
        Developer dev = developerRepository.getByDevId(Integer.parseInt(listOfDev.get(devIndex)));
        alertService.notify(dev);
        return dev;
    }

    private void validate(CreateTeamRequestModel requestModel) throws Exception {
        if(Objects.isNull(requestModel.getTeamName()) || requestModel.getTeamName().isEmpty()){
            returnErrorResponse("teamName cannot be empty/null");
        }
        if(Objects.isNull(requestModel.getDevelopers()) || requestModel.getDevelopers().isEmpty()){
            returnErrorResponse("developers list cannot be empty/null");
        }
        for(DevelopersRequestModel dev : requestModel.getDevelopers()){
            if(Objects.isNull(dev.getName()) || dev.getName().isEmpty()){
                returnErrorResponse("developer name cannot be empty/null");
            }
            //validate phone number
            if(dev.getPhoneNumber().length()!=10) {
                returnErrorResponse("invalid phone number");
            }
        }
    }

    private void returnErrorResponse(String errorDescription) throws Exception {
        throw new Exception(errorDescription);
    }

}
