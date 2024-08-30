package com.markcrowe.jdbc.utils;

import java.util.Scanner;

public class Console
{

	private static Scanner scanner = new Scanner(System.in);

	public static void displayLine()
	{
		System.out.println();
	}

	public static void displayLine(String s)
	{
		System.out.println(s);
	}

	public static String getString(String prompt)
	{
		System.out.println(prompt);
		return scanner.nextLine();
	}

	public static int getInt(String prompt)
	{
		int i = 0;
		while(true)
		{
			System.out.println(prompt);
			try
			{
				i = Integer.parseInt(scanner.nextLine());
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Error! Invalid integer. Try again.");
			}
		}
		return i;
	}

	public static double getDouble(String prompt)
	{
		double d = 0;
		while(true)
		{
			System.out.println(prompt);
			try
			{
				d = Double.parseDouble(scanner.nextLine());
				break;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Error! Invalid decimal. Try again.");
			}
		}
		return d;
	}
	private Console()
	{
	}

	public static void waitForEnter() {
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
	}
}
