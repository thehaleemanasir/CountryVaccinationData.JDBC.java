package com.markcrowe.jdbc;

import com.markcrowe.jdbc.model.CountryRepository;
import com.markcrowe.jdbc.model.CountryVaccinationData;
import com.markcrowe.jdbc.utils.Console;
import com.markcrowe.jdbc.utils.DatabaseUtility;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    DecimalFormat twoDigits = new DecimalFormat("0.00");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final CountryRepository repository;

    public static void main(String[] args) throws Exception {
        new Application();
    }

    public Application() throws Exception {

        repository = new CountryRepository(DatabaseUtility.getConnection());

        Console.displayLine("    Welcome to the Country Vaccination Data 2021 Database Application Menu");
        Console.displayLine();

        int choice = 0;

        while (choice != -1) {

            //display menu choices
            Console.displayLine("1:  Task 1");
            Console.displayLine("2:  Task 2");
            Console.displayLine("3:  Task 3");
            Console.displayLine("4:  Task 4");
            Console.displayLine("-1: Quit");
            Console.displayLine();

            //prompt user for their menu choice
            choice = Console.getInt("Enter Choice: ");

            //evaluate user choice
            switch (choice) {
                case 1:
                    taskOne();
                    break;
                case 2:
                    taskTwo();
                    break;
                case 3:
                    taskThree();
                    break;
                case 4:
                    taskFour();
                    break;
                default:
                    Console.displayLine("Invalid Choice");
            }
            Console.displayLine();
        }

        System.exit(0);
    }

    private void taskOne() throws SQLException {
        String isoCode = Console.getString("Enter ISO Code:  ");
        repository.searchByIsoCode(isoCode);
        Console.displayLine();
    }

    private void taskTwo() throws SQLException {
        String country = Console.getString("Enter country: ");
        if (country.isBlank()) {
            country = "Ireland";
            Console.displayLine("Defaulting to Ireland");
        }
        String isoCode = Console.getString("Enter ISO Code: ");
        if (isoCode.isBlank()) {
            isoCode = "IRE";
            Console.displayLine("Defaulting to IRE");
        }
        String totalVaccinationsInput = Console.getString("Enter Total Vaccinations: ");
        int totalVaccinations = 0;
        if (totalVaccinationsInput.isBlank()) {
            totalVaccinations = 1_000_000;
            Console.displayLine("Defaulting to 1000000");
        } else {
            totalVaccinations = Integer.parseInt(totalVaccinationsInput);
        }
        String peopleVaccinatedInput = Console.getString("Enter People Vaccinated: ");
        int peopleVaccinated;
        if (peopleVaccinatedInput.isBlank()) {
            peopleVaccinated = 500_000;
            Console.displayLine("Defaulting to 500000");
        } else {
            peopleVaccinated = Integer.parseInt(peopleVaccinatedInput);
        }
        String totalPeopleVaccinatedInput = Console.getString("Enter Total People Vaccinated: ");
        int totalPeopleVaccinated;
        if (totalPeopleVaccinatedInput.isBlank()) {
            totalPeopleVaccinated = 500_000;
            Console.displayLine("Defaulting to 500000");
        } else {
            totalPeopleVaccinated = Integer.parseInt(totalPeopleVaccinatedInput);
        }
        String dailyVaccinatedInput = Console.getString("Enter Daily Vaccinations: ");
        int dailyVaccinated;
        if (dailyVaccinatedInput.isBlank()) {
            dailyVaccinated = 10_000;
            Console.displayLine("Defaulting to 10000");
        } else {
            dailyVaccinated = Integer.parseInt(dailyVaccinatedInput);
        }
        String vaccines = Console.getString("Enter Vaccines: ");
        if (vaccines.isBlank()) {
            vaccines = "Pfizer";
            Console.displayLine("Defaulting to Pfizer");
        }

        CountryVaccinationData countryVaccinationData = new CountryVaccinationData(0,
                country,
                isoCode,
                new Date(),
                totalVaccinations,
                peopleVaccinated,
                totalPeopleVaccinated,
                dailyVaccinated,
                vaccines);

        repository.insertNewRecord(countryVaccinationData);
        Console.displayLine();
    }

    private void taskThree() throws SQLException, ParseException {
        String beginDateInput = Console.getString("Enter first date in the format yyyy-MM-dd: ");
        if (beginDateInput.isBlank()) {
            beginDateInput = "2021-01-01";
            Console.displayLine("Defaulting to 2021-01-01");
        }
        Date beginDate = dateFormat.parse(beginDateInput);
        String endDateInput = Console.getString("Enter second date in the format yyyy-MM-dd: ");
        if (endDateInput.isBlank()) {
            endDateInput = "2024-08-30";
            Console.displayLine("Defaulting to 2024-08-30");
        }
        Date endDate = dateFormat.parse(endDateInput);
        String isoCode = Console.getString("Enter ISO Code: ");
        if (isoCode.isBlank()) {
            isoCode = "IRE";
            Console.displayLine("Defaulting to IRE");
        }
        repository.calculateAverageDailyVaccinations(new java.sql.Date(beginDate.getTime()),
                new java.sql.Date(endDate.getTime()),
                isoCode);

        Console.displayLine();
    }

    private void taskFour() throws SQLException {
        int id = Console.getInt("Enter Record ID: ");
        repository.retrieveRecordById(id);
        Console.displayLine();
    }
}
