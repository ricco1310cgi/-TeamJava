package com.teamjava.SmartCV;

import java.io.IOException;

public class BoilerController {
    private static BoilerDTO boilerDTO;
    private static String boilerOutputString;
    private static BoilerReader boilerReader;
    private static BoilerWriter boilerWriter;
    private static BoilerConverter boilerConverter;

    public static void main(String[] args) throws IOException {
        String boilerOutputString = "";

        boilerReader = new BoilerReader();
        boilerOutputString = boilerReader.readFromBoiler(7777);
    }
}
