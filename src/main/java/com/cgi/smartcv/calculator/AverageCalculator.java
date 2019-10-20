package com.cgi.smartcv.calculator;

import com.cgi.smartcv.dto.Boiler;

import java.time.*;
import java.util.ArrayList;

public class AverageCalculator {
    public ArrayList<CalculationObject> calculateAverage(Iterable<Boiler> boilers, long startDate, long endDate, String period, String value) {
        ArrayList<CalculationObject> averages = new ArrayList<>();
        long firstDay = startDate;
        long lastDay = endDate;
        long firstDayMidnight = getFirstDayMidnight(firstDay);
        long dayCounter = firstDayMidnight;

        while (dayCounter < lastDay) {
            long dayInSeconds = getDayInSeconds(dayCounter);
            float average = getAverageDay(boilers, dayCounter);
            CalculationObject averageObject = new CalculationObject(dayCounter, average, period, value);
            if(average == -1000){
                averageObject.setValue("temperature not available");
            }
            averages.add(averageObject);
            dayCounter += dayInSeconds;
        }

        if(period.equalsIgnoreCase("month")){
            averages = getAverageMonth(averages, period, value);
        }
        if(period.equalsIgnoreCase("year")){
            averages = getAverageYear(averages, period, value);
        }
        return averages;
    }

    long getDayInSeconds(long dayCounter) {
        LocalDateTime beginDayDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(dayCounter), ZoneId.systemDefault());
        LocalDateTime endDayDateTime = beginDayDateTime.plusDays(1);
        long beginDayLong = beginDayDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        long endDayLong = endDayDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
        return endDayLong - beginDayLong;
    }


    long getFirstDayOfMonth(long beginDay) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(beginDay), ZoneId.systemDefault());
        dateTime = dateTime.minusDays(dateTime.getDayOfMonth() - 1);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    long getFirstDayOfYear(long beginDay) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(beginDay), ZoneId.systemDefault());
        dateTime = dateTime.minusDays(dateTime.getDayOfYear() - 1);
        return dateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    private float getAverageDay(Iterable<Boiler> boilers, long dayCounter) {
        float average = 0;
        int count = 0;

        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > dayCounter && b.getTimeRecorder() < dayCounter + 86400) {
                average += b.getTempInside();
                count++;
            }
        }
        if(count == 0){
            average = -1000;
        } else {
            average = average / count;
        }
        return average;
    }

    long getFirstDayMidnight(long firstDay) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(firstDay), ZoneId.systemDefault());
        return dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    int getMonthValue(CalculationObject calculationObject){
        long beginDay = calculationObject.getBeginDay();
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(beginDay), ZoneId.systemDefault()).getMonthValue();
    }

    private ArrayList<CalculationObject> getAverageMonth(ArrayList<CalculationObject> averages, String period, String value) {
        int count = 0;
        float average = 0;
        ArrayList<CalculationObject> monthAverages = new ArrayList<>();
        for( int i = 0; i < averages.size(); i++){
            int monthValueCurrentIteration = getMonthValue(averages.get(i));
            int monthValueNextIteration = 0;
            if(i != averages.size() -1) {
                monthValueNextIteration = getMonthValue(averages.get(i + 1));
            }
            if(averages.get(i).getValue().equals(value)) {
                average += averages.get(i).getCalculation();
                count++;
            }
            if(monthValueCurrentIteration != monthValueNextIteration && count != 0){
                average = average/count;
                monthAverages.add(new CalculationObject(getFirstDayOfMonth(averages.get(i).getBeginDay()), average, period, value));
                average = 0;
                count = 0;
            } else if(monthValueCurrentIteration != monthValueNextIteration){
                monthAverages.add(new CalculationObject(getFirstDayOfMonth(averages.get(i).getBeginDay()), -1000, period, "temperature not available"));
            }
        }
        return monthAverages;
    }

    private ArrayList<CalculationObject> getAverageYear(ArrayList<CalculationObject> monthAverages, String period, String value) {
        int count = 0;
        float average = 0;
        ArrayList<CalculationObject> yearAverages = new ArrayList<>();
        monthAverages = getAverageMonth(monthAverages, period, value);
        for( int i = 0; i < monthAverages.size(); i++){
            int monthValueCurrentIteration = getMonthValue(monthAverages.get(i));
            int monthValueNextIteration = 0;
            if(i != monthAverages.size() - 1){
                monthValueNextIteration = getMonthValue(monthAverages.get(i+1));
            }
            if(monthAverages.get(i).getValue().equals(value)) {
                average += monthAverages.get(i).getCalculation();
                count++;
            }
            if(monthValueCurrentIteration == 12 && monthValueNextIteration == 1 && count != 0){
                average = average/count;
                yearAverages.add(new CalculationObject(getFirstDayOfYear(monthAverages.get(i).getBeginDay()), average, period, value));
                average = 0;
                count = 0;
            }  else if(monthValueCurrentIteration == 12 && monthValueNextIteration == 1){
                yearAverages.add(new CalculationObject(getFirstDayOfYear(monthAverages.get(i).getBeginDay()), -1000, period, "temperature not available"));
            }
        }
        return yearAverages;
    }

    private long getFirstDay(Iterable<Boiler> boilers) {
        long firstDay = 0;
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() < firstDay) {
                firstDay = b.getTimeRecorder();
            }
        }
        return firstDay;
    }

    private long getLastDay(Iterable<Boiler> boilers) {
        long lastDay = 0;
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > lastDay) {
                lastDay = b.getTimeRecorder();
            }
        }
        return lastDay;
    }

}
