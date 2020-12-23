package com.gridnine.testing;

import java.util.List;

public class Main {
	public static void main (String[] args) {
		List<Flight> testFlights = FlightBuilder.createFlights();
		
		System.out.println("�������� ����� ��������: " + testFlights.toString() + "\n");
		System.out.println("���������� �������� � ������� �� �������� ������� �������: " + FilterOutFlights.Departured(testFlights));
		System.out.println("���������� �������� � ������������� ����������: " + FilterOutFlights.Incorrect(testFlights));
		System.out.println("���������� �������� � �������� �� ����� ������ 2� �����: " + FilterOutFlights.OverTwoHoursOnGround(testFlights));
	}
}
