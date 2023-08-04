package com.javacodingtest.model;

import com.javacodingtest.model.Car;


public class Slot {
	
	private int slotNumber;
	private boolean isEmpty;
	private Car parkVehicle;

	public Slot(int slotNumber) {
		this.slotNumber = slotNumber;
		isEmpty = true;
	}

	public int getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(int slotNumber) {
		this.slotNumber = slotNumber;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public Car getParkVehicle() {
		return parkVehicle;
	}

	public void setParkVehicle(Car parkVehicle) {
		this.parkVehicle = parkVehicle;
	}
	
    //method to unpark the vehicle
	public void vacateSlot() {
		parkVehicle = null;
		this.isEmpty = true;
	}
    // method to check and allocate the slot
	public void occupySlot(Car parkVehicle) {
		this.parkVehicle = parkVehicle;
		this.isEmpty = false;
	}

}
