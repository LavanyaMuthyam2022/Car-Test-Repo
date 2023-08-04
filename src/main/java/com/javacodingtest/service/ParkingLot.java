package com.javacodingtest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javacodingtest.charges.ParkingCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Vehicle;
import com.javacodingtest.service.ParkingCheck;
import com.javacodingtest.model.Slot;
import com.javacodingtest.service.ParkingLot;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.VehicleSize;

public class ParkingLot implements ParkingCheck{
	
	private static ParkingLot parkingLot;
	private final List<Slot> fourWheeler;
	
	private ParkingLot() {
		this.fourWheeler = new ArrayList<Slot>();
	}
	
	public static ParkingLot getParkingLot() {
		if (parkingLot == null)
			parkingLot = new ParkingLot();
		return parkingLot;
	}
	

	
	public boolean setParkingSlots(int numberOfParkingSlots) {

		for (int i = 1; i <= numberOfParkingSlots; i++) {
			fourWheeler.add(new Slot(i));
		}

		System.out.printf("Created a four wheeler parking lot with %s slots %n", numberOfParkingSlots);
		return true;
	}
	
	public Ticket park(Vehicle vehicle) throws ParkingException {
		// TODO Auto-generated method stub
		Slot nextAvailableSlot;
		nextAvailableSlot = getNextAvailableFourWheelerSlot();
		nextAvailableSlot.occupySlot(vehicle);
		System.out.printf("Allocated slot number: %d \n", nextAvailableSlot.getSlotNumber());
		Ticket ticket = new Ticket(nextAvailableSlot.getSlotNumber(), vehicle.getVehicleNumber(), new Date());
		return ticket;
	}



	private Slot getNextAvailableFourWheelerSlot() throws ParkingException{
		// TODO Auto-generated method stub
		for (Slot slot : fourWheeler) {
			if (slot.isEmpty()) {
				return slot;
			}
		}
		throw new ParkingException("No Empty Slot available");
	}

	public int unPark(Ticket ticket, ParkingCharges parkingCharges) throws InvalidVehicleNoException {
		int totalCharge = 0;
		Slot slot;
		slot = getSlot(ticket.getVehicleNumber());
		slot.vacateSlot();
		int duration = getParkedHours(ticket.getDate(), new Date());
		
//		int duration = 2;
		totalCharge = parkingCharges.getCharges(duration);
		
		System.out.println(" Vehicle "+ticket.getVehicleNumber() +" at slot number "
				+ticket.getSlotNumber() + " was parked for " + duration + " total charge is " 
				+ totalCharge);
		
	return totalCharge;
	}

	private int getParkedHours(Date start, Date end) {
		// TODO Auto-generated method stub
		long timeSecs= (end.getTime() - start.getTime())/1000;
		int totalTime = (int)(timeSecs/3600);
		return totalTime;
	}

	private Slot getSlot(String vehicleNumber) throws InvalidVehicleNoException {
		// TODO Auto-generated method stub
		
		for (Slot slot : fourWheeler) {
			Vehicle vehicle = slot.getParkVehicle();
			if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
				return slot;
			}
			
			
		}
		throw new InvalidVehicleNoException("FourWheeler vehicle with registration number " + vehicleNumber + " not found");
		
	}
}
