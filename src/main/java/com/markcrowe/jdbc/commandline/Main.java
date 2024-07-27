package com.markcrowe.jdbc.commandline;

import com.markcrowe.jdbc.ascii.ASCIIArtGenerator;
import com.markcrowe.jdbc.countryvaccinationdata.CountryVaccinationData;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main
{

	DecimalFormat twoDigits = new DecimalFormat("0.00");
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args)
	{

		try
		{
			new Main();
		}
		catch(ParseException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public Main() throws ParseException
	{

		// displayLine a welcome message
		Console.displayLine("    Welcome to the Country Vaccination Data 2021 Database Main Menu");
		Console.displayLine();

		int choice = 0;

		while(choice != -1)
		{

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
			switch(choice)
			{

				case 1:
					String isoCode = Console.getString("Enter ISO Code: ");
					taskOne(isoCode);
					break;
				case 2:
					String country = Console.getString("Enter country: ");
					isoCode = Console.getString("Enter ISO Code: ");
					Date now = new Date();
					int totVacc = Console.getInt("Enter Total Vaccinations: ");
					int peopVacc = Console.getInt("Enter People Vaccinated: ");
					int totPeopVacc = Console.getInt("Enter People Fully Vaccinated: ");
					int dailyVacc = Console.getInt("Enter Daily Vaccinations: ");
					String vaccines = Console.getString("Enter Vaccines: ");
					CountryVaccinationData c = new CountryVaccinationData(choice, country, isoCode, now, totVacc, peopVacc, totPeopVacc, dailyVacc, vaccines);
					taskTwo(c);
					break;
				case 3:
					Date sDate = dateFormat.parse(Console.getString("Enter first date in the format yyyy-MM-dd: "));
					Date eDate = dateFormat.parse(Console.getString("Enter second date in the format yyyy-MM-dd: "));
					isoCode = Console.getString("Enter ISO Code: ");
					taskThree(sDate, eDate, isoCode);
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
		try
		{
			art.printTextArt("Bye!", 14, ASCIIArtGenerator.ASCIIArtFont.ART_FONT_DIALOG, "+");
			System.exit(0);
		}
		catch(Exception ex)
		{
			Console.displayLine("Error with ASCII art " + ex);
		}//end try
	}

	private void taskOne(String iso_code)
	{
		//ToDo Task 1

		Console.displayLine();
	}

	private void taskTwo(CountryVaccinationData c)
	{
		//ToDo Task 2

		Console.displayLine();
	}

	private void taskThree(Date sDate, Date eDate, String iso_code)
	{
		//ToDo Task 3

		Console.displayLine();
	}

	private void taskFour(int id)
	{
		//ToDo Task 4
	}

}//end class
