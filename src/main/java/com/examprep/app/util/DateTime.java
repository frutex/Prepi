package com.examprep.app.util;



import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateTime {

	private int year;
	private int month;
	private int day;
	
	
	public Boolean DateTime(int year, int month, int day) {
        
		if(year > Calendar.getInstance().get(Calendar.YEAR)){
        	return false;
        }
        if (month > 12 || month < 1){
        	return false;
        }
        Calendar mycal = new GregorianCalendar(year, month, 1);
        if (day > mycal.getActualMaximum(Calendar.DAY_OF_MONTH) || day < 1){
        	return false;
        }
		this.year = year;
		this.month = month;
		this.day = day;
		return true;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

}
