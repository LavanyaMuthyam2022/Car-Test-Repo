package com.javacodingtest.service;


import com.javacodingtest.charges.ParkingCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Car;

public interface ParkingCheck {
	
	public Ticket park(Car vehicle)throws ParkingException;
	public int remove(Ticket ticker,ParkingCharges parkingCost) throws InvalidVehicleNoException;

}
