package com.javacodingtest.charges;

public class CarCharges implements ParkingCharges{

	public int getCharges(int hours) {
		// TODO Auto-generated method stub
		if(hours < 1) {
			return 2;
		}
		return hours * 2;
	}

}
