package com.example.carfactory.controller;

import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.carfactory.model.request.CarFactoryRequest;
import com.example.carfactory.model.response.CarFactoryResponse;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class CarFactoryControllerTest {
	
	private final String SEDAN_RESP = "Sedan Car has produced.";
	private final String CABRIO_RESP = "Cabrio Car has produced.";
	private final String HATCHBACK_RESP = "Hatchback Car has produced.";
	private final String INVALID_VALUE_RESP = "INVALID_VALUE";
	private final String INVALID_REQ_RESP = "INVALID_REQUEST";

	private final String SEDAN_SUCCESS_1 = "SEDAN";
	private final String SEDAN_SUCCESS_2 = "sedan";
	private final String CABRIO_SUCCESS_1 = "CABRIO";
	private final String CABRIO_SUCCESS_2 = "CabRiO";
	private final String HATCHBACK_SUCCESS_1 = "HATCHBACK";
	private final String HATCHBACK_SUCCESS_2 = "HaTchBaCk";
	private final String SEDAN_FAIL_1 = "SEDANn";
	private final String SEDAN_FAIL_2 = "sedn";
	private final String CABRIO_FAIL_1 = "CABRiIO";
	private final String CABRIO_FAIL_2 = "CaabRiO";
	private final String HATCHBACK_FAIL_1 = "HATCcHBACK";
	private final String HATCHBACK_FAIL_2 = "HatTchBaCk";
	
	@Autowired
	private CarFactoryController carFactoryController;
	
	@Test
	@Order(1)
	public void getDefaultTest() {
		String defaultValue = carFactoryController.getDefault();
		assertNotNull(defaultValue);
	}
	
	@Test
	@Order(2)
	public void getSampleSuccessWithIgnoreCaseTest() {
		Optional<String> name = Optional.ofNullable(SEDAN_SUCCESS_1);
		String sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, SEDAN_RESP);
		name = Optional.ofNullable(SEDAN_SUCCESS_2);
		sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, SEDAN_RESP);
		name = Optional.ofNullable(CABRIO_SUCCESS_1);
		sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, CABRIO_RESP);
		name = Optional.ofNullable(CABRIO_SUCCESS_2);
		sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, CABRIO_RESP);
		name = Optional.ofNullable(HATCHBACK_SUCCESS_1);
		sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, HATCHBACK_RESP);
		name = Optional.ofNullable(HATCHBACK_SUCCESS_2);
		sample = carFactoryController.getSampleByName(name);
		assertEquals(sample, HATCHBACK_RESP);
	}
	
	@Test
	@Order(3)
	public void getSampleFailWithIgnoreCaseTest() {
		Optional<String> name = Optional.ofNullable(SEDAN_FAIL_1);
		String sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, SEDAN_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
		name = Optional.ofNullable(SEDAN_FAIL_2);
		sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, SEDAN_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
		name = Optional.ofNullable(CABRIO_FAIL_1);
		sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, CABRIO_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
		name = Optional.ofNullable(CABRIO_FAIL_2);
		sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, CABRIO_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
		name = Optional.ofNullable(HATCHBACK_FAIL_1);
		sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, HATCHBACK_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
		name = Optional.ofNullable(HATCHBACK_FAIL_2);
		sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, HATCHBACK_RESP);
		assertEquals(sample, INVALID_VALUE_RESP);
	}
	
	@Test
	@Order(4)
	public void getSampleFailNullParamTest() {
		Optional<String> name = Optional.ofNullable(null);
		String sample = carFactoryController.getSampleByName(name);
		assertNotEquals(sample, SEDAN_RESP);
		assertEquals(sample, INVALID_REQ_RESP);
	}
	
	@Test
	@Order(5)
	public void getSampleRespSuccessWithIgnoreCaseTest() {
		CarFactoryRequest req = new CarFactoryRequest();
		req.setName(SEDAN_SUCCESS_1);
		CarFactoryResponse resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), SEDAN_RESP);
		req.setName(SEDAN_SUCCESS_2);
		resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), SEDAN_RESP);
		req.setName(CABRIO_SUCCESS_1);
		resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), CABRIO_RESP);
		req.setName(CABRIO_SUCCESS_2);
		resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), CABRIO_RESP);
		req.setName(HATCHBACK_SUCCESS_1);
		resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), HATCHBACK_RESP);
		req.setName(HATCHBACK_SUCCESS_2);
		resp = carFactoryController.getSampleByName(req);
		assertEquals(resp.getResult(), HATCHBACK_RESP);
	}
	
	@Test
	@Order(6)
	public void getSampleRespFailWithIgnoreCaseTest() {
		CarFactoryRequest req = new CarFactoryRequest();
		req.setName(SEDAN_FAIL_1);
		CarFactoryResponse resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), SEDAN_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
		req.setName(SEDAN_FAIL_2);
		resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), SEDAN_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
		req.setName(CABRIO_FAIL_1);
		resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), CABRIO_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
		req.setName(CABRIO_FAIL_2);
		resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), CABRIO_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
		req.setName(HATCHBACK_FAIL_1);
		resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), HATCHBACK_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
		req.setName(HATCHBACK_FAIL_2);
		resp = carFactoryController.getSampleByName(req);
		assertNotEquals(resp.getResult(), HATCHBACK_RESP);
		assertEquals(resp.getResult(), INVALID_VALUE_RESP);
	}

}
