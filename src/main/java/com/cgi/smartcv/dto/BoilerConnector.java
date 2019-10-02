package com.cgi.smartcv.dto;

import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;

import java.io.*;
import java.net.Socket;

public class BoilerConnector {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Process proc;

    public BoilerConnector() {

    }

    public Socket connectBoiler() throws IOException {
        // Temp String
        String returnString = "";

        // Run the Boiler Simulator
        runBoilerSimulator();

        // Connect to boiler sim at localhost/127.0.0.1 and at port 7777
        clientSocket = new Socket("127.0.0.1", 7777);

        // Connect to the BoilerSimulator and retrieve the Secret Key
        String secretKey = getSecretKey();

        // Send CONNECT command to bioler and store response
        returnString = sendCommandToBoiler("$CV-CONNECT-$-" + secretKey, clientSocket);

        return clientSocket;
    }

    String sendCommandToBoiler(String input, Socket clientSocket) throws IOException {
        String returnString;

        if (clientSocket == null) {
            System.out.println("leeg");
            //clientSocket = new Socket("127.0.0.1", 7777);
        } else {
            System.out.println("vol");
        }

        // Create a new PrintWriter and BufferedReader from the clientSocket
//        System.out.println(clientSocket.toString());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Setup up the initial session with the CV !SECRET KEY CHANGES!
        out.println(input);
        System.out.println(input);

        // Output 1 from CV
        returnString = in.readLine();
        System.out.println(returnString);
//        out.close();
//        in.close();
        return returnString;
    }

    private String getSecretKey() throws IOException {

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        // Read the output from the command
        String s = null;
        String secretKey = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
            if (s.contains("Server secret")) {
                secretKey = s.substring(s.length() - 6);
                System.out.println("Server secret " + secretKey);
            }
            if (s.contains("Ready")) {
                break;
            }
        }
        return secretKey;
    }

    boolean runBoilerSimulator() throws IOException {
        if (proc == null || !proc.isAlive()) {
            Runtime rt = Runtime.getRuntime();

            String[] commands = {"java", "-jar", "..\\-TeamJava\\src\\main\\resources\\boilerSimulator\\CV-CGI-1.1.1-SNAPSHOT-jar-with-dependencies.jar"};

            proc = rt.exec(commands);

            return true;
        } else {
            return false;
        }

    }

    boolean stopBoilerSimulator() throws IOException {
        if (proc.isAlive()) {
            proc.destroy();
            out.close();
            in.close();
            return true;
        } else {
            return false;
        }
    }
}
