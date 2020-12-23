package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class FilterOutFlights {
	
	// Исключение перелётов, в которых вылет до текущего момента времени
	static List<Flight> Departured(List<Flight> flights) {
		List<Flight> filtered = new ArrayList<Flight>();
		//System.out.println(flights.size());
		for (int i = 0; i < flights.size(); i += 1) {
			Flight flight = flights.get(i);
			
			if (flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now())) {
				filtered.add(flight);
			}
			else {
				// System.out.println("Время вылета " + (i + 1) + " перелёта: " + flight.getSegments().get(0).getDepartureDate() + " уже прошло.");
			}
		}
		
		return filtered;
	}
	
	// Исключение перелётов, в которых имеются сегменты с датой прилёта раньше даты вылета
	static List<Flight> Incorrect(List<Flight> flights) {
		List<Flight> filtered = new ArrayList<Flight>();
		for (int i = 0; i < flights.size(); i += 1) {
			Flight flight = flights.get(i);
			boolean good = true;
			List<Segment> segs = flights.get(i).getSegments();
			for (int j = 0; j < segs.size(); j += 1) {
				if (segs.get(j).getArrivalDate().isBefore(segs.get(j).getDepartureDate())) {
					good = false;
					// System.out.println("Сегмент " + segs.get(j).toString() + " в " + (i + 1) + " перелёте некорректный.");
					break;
				}
			if (good) { filtered.add(flight); }
			}
		}
		
		return filtered;
	}
	
	// Исключение перелётов, в которых общее время, проведённое на земле превышает два часа
	// (время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним)
	static List<Flight> OverTwoHoursOnGround(List<Flight> flights) {
		List<Flight> filtered = new ArrayList<Flight>();
		for (int i = 0; i < flights.size(); i += 1) {
			Flight flight = flights.get(i);
			long minsSpentOnGround = 0;
			List<Segment> segs = flights.get(i).getSegments();
			for (int j = 0; j < segs.size() - 1; j += 1) {
				minsSpentOnGround += ChronoUnit.MINUTES.between(segs.get(j).getArrivalDate(), segs.get(j+1).getDepartureDate());
			}
			if (minsSpentOnGround <= 120) { filtered.add(flight); }
			else {
				// System.out.println("Время на земле в " + (i + 1) + " перелёте составляет " + minsSpentOnGround + " минут. Это слишком много!");
			}
		}
		
		return filtered;
	}
}
