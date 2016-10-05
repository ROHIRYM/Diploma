package com.rohirym.diploma;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

public class DataBaseReader {

	private final String URL;
	private final String USERNAME;
	private final String PASSWORD;

	private HashMap<String, ArrayList<DateAndSales>> observationsOfShops;

	public DataBaseReader(String dataBaseName, String login, String password)
			throws SQLException {

		URL = "jdbc:mysql://localhost:3306/" + dataBaseName;
		USERNAME = login;
		PASSWORD = password;

		Connection connection;

		Driver driver = new FabricMySQLDriver();
		DriverManager.registerDriver(driver);

		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		Statement statement = connection.createStatement();

		ResultSet observationsResultSet = statement
				.executeQuery("select shop.name, year(buy.dateT), month(buy.dateT), day(buy.dateT), product.price * buylist.quantity from shop"
						+ " inner join buy"
						+ " on shop.shopId = buy.shopId"
						+ " inner join buylist"
						+ " on buy.buyId = buylist.buyId"
						+ " inner join product"
						+ " on buylist.productId_ = product.productId;");	
		observationsOfShops = fillObservationsOfShops(observationsResultSet);

		connection.close();

	}

	private HashMap<String, ArrayList<DateAndSales>> fillObservationsOfShops(
			ResultSet resultSet) throws SQLException {
		HashMap<String, ArrayList<DateAndSales>> result = new HashMap<>();
		while (resultSet.next()) {
			String shopName = resultSet.getString("shop.name");
			int year = resultSet.getInt("year(buy.dateT)");
			int month = resultSet.getInt("month(buy.dateT)");
			int day = resultSet.getInt("day(buy.dateT)");
			int sales = resultSet.getInt("product.price * buylist.quantity");
			DateAndSales dateAndSales = new DateAndSales(year, month, day, sales);
			if (result.containsKey(shopName)) {
				result.get(shopName).add(dateAndSales);
			} else {
				ArrayList<DateAndSales> temp = new ArrayList<>();
				temp.add(dateAndSales);
				result.put(shopName, temp);
			}
		}
		return result;
	}
	
	public HashMap<String, ArrayList<DateAndSales>> getObservationsOfShops() {
		return observationsOfShops;
	}

}
