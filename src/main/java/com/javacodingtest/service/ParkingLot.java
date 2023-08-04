package com.javacodingtest.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javacodingtest.charges.ParkingCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Car;
import com.javacodingtest.service.ParkingCheck;
import com.javacodingtest.model.Slot;
import com.javacodingtest.service.ParkingLot;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;


public class ParkingLot implements ParkingCheck{
	
	private static ParkingLot parkingLot;
	private final List<Slot> slotAvailable;
	
	private ParkingLot() {
		this.slotAvailable = new ArrayList<Slot>();
	}
	
	public static ParkingLot getParkingLot() {
		if (parkingLot == null)
			parkingLot = new ParkingLot();
		return parkingLot;
	}
	

	
	public boolean setParkingSlots(int numberOfParkingSlots) {

		for (int i = 1; i <= numberOfParkingSlots; i++) {
			slotAvailable.add(new Slot(i));
		}

		System.out.printf("Created a parking lot with slots ", numberOfParkingSlots);
		return true;
	}
	
	public Ticket park(Car vehicle) throws ParkingException {
		// TODO Auto-generated method stub
		Slot nextAvailableSlot;
		nextAvailableSlot = getNextAvailableFourWheelerSlot();
		nextAvailableSlot.occupySlot(vehicle);
		System.out.printf("Slot Allocated: %d \n", nextAvailableSlot.getSlotNumber());
		Ticket ticket = new Ticket(nextAvailableSlot.getSlotNumber(), vehicle.getVehicleNumber(), new Date());
		return ticket;
	}



	private Slot getNextAvailableFourWheelerSlot() throws ParkingException{
		// TODO Auto-generated method stub
		for (Slot slot : slotAvailable) {
			if (slot.isEmpty()) {
				return slot;
			}
		}
		throw new ParkingException("the car park is full");
	}

	public int remove(Ticket ticket, ParkingCharges parkingCharges) throws InvalidVehicleNoException {
		int totalCharge = 0;
		Slot slot;
		slot = getSlot(ticket.getVehicleNumber());
		slot.vacateSlot();
		int duration = getParkedHours(ticket.getDate(), new Date());
		
//		int duration = 2;
		totalCharge = parkingCharges.generateCharges(duration);
		
		System.out.println(" Car "+ticket.getVehicleNumber() +" at slot "
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
		
		for (Slot slot : slotAvailable) {
			Car vehicle = slot.getParkVehicle();
			if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
				return slot;
			}
			
			
		}
		throw new InvalidVehicleNoException("Vehicle with registration number " + vehicleNumber + " not found");
		
	}
}
