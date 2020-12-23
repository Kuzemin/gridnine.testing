package com.gridnine.testing;

import java.util.List;

public class Main {
	public static void main (String[] args) {
		List<Flight> testFlights = FlightBuilder.createFlights();
		
		System.out.println("Тестовый набор перелётов: " + testFlights.toString() + "\n");
		System.out.println("Фильтрация перелётов с вылетом до текущего момента времени: " + FilterOutFlights.Departured(testFlights));
		System.out.println("Фильтрация перелётов с некорректными сегментами: " + FilterOutFlights.Incorrect(testFlights));
		System.out.println("Фильтрация перелётов с временем на земле больше 2х часов: " + FilterOutFlights.OverTwoHoursOnGround(testFlights));
	}
}
