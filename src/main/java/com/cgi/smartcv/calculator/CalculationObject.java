package com.cgi.smartcv.calculator;

public class CalculationObject {
    private long beginDay;
    private float calculation;
    private String period;
    private String value;

    public CalculationObject(long beginDay, float average, String period, String value){
        this.beginDay = beginDay;
        this.calculation = average;
        this.period = period;
        this.value = value;
    }

    public long getBeginDay(){
        return beginDay;
    }

    public void setBeginDay(long beginDay){
        this.beginDay = beginDay;
    }

    public float getCalculation(){
        return calculation;
    }

    public void setCalculation(float calculation) {
        this.calculation = calculation;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
