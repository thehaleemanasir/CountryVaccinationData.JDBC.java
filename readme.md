# Country Vaccination Data 2021 Database

`country_vaccination_data_2021` is a database of that contains a table called `country_vaccination_data`.  The table contains complete information on all types ...  

| Field                   | Type        | Null | Key | Default | Extra          |
|-------------------------|-------------|------|-----|---------|----------------|
| id                      | int         | NO   | PRI |         | auto_increment |
| country                 | varchar(24) | YES  |     |         |                |
| iso_code                | varchar(3)  | YES  |     |         |                |
| date                    | date        | YES  |     |         |                |
| total_vaccinations      | int         | YES  |     |         |                |
| people_vaccinated       | int         | YES  |     |         |                |
| people_fully_vaccinated | int         | YES  |     |         |                |
| daily_vaccinations      | int         | YES  |     |         |                |
| vaccines                | varchar(82) | YES  |     |         |                |

>Table Structure

| id   | country | iso_code | date       | total_vaccinations | people_vaccinated | people_fully_vaccinated | daily_vaccinations | vaccines |
|------|---------|----------|------------|--------------------|-------------------|-------------------------|--------------------|----------|
| 1367 | Ireland | IRL      | 2020-12-31 | 1800               | 0                 | 0                       | 0                  | 6, 3, 1  |
| 1368 | Ireland | IRL      | 2021-01-01 | 0                  | 0                 | 0                       | 550                | 6, 3, 1  |
| 1369 | Ireland | IRL      | 2021-01-02 | 0                  | 0                 | 0                       | 550                | 6, 3, 1  |
| 1370 | Ireland | IRL      | 2021-01-03 | 0                  | 0                 | 0                       | 550                | 6, 3, 1  |
| 1371 | Ireland | IRL      | 2021-01-04 | 4000               | 0                 | 0                       | 550                | 6, 3, 1  |
| 1372 | Ireland | IRL      | 2021-01-05 | 0                  | 0                 | 0                       | 1194               | 6, 3, 1  |
| 1373 | Ireland | IRL      | 2021-01-06 | 0                  | 0                 | 0                       | 1624               | 6, 3, 1  |
| 1374 | Ireland | IRL      | 2021-01-07 | 15314              | 0                 | 0                       | 1931               | 6, 3, 1  |

>Table Sample Data

*Note also that the database is not normalized.*

For this assignment you must download a starter NetBeans project from Moodle. The project contains a SQL script that you will need for this assignment. This starter project also contains a class called *Main*. When you run this class, it presents the user with a menu like the following: 

```out
    Welcome to the Country Vaccination Data 2021 Database Main Menu

1:  Task 1
2:  Task 2
3:  Task 3
4:  Task 4
-1: Quit

Enter Choice: 
```

## Task 1: (30 marks)

If the user selects **option 1**, they will be prompted to enter an *value* from the keyboard - an ISO code. This value represents a search criteria.A method called `taskOne()` will then be called and the *ISO code* entered from the keyboard will be passed onto this method. Here you must search the database for and display all countries that match the specified iso code. You must indicate how may records were returned. When displaying the data ensure that the records are ordered by date in descending order.

For example:  

```out
1:  Task 1
2:  Task 2
3:  Task 3
4:  Task 4
-1: Quit

Enter Choice: 
```

If no records return from the database, a suitable error message must be returned instead  

## Task 2: (30 marks)

If the user selects option 2, they will be prompted to enter a series of values. These values are used to create a Countries object which is passed to a method `taskTwo()`. This method must then use a prepared statement to insert the data as a new record into the countries table. For any records that are inserted into the database you must ensure that the current date and time will be used for the date field. Once the record has been inserted you should print a conformation message to the screen. For example:

```out
1:  Task 1
2:  Task 2
3:  Task 3
4:  Task 4
-1: Quit

Enter Choice: 
```

## Task 3: (20 marks)

If the user selects options 3, the user will be prompted to enter 3 pieces of data:

1. A starting date (in the format yyyy-MM-dd).
2. An ending date (in format yyyy-MM-dd).
3. An iso code.  

A method `taskThree()` is then called with the users input passed to it. The method will print the average number of daily vaccinations (formatted to 2 decimal places) between the starting date and the ending for the specified ISO code.

For example:  

```out
1:  Task 1
2:  Task 2
3:  Task 3
4:  Task 4
-1: Quit

Enter Choice: 
```

## Task 4: (20 marks)

If the user selects **option 4**,they will be prompted to enter a record number/ID. A method taskFour() will be called with the record number/ID passed to it. In this method you must call a stored procedure getCountryByID. This procedure accepts a record number/ID as a parameter and will return the corresponding record from the database which you must display on the console.

For example:  

```out
1:  Task 1
2:  Task 2
3:  Task 3
4:  Task 4
-1: Quit

Enter Choice: 
```
