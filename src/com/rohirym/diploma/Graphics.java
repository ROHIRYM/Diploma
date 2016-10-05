package com.rohirym.diploma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimeSeries;

public class Graphics {

	private HashMap<String, ArrayList<DateAndSales>> observations;
	private ArrayList<DateAndSales> dateAndSales;
	private int len;

	private TimeSeries timeObservations;

	public Graphics(HashMap<String, ArrayList<DateAndSales>> observations) {
		this.observations = observations;
		dateAndSales = new ArrayList<DateAndSales>();
		fillDateAndSales();
		timeObservations = new TimeSeries("Квартальні продажі", Quarter.class);
		fillTimeObservations();
		ForecastingChart forecast = new ForecastingChart("Прогнози", timeObservations, len);
		forecast.pack();
		forecast.setVisible(true);
	}

	private void fillDateAndSales() {
		Iterator<Map.Entry<String, ArrayList<DateAndSales>>> iterator = observations
				.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, ArrayList<DateAndSales>> pair = iterator.next();
			for (DateAndSales datAndSal : pair.getValue()) {
				if (dateAndSales.contains(datAndSal)) {
					for (DateAndSales el : dateAndSales) {
						if (el.equals(datAndSal)) {
							el.setSales(el.getSales() + datAndSal.getSales());
						}
					}
				} else {
					int year = datAndSal.getYear();
					int month = datAndSal.getMonth();
					int day = datAndSal.getDay();
					int sales = datAndSal.getSales();
					dateAndSales.add(new DateAndSales(year, month, day, sales));
				}
			}
		}
	}

	private void fillTimeObservations() {
		for (DateAndSales d : dateAndSales) {
			Quarter temp;
			if (d.getMonth() >= 1 && d.getMonth() < 4) {
				temp = new Quarter(1, d.getYear());
			} else if (d.getMonth() >= 4 && d.getMonth() < 7) {
				temp = new Quarter(2, d.getYear());
			} else if (d.getMonth() >= 7 && d.getMonth() < 10) {
				temp = new Quarter(3, d.getYear());
			} else {
				temp = new Quarter(4, d.getYear());
			}
			timeObservations.add(temp, d.getSales());
			len++;
		}
	}

	public TimeSeries getTimeObservations() {
		return timeObservations;
	}

}
