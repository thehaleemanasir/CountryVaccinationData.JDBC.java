package com.markcrowe.jdbc.commandline;

import com.markcrowe.jdbc.DatabaseUtility;
import com.markcrowe.jdbc.ascii.ASCIIArtGenerator;
import com.markcrowe.jdbc.countryvaccinationdata.CountryVaccinationData;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    DecimalFormat twoDigits = new DecimalFormat("0.00");
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final CountryRepository repository;

    public static void main(String[] args) throws SQLException {

        try {
            new Main();
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Main() throws ParseException, SQLException {

        repository = new CountryRepository(DatabaseUtility.getConnection());

        Console.displayLine("    Welcome to the Country Vaccination Data 2021 Database Main Menu");
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
                    String country = Console.getString("Enter country: ");
                    String isoCode = Console.getString("Enter ISO Code: ");
                    Date now = new Date();
                    String totalVaccinationsInput = Console.getString("Enter Total Vaccinations: ");
                    int totalVaccinations = Console.getInt("Enter Total Vaccinations: ");
                    int peopVacc = Console.getInt("Enter People Vaccinated: ");
                    int totPeopVacc = Console.getInt("Enter People Fully Vaccinated: ");
                    int dailyVacc = Console.getInt("Enter Daily Vaccinations: ");
                    String vaccines = Console.getString("Enter Vaccines: ");
                    CountryVaccinationData c = new CountryVaccinationData(choice, country, isoCode, now, totalVaccinations, peopVacc, totPeopVacc, dailyVacc, vaccines);
                    taskTwo(c);
                    break;
                case 3:
                    String beginDateInput = Console.getString("Enter first date in the format yyyy-MM-dd: ");
                    if (beginDateInput.isBlank()) {
                        beginDateInput = "2021-01-01";
                        Console.displayLine("Defaulting to 2021-01-01");
                    }
                    Date beginDate = dateFormat.parse(beginDateInput);
                    String endDateInput = Console.getString("Enter second date in the format yyyy-MM-dd: ");
                    if (endDateInput.isBlank()) {
                        endDateInput = "2024-08-30";
                        System.out.println("Defaulting to 2024-08-30");
                    }
                    Date endDate = dateFormat.parse(endDateInput);
                    isoCode = Console.getString("Enter ISO Code: ");
                    if (isoCode.isBlank()) {
                        isoCode = "IRE";
                        System.out.println("Defaulting to IRE");
                    }
                    taskThree(beginDate, endDate, isoCode);
                    break;
                case 4:
                    int id = Console.getInt("Enter Record ID: ");
                    taskFour(id);
                    break;

            }//end switch

            Console.displayLine();

        }//end while

        //display ASCII art before exiting
        ASCIIArtGenerator art = new ASCIIArtGenerator();
        try {
            art.printTextArt("Bye!", 14, ASCIIArtGenerator.ASCIIArtFont.ART_FONT_DIALOG, "+");
            System.exit(0);
        } catch (Exception ex) {
            Console.displayLine("Error with ASCII art " + ex);
        }//end try
    }

    private void taskOne() throws SQLException {
        String isoCode = Console.getString("Enter ISO Code: ");

        repository.searchByIsoCode(isoCode);
        Console.displayLine();
    }

    private void taskTwo(CountryVaccinationData c) throws SQLException {
        //ToDo Task 2
        repository.insertNewRecord(c);
        Console.displayLine();
    }

    private void taskThree(Date sDate, Date eDate, String isoCode) throws SQLException {
        repository.calculateAverageDailyVaccinations(new java.sql.Date(sDate.getTime()),
                new java.sql.Date(eDate.getTime()),
                isoCode);

        Console.displayLine();
    }

    private void taskFour(int id) throws SQLException {
        //ToDo Task 4
        repository.retrieveRecordById(id);
        Console.displayLine();

    }

}//end class
