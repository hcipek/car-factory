package com.example.carfactory.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.carfactory.exception.CarFactoryException;
import com.example.carfactory.model.car.Cabrio;
import com.example.carfactory.model.car.Car;
import com.example.carfactory.model.car.Hatchback;
import com.example.carfactory.model.car.Sedan;
import com.example.carfactory.model.request.CarFactoryRequest;
import com.example.carfactory.model.response.CarFactoryResponse;

@RestController
@RequestMapping("/api")
public class CarFactoryController {
	
	@GetMapping
	public String getDefault() {
		return "Welcome to the Car Factory! Enjoy your ride!";
	}
	
	@GetMapping("/getsample")
	public String getSampleByName(@RequestParam Optional<String> name) {
		
		String resultMessage;
		
		try {
			if(name.isEmpty())
				throw new CarFactoryException("INVALID_REQUEST");
			resultMessage = handleCarTypeByName(name.get());
		} catch (CarFactoryException e) {
			resultMessage = e.getDescription();
		} catch (Exception e) {
			resultMessage = "UNEXPECTED_ERROR";
		}
					
		return resultMessage;
	}
	
	@GetMapping("/getsamplewithbody")
	public CarFactoryResponse getSampleByName(@RequestBody CarFactoryRequest request) {
		
		CarFactoryResponse response = new CarFactoryResponse();
		
		try {
			response.setResult(handleCarTypeByName(request.getName()));
		} catch (CarFactoryException e) {
			response.setResult(e.getDescription());
		} catch (Exception e) {
			response.setResult("UNEXPECTED_ERROR");
		}
		
		return response;
	}
	
	private String handleCarTypeByName(String name) {
		Locale locale = new Locale("us");
		switch(name.toLowerCase(locale)) {
			case "sedan" :
				return getValue(new Sedan());
			case "cabrio" :
				return getValue(new Cabrio());
			case "hatchback" :
				return getValue(new Hatchback());
			default :
				throw new CarFactoryException("INVALID_VALUE");
		}
	}
	
	private String getValue(Car car) {
		return car.getType();
	}

}
