package com.surbhi.decathlon.controller;

import com.surbhi.decathlon.model.CreateTeamRequestModel;
import com.surbhi.decathlon.model.repository.Developer;
import com.surbhi.decathlon.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    MainService service;

    @PostMapping("/team")
    public ResponseEntity createTeam(@RequestBody CreateTeamRequestModel requestModel) throws Exception {
        int teamId = service.createTeam(requestModel);
        return new ResponseEntity("team created with id "+teamId, HttpStatus.OK);
    }

    @PostMapping("/{id}/alert")
    public ResponseEntity createAlert(@PathVariable("id") String id) throws Exception {
        Developer dev = service.createAlert(id);
        return new ResponseEntity(dev.notifyResponseValue(), HttpStatus.OK);
    }

}
