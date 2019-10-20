package com.cgi.smartcv.calculator;

import com.cgi.smartcv.dto.Boiler;

import java.util.ArrayList;

public class CalcRequest {
    private long startDate;
    private long endDate;
    private String period;
    private String value;

    public CalcRequest(long startDate, long endDate, String period, String value) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.period = period;
        this.value = value;
    }

    public ArrayList<CalculationObject> getCalculation(Iterable<Boiler> boilers) {
        if(value.equals("temperature"))
            return new AverageCalculator().calculateAverage(boilers, startDate, endDate, period, value);
        else
            return new SumCalculator().calculateSum(boilers, startDate, endDate, period, value);
    }
}
