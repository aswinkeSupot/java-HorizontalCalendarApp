package com.aswin.horizontalcalendar.utils.utilities;

import java.util.Calendar;

/**
 * Created by Aswin on 27-09-2024.
 */
public class CommonUtils {

    public static String getEnglishMonth(String month) {
        switch (month) {
            case "01":
            case "1":
                return "January";
            case "02":
            case "2":
                return "February";
            case "03":
            case "3":
                return "March";
            case "04":
            case "4":
                return "April";
            case "05":
            case "5":
                return "May";
            case "06":
            case "6":
                return "June";
            case "07":
            case "7":
                return "July";
            case "08":
            case "8":
                return "August";
            case "09":
            case "9":
                return "September";
            case "10":
                return "October";
            case "11":
                return "November";
            case "12":
                return "December";
        }
        return month;
    }

    public static String getEnglishMonthShort(String month) {
        switch (month) {
            case "01":
            case "1":
                return "Jan";
            case "02":
            case "2":
                return "Feb";
            case "03":
            case "3":
                return "Mar";
            case "04":
            case "4":
                return "Apr";
            case "05":
            case "5":
                return "May";
            case "06":
            case "6":
                return "Jun";
            case "07":
            case "7":
                return "Jul";
            case "08":
            case "8":
                return "Aug";
            case "09":
            case "9":
                return "Sep";
            case "10":
                return "Oct";
            case "11":
                return "Nov";
            case "12":
                return "Dec";
        }
        return month;
    }

    public static String getWeekDay(int day) {
        switch (day) {
            case 1:
                return "Sun";
            case 2:
                return "Mon";
            case 3:
                return "Tue";
            case 4:
                return "Wed";
            case 5:
                return "Thu";
            case 6:
                return "Fri";
            case 7:
                return "Sat";
        }
        return "";
    }

    public static long dateCheck(int selectedDate, int selectedMonth, int selectedYear) {
        Calendar myCalendar = Calendar.getInstance();
        Calendar myCalendarSelected = Calendar.getInstance();
        myCalendar.set(Calendar.HOUR, 0);
        myCalendar.set(Calendar.MINUTE, 0);
        myCalendar.set(Calendar.SECOND, 0);
        myCalendar.set(Calendar.MILLISECOND, 0);
        myCalendarSelected.set(Calendar.HOUR, 0);
        myCalendarSelected.set(Calendar.MINUTE, 0);
        myCalendarSelected.set(Calendar.SECOND, 0);
        myCalendarSelected.set(Calendar.MILLISECOND, 0);
        myCalendarSelected.set(Calendar.DATE, selectedDate);
        myCalendarSelected.set(Calendar.MONTH, selectedMonth);
        myCalendarSelected.set(Calendar.YEAR, selectedYear);
        System.out.println("Difference" + (((myCalendarSelected.getTimeInMillis() / 1000) - (myCalendar.getTimeInMillis() / 1000)) / 86400));
        return (((myCalendarSelected.getTimeInMillis() / 1000) - (myCalendar.getTimeInMillis() / 1000)) / 86400);
    }
}
