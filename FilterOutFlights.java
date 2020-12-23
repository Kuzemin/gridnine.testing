package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class FilterOutFlights {
	
	// ���������� ��������, � ������� ����� �� �������� ������� �������
	static List<Flight> Departured(List<Flight> flights) {
		List<Flight> filtered = new ArrayList<Flight>();
		//System.out.println(flights.size());
		for (int i = 0; i < flights.size(); i += 1) {
			Flight flight = flights.get(i);
			
			if (flight.getSegments().get(0).getDepartureDate().isAfter(LocalDateTime.now())) {
				filtered.add(flight);
			}
			else {
				// System.out.println("����� ������ " + (i + 1) + " �������: " + flight.getSegments().get(0).getDepartureDate() + " ��� ������.");
			}
		}
		
		return filtered;
	}
	
	// ���������� ��������, � ������� ������� �������� � ����� ������ ������ ���� ������
	static List<Flight> Incorrect(List<Flight> flights) {
		List<Flight> filtered = new ArrayList<Flight>();
		for (int i = 0; i < flights.size(); i += 1) {
			Flight flight = flights.get(i);
			boolean good = true;
			List<Segment> segs = flights.get(i).getSegments();
			for (int j = 0; j < segs.size(); j += 1) {
				if (segs.get(j).getArrivalDate().isBefore(segs.get(j).getDepartureDate())) {
					good = false;
					// System.out.println("������� " + segs.get(j).toString() + " � " + (i + 1) + " ������� ������������.");
					break;
				}
			if (good) { filtered.add(flight); }
			}
		}
		
		return filtered;
	}
	
	// ���������� ��������, � ������� ����� �����, ���������� �� ����� ��������� ��� ����
	// (����� �� ����� � ��� �������� ����� ������� ������ �������� � ������� ���������� �� ���)
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
				// System.out.println("����� �� ����� � " + (i + 1) + " ������� ���������� " + minsSpentOnGround + " �����. ��� ������� �����!");
			}
		}
		
		return filtered;
	}
}
