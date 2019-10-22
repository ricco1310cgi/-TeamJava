package com.cgi.smartcv.persistence;

import com.cgi.smartcv.calculator.CalcRequest;
import com.cgi.smartcv.calculator.CalculationObject;
import com.cgi.smartcv.dto.Boiler;
import com.cgi.smartcv.dto.BoilerController;
import com.cgi.smartcv.dto.BoilerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

@Service
@Transactional
public class BoilerService {

    private BoilerRepository boilerRepository;
    private BoilerController boilerController;
    private BoilerConverter boilerConverter;

    @Autowired
    public BoilerService(BoilerRepository boilerRepository) {
        this.boilerRepository = boilerRepository;
    }

    public BoilerService() {
    }

    public Iterable<Boiler> findAll() throws IOException, InterruptedException {
        Iterable<Boiler> result = boilerRepository.findAll();
        return result;
    }

    public Boiler save(Boiler boiler) {
        return boilerRepository.save(boiler);
    }

    public boolean startBoiler() throws IOException, InterruptedException {
        boilerController = new BoilerController();
        return boilerController.connectBoiler();
    }

    public boolean isBoilerRunning() {
        boilerController = new BoilerController();
        return boilerController.getBoilerAliveState();
    }

    public float findTemperature() {
        Iterable<Boiler> boilers = boilerRepository.findAll();
        float tempInside = 0;
        long timeRecorder = 0;
        //find last (highest timeRecorder) boiler temperature in database
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > timeRecorder) {
                timeRecorder = b.getTimeRecorder();
                tempInside = b.getTempInside();
            }
        }
        return tempInside;
    }

    public long findLastEpochTime() {
        Iterable<Boiler> boilers = boilerRepository.findAll();
        long timeRecorder = 0;
        //find last timeRecorder of boiler in database
        for (Boiler b : boilers) {
            if (b.getTimeRecorder() > timeRecorder) {
                timeRecorder = b.getTimeRecorder();
            }
        }
        return timeRecorder;
    }

    public ArrayList<CalculationObject> getCalculation(long startDate, long endDate, String period, String value) {
        Iterable<Boiler> boilers = boilerRepository.findAll();
        CalcRequest calcRequest = new CalcRequest(startDate, endDate, period, value);
        ArrayList<CalculationObject> calculations = calcRequest.getCalculation(boilers);
        return calculations;
    }

    public float convertIntToFloat(int intNumber) {
        String parser = String.valueOf(intNumber);
        BigInteger bigInteger = new BigInteger(parser);
        float floatValue = bigInteger.floatValue();
        float convertedResult = floatValue / 10;
        return convertedResult;
    }

    public long convertFloatToEpoch(float minutesOfIncreasingTemperature) {
        long minutesInLong = (long) minutesOfIncreasingTemperature;
        System.out.println("minutes in long " + minutesInLong);
        long epoch = minutesInLong * 60;
        System.out.println("minutes in epoch " + epoch);
        return epoch;
    }


    public Boiler saveData(Boiler boiler) {
        Boiler save = boilerRepository.save(boiler);
        return save;
    }

    public Boiler convertString(String input, Boiler inputBoiler) {
        boilerConverter = new BoilerConverter();
        return boilerConverter.convertStringToBoilerDTO(input, inputBoiler);
    }

    public Boiler getCurrentBoiler(Boiler boiler) throws IOException {
        boiler = boilerController.outputBoiler();
        return boiler;
    }

    public Iterable<Boiler> findAllByOrderByIdDesc() {
        Iterable<Boiler> result = boilerRepository.findAllByOrderByIdDesc();
        return result;
    }

    public boolean setTemperature(int temperatureId) {
        float floatNumber = convertIntToFloat(temperatureId);
        return boilerController.modifyTemperatureBoiler(floatNumber, findTemperature());
    }

    public long setTimer(int temperatureId, long setTime) {
        float floatNumber = convertIntToFloat(temperatureId);
        return boilerController.setTimerWithTemperatureAndTime(floatNumber, setTime, findLastEpochTime(), findTemperature());
    }
}
