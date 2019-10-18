package com.cgi.smartcv.calculator;

import com.cgi.smartcv.dto.Boiler;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalcRequest {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String period;
    private String value;

    public CalcRequest(LocalDateTime startDate, LocalDateTime endDate, String period, String value) {
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
