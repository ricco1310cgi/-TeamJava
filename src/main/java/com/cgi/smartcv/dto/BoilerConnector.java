package com.cgi.smartcv.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class BoilerConnector {

	private static Socket clientSocket;
	private static PrintWriter out;
	private static BufferedReader in;
	private static Process proc;

	// Method that connects to the BoilerSimulator and responds true or false based
	// on CONNECT-OK
	boolean connectBoiler() throws IOException, InterruptedException {
		// Temp String
		String returnString = "";

		// Run the Boiler Simulator and establish a connection to it
		runBoilerSimulator();
		establishConnection();

		// Create a new PrintWriter and BufferedReader from the clientSocket
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		// Connect to the BoilerSimulator and retrieve the Secret Key
		String secretKey = getSecretKey();

		// Send CONNECT command to boiler and store response
		returnString = sendCommandToBoiler("$CV-CONNECT-$-" + secretKey);

		// Return the boolean based on the CONNECT-OK message, allows for future
		// checking for errors
		return returnString.contains("CONNECT-OK");
	}

	// Method that establishes a connection with the BoilerSimulator over the
	// Socket, attempts again on failure
	private void establishConnection() throws IOException, InterruptedException {
		int attempt = 0;
		// Attempt the connection and if unsuccessful, try again
		try {
			// Connect to boiler sim at localhost/127.0.0.1 and at port 7777
			clientSocket = new Socket("127.0.0.1", 7777);
		} catch (ConnectException e) {
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
	String sendCommandToBoiler(String input) throws IOException {
		String returnString;

		// Send command to boiler
		out.println(input);
		System.out.println(input);

		// Get output from boiler
		returnString = in.readLine();
		System.out.println(returnString);

		return returnString;
	}

	// Method that reads the input from the BoilerSimulator JAR, waits for the
	// secret key and passes this back on the ready signal
	private String getSecretKey() throws IOException, InterruptedException {

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

//		BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

		// Read the output from the command
		String s = null;
		String secretKey = null;
		Thread.sleep(1000);

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
		stdInput.close();
		return secretKey;
	}

	// Method that runs the BoilerSimulator and binds it to a Process proc
	boolean runBoilerSimulator() throws IOException {
		if (proc == null || !proc.isAlive()) {
			Runtime rt = Runtime.getRuntime();

			String[] commands = { "java", "-jar",
					"..\\-TeamJava\\src\\main\\resources\\boilerSimulator\\CV-CGI-1.1.1-SNAPSHOT-jar-with-dependencies.jar" };

			proc = rt.exec(commands);

			System.out.println(proc.isAlive());

			if (proc.isAlive()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	// Method that stops the Process proc and closes all streams
	boolean stopBoilerSimulator() throws IOException {
		if (proc.isAlive()) {
			proc.destroy();
			clientSocket.close();
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean getProc() {
		if (proc == null) {
			return false;
		}
		return proc.isAlive();
	}
}
