package com.surbhi.decathlon;

import com.surbhi.decathlon.model.DevelopersRequestModel;
import com.surbhi.decathlon.model.repository.Team;
import com.surbhi.decathlon.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import com.surbhi.decathlon.model.CreateTeamRequestModel;
import com.surbhi.decathlon.model.repository.Developer;
import com.surbhi.decathlon.repository.DeveloperRepository;
import com.surbhi.decathlon.service.MainService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class AlertMechanismSystemApplicationTests {

	@Mock
	TeamRepository teamRepository;

	@Mock
	DeveloperRepository developerRepository;

	@InjectMocks
	private MainService service;

	@Test
	void test_createTeamSuccessScenerio() throws Exception {

		Developer dev = new Developer();
		dev.setName("Manoj");
		dev.setPhoneNumber("9884656448");

		CreateTeamRequestModel requestModel = new CreateTeamRequestModel();
		List<DevelopersRequestModel> devList = new ArrayList<>();
		DevelopersRequestModel d = new DevelopersRequestModel();
		d.setName("Manoj");
		d.setPhoneNumber("9884656448");
		DevelopersRequestModel d1 = new DevelopersRequestModel();
		d1.setName("Suresh");
		d1.setPhoneNumber("9464753882");
		devList.add(d);
		devList.add(d1);

		requestModel.setTeamName("Rock");
		requestModel.setDevelopers(devList);
		Team t =new Team();
		t.setTeamName(requestModel.getTeamName());
		t.setListOfDevs(String.valueOf(requestModel.getDevelopers()));
		when(developerRepository.save(any())).thenReturn(dev);
		when(teamRepository.save(any())).thenReturn(t);
		Assertions.assertNotNull(service.createTeam(requestModel));
	}
  @Test
	void test_invalidPhoneNumberFailureScenerio() throws Exception {

		Developer dev = new Developer();
		dev.setName("Manoj");
		dev.setPhoneNumber("988465644");
		CreateTeamRequestModel requestModel = new CreateTeamRequestModel();
		List<DevelopersRequestModel> devList = new ArrayList<>();
		DevelopersRequestModel d = new DevelopersRequestModel();
		d.setName("Manoj");
		d.setPhoneNumber("988465644");
		DevelopersRequestModel d1 = new DevelopersRequestModel();
		d1.setName("Suresh");
		d1.setPhoneNumber("9464753882");
		devList.add(d);
		devList.add(d1);
		requestModel.setTeamName("Rock");
		requestModel.setDevelopers(devList);
		Team t =new Team();
		t.setTeamName(requestModel.getTeamName());
		t.setListOfDevs(String.valueOf(requestModel.getDevelopers()));
		try {
			service.createTeam(requestModel);
		}
		catch (Exception e)
		{
			Assertions.assertEquals(e.getMessage(),"invalid phone number");
		}

	}


}
