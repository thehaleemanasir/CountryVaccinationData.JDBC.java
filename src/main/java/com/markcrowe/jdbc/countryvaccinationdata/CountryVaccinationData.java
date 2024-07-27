package com.markcrowe.jdbc.countryvaccinationdata;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CountryVaccinationData
{
	private int id;
	private String country;
	private String isoCode;
	private Date date;
	private int totalVaccinations;
	private int peopleVaccinated;
	private int peopleFullyVaccinated;
	private int dailyVaccinations;
	private String vaccines;
}
