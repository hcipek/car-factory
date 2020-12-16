package com.example.carfactory.exception;

public class CarFactoryException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8605065566158561337L;
	
	private String description;
	
	public CarFactoryException(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
