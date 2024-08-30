package com.markcrowe.jdbc.commandline;

import com.markcrowe.jdbc.countryvaccinationdata.CountryVaccinationData;

import java.sql.*;

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
                System.out.println("ID: " + resultSet.getInt("id") + ", Country: " + resultSet.getString("country") +
                        ", Date: " + resultSet.getDate("date") + ", Total Vaccinations: " + resultSet.getInt("total_vaccinations"));
                count++;
            }

            if (count == 0) {
                System.out.println("No records found for ISO code: " + isoCode);
            } else {
                System.out.println(count + " records found.");
            }

        }
    }

    //task2
    public int insertNewRecord(CountryVaccinationData data) throws SQLException {
        String query = "INSERT INTO country_vaccination_data (country, iso_code, total_vaccinations, people_vaccinated, " +
                "people_fully_vaccinated, daily_vaccinations, vaccines, date) VALUES (?, ?, ?, ?, ?, ?, ?, CURDATE())";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, data.getCountry());
            preparedStatement.setString(2, data.getIsoCode());
            preparedStatement.setInt(3, data.getTotalVaccinations());
            preparedStatement.setInt(4, data.getPeopleVaccinated());
            preparedStatement.setInt(5, data.getPeopleFullyVaccinated());
            preparedStatement.setInt(6, data.getDailyVaccinations());
            preparedStatement.setString(7, data.getVaccines());

            // Get the generated new ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    data.setId(generatedId);
                    return generatedId;
                }
            }
        }
        throw new SQLException("Creating property failed, no ID obtained.");
    }

    //task3
    public void calculateAverageDailyVaccinations(Date sDate, Date eDate, String isoCode) throws SQLException {
        String query = "SELECT AVG(daily_vaccinations) FROM country_vaccination_data " +
                "WHERE iso_code = ? AND date BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, isoCode);
            preparedStatement.setDate(2, new java.sql.Date(sDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(eDate.getTime()));

            ResultSet ResultSet = preparedStatement.executeQuery();
            if (ResultSet.next()) {
                double average = ResultSet.getDouble(1);
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
            ResultSet ResultSet = callableStatement.executeQuery();

            if (ResultSet.next()) {
                System.out.println("ID: " + ResultSet.getInt("id") + ", Country: " + ResultSet.getString("country") +
                        ", Date: " + ResultSet.getDate("date") + ", Total Vaccinations: " + ResultSet.getInt("total_vaccinations"));
            } else {
                System.out.println("No record found with ID: " + recordID);
            }
        }
    }

}

