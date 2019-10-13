package com.cgi.smartcv.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class BoilerConnector {
    private static final String RELATIVE_PATH_CV_SIMULATOR = "..\\-TeamJava\\src\\main\\resources\\boilerSimulator\\CV-CGI-1.1.1-SNAPSHOT-jar-with-dependencies.jar";

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Process proc;

    // Method that connects to the BoilerSimulator and responds true or false based on CONNECT-OK
    public boolean connectBoiler() {
        // Temp String
        String returnString = "";

        // Run the Boiler Simulator and establish a connection to it
        runBoilerSimulator();
        establishConnection();

        // Create a new PrintWriter and BufferedReader from the clientSocket
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }

        // Connect to the BoilerSimulator and retrieve the Secret Key
        String secretKey = getSecretKey();

        // Send CONNECT command to boiler and store response
        returnString = sendCommandToBoiler("$CV-CONNECT-$-" + secretKey);

        // Return the boolean based on the CONNECT-OK message, allows for future checking for errors
        return returnString.contains("CONNECT-OK");
    }

    // Method that establishes a connection with the BoilerSimulator over the Socket, attempts again on failure
    private void establishConnection() {
        int attempt = 0;
        // Attempt the connection and if unsuccessful, try again
        // Connect to boiler sim at localhost/127.0.0.1 and at port 7777
        try {
            clientSocket = new Socket("127.0.0.1", 7777);
        } catch (IOException e) {
            System.out.println(e);
            if (attempt < 5) {
                attempt++;
                connectBoiler();
            } else {
                System.exit(-1);
            }
        }
    }

    // Method that takes a String as input and outputs a response from the Boiler
    public String sendCommandToBoiler(String input) {
        String returnString;

        // Send command to boiler
        out.println(input);
        System.out.println(input);

        // Get output from boiler
        try {
            returnString = in.readLine();
            System.out.println(returnString);
            return returnString;
        } catch (IOException e) {
            System.out.println("Could not read line");
        }
        return "";
    }

    // Method that reads the input from the BoilerSimulator JAR, waits for the secret key and passes this back on the ready signal
    private String getSecretKey() {

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
//      Read the output from the command
        String s = null;
        String secretKey = null;
        while (true) {
            try {
                if (!((s = stdInput.readLine()) != null))
                    break;
            } catch (IOException e) {
                System.out.println(e);
            }
            System.out.println(s);
            if (s.contains("Server secret")) {
                secretKey = s.substring(s.length() - 6);
                System.out.println("Server secret " + secretKey);
            }
            if (s.contains("Ready")) {
                break;
            }
        }
        try {
            stdInput.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return secretKey;
    }

    // Method that runs the BoilerSimulator and binds it to a Process proc
    public boolean runBoilerSimulator() {
        if (proc == null || !proc.isAlive()) {
            Runtime rt = Runtime.getRuntime();
            String[] commands = {"java", "-jar", RELATIVE_PATH_CV_SIMULATOR};
            try {
                proc = rt.exec(commands);
                System.out.println(proc.isAlive());
                return true;
            } catch (IOException e) {
                System.out.println(e);
            }
            return false;
        } else {
            return false;
        }
    }

    // Method that stops the Process proc and closes all streams
    public boolean stopBoilerSimulator() {
        if (proc.isAlive()) {
            proc.destroy();
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
