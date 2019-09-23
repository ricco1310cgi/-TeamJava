package com.cgi.smartcv.dto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

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

		// Create a new PrintWriter and BufferedReader from the clientSocket
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Setup up the initial session with the CV !SECRET KEY CHANGES!
		out.println("$CV-CONNECT-$-465969");
		// Ask the CV for the current stats of the CV
		out.println("$CV-STAT?");

		// Output 1 from CV
		returnString = in.readLine();
		System.out.println(returnString);

		// Output 2 from CV
		returnString = in.readLine();
		System.out.println(returnString);
		System.out.println(clientSocket.isConnected());

		// Terminate the session and return control
		in.close();
		out.close();
		clientSocket.close();

		// Return the final product to the Controller
		return returnString;
	}
}
