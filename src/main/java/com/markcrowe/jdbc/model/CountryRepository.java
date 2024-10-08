package com.markcrowe.jdbc.model;

import java.sql.*;

import com.markcrowe.jdbc.utils.Console;
import com.markcrowe.jdbc.utils.DatabaseUtility;

public class CountryRepository {
    private final Connection connection;

    public CountryRepository(Connection connection) {
        this.connection = connection;
    }

    //task1
    public void searchByIsoCode(String isoCode) throws SQLException {
        String query = "SELECT * FROM country_vaccination_data WHERE iso_code = ? ORDER BY date DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, isoCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                Console.displayLine("ID: " + resultSet.getInt("id") + ", Country: " + resultSet.getString("country") +
                        ", Date: " + resultSet.getDate("date") + ", Total Vaccinations: " + resultSet.getInt("total_vaccinations"));
                count++;
            }

            if (count == 0) {
                Console.displayLine("No records found for ISO code: " + isoCode);
            } else {
                Console.displayLine(count + " records found.");
            }

        }
    }

    //task2
    public int insertNewRecord(CountryVaccinationData countryVaccinationData) throws SQLException {
        String query = "INSERT INTO country_vaccination_data (country, iso_code, total_vaccinations, people_vaccinated, " +
                "people_fully_vaccinated, daily_vaccinations, vaccines, date) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, countryVaccinationData.getCountry());
            preparedStatement.setString(2, countryVaccinationData.getIsoCode());
            preparedStatement.setInt(3, countryVaccinationData.getTotalVaccinations());
            preparedStatement.setInt(4, countryVaccinationData.getPeopleVaccinated());
            preparedStatement.setInt(5, countryVaccinationData.getPeopleFullyVaccinated());
            preparedStatement.setInt(6, countryVaccinationData.getDailyVaccinations());
            preparedStatement.setString(7, countryVaccinationData.getVaccines());

            preparedStatement.executeUpdate();
            // Get the generated new ID

            int newId = DatabaseUtility.getNewId(preparedStatement);
            countryVaccinationData.setId(newId);
            return newId;
        }
    }


    //task3
    public void calculateAverageDailyVaccinations(Date beginDate, Date endDate, String isoCode) throws SQLException {
        String query = "SELECT AVG(daily_vaccinations) FROM country_vaccination_data " +
                "WHERE iso_code = ? AND date BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, isoCode);
            preparedStatement.setDate(2, new java.sql.Date(beginDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(endDate.getTime()));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                double average = resultSet.getDouble(1);
                System.out.printf("Average daily vaccinations: %.2f%n", average);
            } else {
                System.out.println("No data available for the given range.");
            }
        }
    }


    //task4
    public void retrieveRecordById(int recordID) throws SQLException {
        String query = "{CALL getCountryByID(?)}";
        try (CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setInt(1, recordID);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                Console.displayLine("ID: " + resultSet.getInt("id") + ", Country: " + resultSet.getString("country") +
                        ", Date: " + resultSet.getDate("date") + ", Total Vaccinations: " + resultSet.getInt("total_vaccinations"));
            } else {
                Console.displayLine("No record found with ID: " + recordID);
            }
        }
    }

}

