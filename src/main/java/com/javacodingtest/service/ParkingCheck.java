package com.javacodingtest.service;


import com.javacodingtest.charges.ParkingCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Vehicle;

public interface ParkingCheck {
	
	public Ticket park(Vehicle vehicle)throws ParkingException;
	public int unPark(Ticket ticker,ParkingCharges parkingCost) throws InvalidVehicleNoException;

}
