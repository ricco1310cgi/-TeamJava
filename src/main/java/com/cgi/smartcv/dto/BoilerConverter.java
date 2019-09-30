package com.cgi.smartcv.dto;

import java.util.Arrays;

public class BoilerConverter {

    public Boiler convertStringToBoilerDTO(String inputString, Boiler boiler) {
        // Example input
        // #STAT#161#15.62#10.20#1#1488813845#0.03#1488814881
        String[] inputs = inputString.split("#");
        System.out.println(Arrays.toString(inputs));

        // Fill the boilerDTO
        boiler = new Boiler(Integer.parseInt(inputs[2]),
                Float.parseFloat(inputs[3]),
                Float.parseFloat(inputs[4]),
                Boolean.parseBoolean(inputs[5]),
                Long.parseLong(inputs[6]),
                Float.parseFloat(inputs[7]),
                Long.parseLong(inputs[8])
        );
        return boiler;
    }
}
