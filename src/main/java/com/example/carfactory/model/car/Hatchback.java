package com.example.carfactory.model.car;

public class Hatchback implements Car {

	@Override
	public String getType() {
		return this.getClass().getSimpleName()
				.concat(" Car has produced.");
	}

}
