package com.example.carfactory.model.car;

public class Sedan implements Car {

	@Override
	public String getType() {
		return this.getClass().getSimpleName()
				.concat(" Car has produced.");
	}

}
