package com.cgi.smartcv.calculator;

import com.cgi.smartcv.dto.Boiler;

import java.time.*;
import java.util.ArrayList;

public class AverageCalculator {
    public ArrayList<Float> calculateAverage(Iterable<Boiler> boilers) {
        ArrayList<Float> averages = new ArrayList<>();
        long firstDay = getFirstDay(boilers);
        long lastDay = getLastDay(boilers);
        long firstDayMidnight = getFirstDayMidnight(firstDay);
        long dayCounter = firstDayMidnight;
        long DayInSeconds = 86400;

        while (dayCounter < lastDay) {
            float average = getAverage(boilers, dayCounter);
            averages.add(average);
            dayCounter += DayInSeconds;
        }
        return averages;
    }

    private float getAverage(Iterable<Boiler> boilers, long dayCounter) {
        float average = 0;
        int count = 0;

        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > dayCounter && b.getTimeRecorder() < dayCounter + 86400) {
                average += b.getTempInside();
                count++;
            }
        }
        average = average / count;
        return average;
    }

    private long getFirstDayMidnight(long firstDay) {
        LocalDateTime dateTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(firstDay), ZoneId.systemDefault());
        LocalDate date = dateTime.toLocalDate();
        LocalTime midnight = LocalTime.of(0,0,0);
        dateTime = LocalDateTime.of(date,midnight);
        ZoneId zoneId = ZoneId.systemDefault();
        return dateTime.atZone(zoneId).toEpochSecond();
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
