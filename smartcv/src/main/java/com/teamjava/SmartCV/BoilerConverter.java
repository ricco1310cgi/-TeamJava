package com.teamjava.SmartCV;

import com.teamjava.SmartCV.domain.BoilerDTO;

import java.util.Arrays;

public class BoilerConverter {
    private String inputString;
    private String outputString;
    //private BoilerDTO boilerDTO;

    public String sendCommand(String inputString) {
        String outputString = "";

        return outputString;
    }

    public BoilerDTO convertStringToBoilerDTO(String inputString, BoilerDTO boilerDTO) {
        // Example input
        // #STAT#161#15.62#10.20#1#1488813845#0.03#1488814881
        String[] inputs = inputString.split("#");
        System.out.println(Arrays.toString(inputs));

        // Fill the boilerDTO
        boilerDTO = new BoilerDTO(Integer.parseInt(inputs[2]),
                Float.parseFloat(inputs[3]),
                Float.parseFloat(inputs[4]),
                Boolean.parseBoolean(inputs[5]),
                Long.parseLong(inputs[6]),
                Float.parseFloat(inputs[7]),
                Long.parseLong(inputs[8])
        );
        return boilerDTO;
    }
}
