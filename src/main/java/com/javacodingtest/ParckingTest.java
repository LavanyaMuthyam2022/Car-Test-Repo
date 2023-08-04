package com.javacodingtest;

import java.util.ArrayList;
import java.util.List;

import com.javacodingtest.charges.CarCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Car;
import com.javacodingtest.service.ParkingLot;

public class ParckingTest {


	
	public static void main(String[] args) throws ParkingException, InvalidVehicleNoException {
		// TODO Auto-generated method stub
		
		List<Car> vehicleList = new ArrayList<Car>();
		List<Ticket> ticketList = new ArrayList<Ticket>();
		
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		
		//Testing with slots available

		parkingLot.setParkingSlots(100);
		
		for(int i=0; i< 97; i++) {
			vehicleList.add(new Car("FG123"+i));
		}
		
		//Vehicle vehicle = new Vehicle("FG123");
		for(Car vehicle : vehicleList) {
			ticketList.add(parkingLot.park(vehicle));
			System.out.println(ticketList.get(0));
		}
		
		
		int cost = parkingLot.remove(ticketList.get(1), new CarCharges());
		System.out.println("Total cost is: £"+cost);
		
		for(int i=0; i< 5; i++) {
			vehicleList.add(new Car("FG423"+i));
		}
		for(Car vehicle : vehicleList) {
			ticketList.add(parkingLot.park(vehicle));
		}
		
		
		

	}

}
