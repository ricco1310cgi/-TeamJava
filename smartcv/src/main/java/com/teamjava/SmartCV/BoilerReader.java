package com.teamjava.SmartCV;

import org.apache.catalina.connector.CoyoteReader;

import java.net.*;
import java.io.*;

public class BoilerReader {
    // Variables used by program
    private int port;
    private String outputString;

    // Variables used for Sockets
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private OutputStream os;
    private OutputStreamWriter ows;
    private BufferedWriter bw;
    private InputStream is;
    private InputStreamReader isr;

    public String readFromBoiler(int port) throws IOException {
        // Temp String
        String returnString = "";

        // Connect to boiler sim at localhost/127.0.0.1 and at port 7777
        clientSocket = new Socket("127.0.0.1", 7777);


        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out.println("$CV-CONNECT-$-746069");
        out.println("$CV-STAT?");

        returnString = in.readLine();
        System.out.println(returnString);
        System.out.println(clientSocket.isConnected());

        in.close();
        out.close();
        clientSocket.close();

        return returnString;
    }
}
