package com.smartcar.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.smartcar.model.system.DoorSecurityInfo;
import com.smartcar.model.system.EngineAction;
import com.smartcar.model.system.EngineActionResponse;
import com.smartcar.model.system.PercentInfo;
import com.smartcar.model.system.VehicleInfo;
import com.smartcar.model.system.enums.Action;
import com.smartcar.service.VehicleService;
import com.smartcar.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(VehiclesController.class)
public class VehiclesControllerTests {

	@MockBean
	private VehicleService service;

	@Autowired
	private MockMvc mvc;

	@Test
	public void testVehicleInfo() throws Exception {
		int id = 1234;
		VehicleInfo info = new VehicleInfo();
		info.setColor("Metallic Silver");
		info.setDoorCount(4);
		info.setDriveTrain("v8");
		info.setVin("1213231");
		when(service.getVehicleInfo(id)).thenReturn(JsonUtil.getJsonString(info));
		mvc.perform(get("/vehicles/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.vin", is(info.getVin())))
				.andExpect(jsonPath("$.color", is(info.getColor())))
				.andExpect(jsonPath("$.driveTrain", is(info.getDriveTrain())))
				.andExpect(jsonPath("$.doorCount", is(info.getDoorCount())));

	}
	

	@Test
	public void testAnotherVehicleInfo() throws Exception {
		int id = 1235;
		VehicleInfo info = new VehicleInfo();
		info.setColor("Forest Green");
		info.setDoorCount(2);
		info.setDriveTrain("electric");
		info.setVin("1235AZ91XP");
		when(service.getVehicleInfo(id)).thenReturn(JsonUtil.getJsonString(info));
		mvc.perform(get("/vehicles/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.vin", is(info.getVin())))
				.andExpect(jsonPath("$.color", is(info.getColor())))
				.andExpect(jsonPath("$.driveTrain", is(info.getDriveTrain())))
				.andExpect(jsonPath("$.doorCount", is(info.getDoorCount())));
	}

	@Test
	public void testVehicleBatteryInfo() throws Exception {
		int id = 1235;
		PercentInfo info = new PercentInfo();
		info.setPercent(86);
		when(service.getVehicleBatteryInfo(id)).thenReturn(JsonUtil.getJsonString(info));
		mvc.perform(get("/vehicles/" + id + "/battery").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.percent", is(info.getPercent())));

	}

	@Test
	public void testVehicleFuelInfo() throws Exception {
		int id = 1235;
		PercentInfo info = new PercentInfo();
		info.setPercent(42);
		when(service.getVehicleFuelInfo(id)).thenReturn(JsonUtil.getJsonString(info));
		mvc.perform(get("/vehicles/" + id + "/fuel").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.percent", is(info.getPercent())));

	}

	@Test
	public void testVehicleSecurityInfo() throws Exception {
		int id = 1235;
		List<DoorSecurityInfo> infoList = new ArrayList<>();
		DoorSecurityInfo info = new DoorSecurityInfo();
		info.setLocation("frontLeft");
		info.setLocked(true);
		infoList.add(info);
		DoorSecurityInfo info2 = new DoorSecurityInfo();
		info2.setLocation("frontRight");
		info2.setLocked(true);
		infoList.add(info2);

		when(service.getVehicleSecurityInfo(id)).thenReturn(JsonUtil.getJsonString(infoList));
		mvc.perform(get("/vehicles/" + id + "/doors").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].location", is(info.getLocation())))
				.andExpect(jsonPath("$[0].locked", is(info.isLocked())))
				.andExpect(jsonPath("$[1].location", is(info2.getLocation())))
				.andExpect(jsonPath("$[1].locked", is(info2.isLocked())));

	}

	@Test
	public void testVehicleEngineSuccessAction() throws Exception {
		int id = 1235;
		EngineActionResponse res = new EngineActionResponse();
		res.setStatus("success");

		EngineAction action = new EngineAction();
		action.setAction(Action.STOP);

		when(service.performEngineEaction(id, Action.STOP)).thenReturn(JsonUtil.getJsonString(res));
		mvc.perform(post("/vehicles/" + id + "/engine").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(JsonUtil.getJsonString(action))).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(res.getStatus())));

	}

	@Test
	public void testVehicleEngineErrorAction() throws Exception {
		int id = 1234;
		EngineActionResponse res = new EngineActionResponse();
		res.setStatus("success");

		EngineAction action = new EngineAction();
		action.setAction(Action.START);

		when(service.performEngineEaction(id, Action.START)).thenReturn(JsonUtil.getJsonString(res));
		mvc.perform(post("/vehicles/" + id + "/engine").contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8").content(JsonUtil.getJsonString(action))).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.status", is(res.getStatus())));
	}



}