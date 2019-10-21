package com.cgi.smartcv.calculator;

import com.cgi.smartcv.dto.Boiler;

import java.util.ArrayList;

public class SumCalculator {
    public ArrayList<CalculationObject> calculateSum(Iterable<Boiler> boilers, long startDate, long endDate, String period, String value) {
        ArrayList<CalculationObject> sums = new ArrayList<>();
        long firstDay = startDate;
        long lastDay = endDate;
        long firstDayMidnight = new AverageCalculator().getFirstDayMidnight(firstDay);
        long dayCounter = firstDayMidnight;

        while (dayCounter < lastDay) {
            long dayInSeconds = new AverageCalculator().getDayInSeconds(dayCounter);
            float sum = getSumDay(boilers, dayCounter);
            CalculationObject sumObject = new CalculationObject(dayCounter, sum, period, value);
            if (!isSumAvailable(boilers, dayCounter)) {
                sumObject.setValue("gas usage not available");
            }
            sums.add(sumObject);
            dayCounter += dayInSeconds;
        }

        if (period.equalsIgnoreCase("month")) {
            sums = getSumMonth(sums, period, value);
        }
        if (period.equalsIgnoreCase("year")) {
            sums = getSumYear(sums, period, value);
        }
        return sums;
    }

    private boolean isSumAvailable(Iterable<Boiler> boilers, long dayCounter) {
        float count = 0;
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > dayCounter && b.getTimeRecorder() < dayCounter + 86400) {
                count++;
            }
        }
        return count > 0;
    }

    private float getSumDay(Iterable<Boiler> boilers, long dayCounter) {
        float sum = 0;
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > dayCounter && b.getTimeRecorder() < dayCounter + 86400) {
                sum += b.getGasUsage();
            }
        }
        return sum;
    }

    private ArrayList<CalculationObject> getSumMonth(ArrayList<CalculationObject> sums, String period, String value) {
        ArrayList<CalculationObject> monthSums = new ArrayList<>();
        AverageCalculator averageCalculator = new AverageCalculator();
        float sum = 0;
        int count = 0;
        for (int i = 0; i < sums.size(); i++) {
            int monthValueCurrentIteration = averageCalculator.getMonthValue(sums.get(i));
            int monthValueNextIteration = 0;
            if (i != sums.size() - 1) {
                monthValueNextIteration = averageCalculator.getMonthValue(sums.get(i + 1));
            }
            if (sums.get(i).getValue().equals(value)) {
                sum += sums.get(i).getCalculation();
                count++;
            }
            if (monthValueCurrentIteration != monthValueNextIteration && count != 0) {
                monthSums.add(new CalculationObject(averageCalculator.getFirstDayOfMonth(sums.get(i).getBeginDay()), sum, period, value));
                sum = 0;
                count = 0;
            } else if (monthValueCurrentIteration != monthValueNextIteration) {
                monthSums.add(new CalculationObject(averageCalculator.getFirstDayOfMonth(sums.get(i).getBeginDay()), sum, period, "gas usage not available"));
            }
        }
        return monthSums;
    }

    private ArrayList<CalculationObject> getSumYear(ArrayList<CalculationObject> monthSums, String period, String value) {
        AverageCalculator averageCalculator = new AverageCalculator();
        ArrayList<CalculationObject> yearSums = new ArrayList<>();
        monthSums = getSumMonth(monthSums, period, value);
        float sum = 0;
        int count = 0;
        for (int i = 0; i < monthSums.size(); i++) {
            int monthValueCurrentIteration = averageCalculator.getMonthValue(monthSums.get(i));
            int monthValueNextIteration = 0;
            if (i != monthSums.size() - 1) {
                monthValueNextIteration = averageCalculator.getMonthValue(monthSums.get(i + 1));
            }
            if (monthSums.get(i).getValue().equals(value)) {
                sum += monthSums.get(i).getCalculation();
                count++;
            }
            if (monthValueCurrentIteration == 12 && monthValueNextIteration == 1 && count != 0) {
                yearSums.add(new CalculationObject(new AverageCalculator().getFirstDayOfYear(monthSums.get(i).getBeginDay()), sum, period, value));
                sum = 0;
                count = 0;
            } else if (monthValueCurrentIteration == 12 && monthValueNextIteration == 1) {
                yearSums.add(new CalculationObject(new AverageCalculator().getFirstDayOfYear(monthSums.get(i).getBeginDay()), sum, period, "gas usage not available"));
            }
        }
        return yearSums;
    }
}
