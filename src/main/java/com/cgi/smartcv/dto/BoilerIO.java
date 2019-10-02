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

public class BoilerIO {

	BoilerConnector boilerConnector = new BoilerConnector();

	String getCurrentStats(Socket socket) throws IOException {

		return boilerConnector.sendCommandToBoiler("$CV-STAT?", socket);
	}
}
