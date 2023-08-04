package com.javacodingtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javacodingtest.charges.CarCharges;
import com.javacodingtest.charges.ParkingCharges;
import com.javacodingtest.exception.InvalidVehicleNoException;
import com.javacodingtest.exception.ParkingException;
import com.javacodingtest.model.Ticket;
import com.javacodingtest.model.Car;
import com.javacodingtest.service.ParkingLot;

public class ParckingTest {


	
   public static void main(String[] args) throws ParkingException, InvalidVehicleNoException {
		toTestPakingFull();

	}

	public static void toTestPakingFull() throws ParkingException, InvalidVehicleNoException {
		List<Car> vehicleList = new ArrayList<Car>();
		List<Ticket> ticketList = new ArrayList<Ticket>();
		
	
		ParkingLot parkingLot = ParkingLot.getParkingLot();
		Scanner sc = new Scanner(System.in);
		System.out.print("Press 1 to test Car Parking");
		System.out.print("\nPress 2 to test Parking lot full ");
		System.out.print("\nPress 3 to test UnParking");
		String str = sc.nextLine();
		while (true) {
			switch (str.trim()) {

			case "1":
				System.out.println("Please enter Vachle number :");
				str = sc.nextLine();
				boolean setParkingSlots = parkingLot.setParkingSlots(ticketList.size()+1);
				ticketList.add(parkingLot.park(new Car(str)));
				System.out.println("Your Token Number is :" + (ticketList.size()));
				break;
			case "2":
				for (int i = 0; i < 101; i++) {
					vehicleList.add(new Car("FG123" + i));
					for (Car vehicle : vehicleList) {
						ticketList.add(parkingLot.park(vehicle));
					}
				}
				break;
			case "3":
				
				System.out.print("Please provide your Token Number : ");
				str = sc.nextLine();
				System.out.println(str);
				if(Integer.parseInt(str) > ticketList.size()) {
					System.out.println("Invalid token number");
				}
				else {
			
				Ticket ticket = ticketList.get(Integer.parseInt(str)-1);
				ParkingCharges carParkingCharges = new CarCharges();
				parkingLot.remove(ticket, carParkingCharges);
				//vehicleList.remove(Integer.parseInt(str));
			}
				break;
			}
			System.out.print("Press 1 to test Car Parking");
			System.out.print("\nPress 2 to test Parking lot full ");
			System.out.print("\nPress 3 to test UnParking lot full ");
			str = sc.nextLine();
		}

	}

}
