package com.teamjava.SmartCV;

import com.teamjava.SmartCV.domain.BoilerDTO;

import java.util.Arrays;

public class BoilerConverter {
    private String inputString;
    private String outputString;
    private BoilerDTO boilerDTO;

    public String sendCommand(String inputString) {
        String outputString = "";

        return outputString;
    }

    public BoilerDTO convertStringToBoilerBTO(String inputString) {
        // Example input
        // #STAT#161#15.62#10.20#1#1488813845#0.03#1488814881
        StringBuilder stb = new StringBuilder(inputString);
        String[] inputs = inputString.split("#");
        System.out.println(Arrays.toString(inputs));

        return boilerDTO;
    }
}
