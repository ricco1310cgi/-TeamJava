package com.teamjava.SmartCV;

import java.io.IOException;
import com.teamjava.SmartCV.domain.BoilerDTO;

public class BoilerController {
    // Declare all the other Boiler objects required by BoilerController to run
    private static BoilerDTO boilerDTO;
    private static BoilerReader boilerReader;
    private static BoilerWriter boilerWriter;
    private static BoilerConverter boilerConverter;

    public static void main(String[] args) throws IOException {
        // Instantiate all Boiler objects
        boilerReader = new BoilerReader();
        boilerDTO = new BoilerDTO();
        boilerConverter = new BoilerConverter();

        // Reads the String from the cv simulator on port 7777 !ASSIGN SECRET KEY IN CLASS!
        String boilerOutputString = boilerReader.readFromBoiler(7777);
        // Create a BoilerBTO object from the String boilerOutputString
        boilerDTO = boilerConverter.convertStringToBoilerBTO("#STAT#161#15.62#10.20#1#1488813845#0.03#1488814881");
    }

}
