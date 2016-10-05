package com.rohirym.diploma;

public class DateAndSales {

	private int year;
	private int month;
	private int day;
	private int sales;

	public DateAndSales(int year, int month, int day, int sales) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.sales = sales;
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public boolean equals(Object o) {
		DateAndSales d = (DateAndSales) o;
		return this.getYear() == d.getYear() && this.getMonth() == d.getMonth()
				&& this.getDay() == d.getDay();
	}

	@Override
	public String toString() {
		return "DateAndSales [year=" + year + ", month=" + month + ", day="
				+ day + ", sales=" + sales + "]";
	}

}
