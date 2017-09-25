package com.lti.httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Appplication {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0); // Create a server at the mentioned port.
    server.createContext("/postRequest", new MyPostHandler());
    server.createContext("/getRequest", new MyGetHandler());
    server.setExecutor(null); // creates a default executor
    server.start();
    System.out.println("Server Started...");
  }
  
  static class MyPostHandler implements HttpHandler {
	    public void handle(HttpExchange httpExchange) throws IOException {
	        InputStream inputStream =  httpExchange.getRequestBody();
	        int i;
	        StringBuilder stringBuilder = new StringBuilder();
	        while((i = inputStream.read()) != -1)
	        	stringBuilder.append((char)i);
	        String requestBody = stringBuilder.toString(); // Will send the HTTP Body request has a response to the client.
	        String response = "Welcome the request body sent by you is \n" + requestBody;
	        httpExchange.sendResponseHeaders(200, response.length());
	        System.out.println(response);
	        OutputStream outputStream = httpExchange.getResponseBody();
	        outputStream.write(response.getBytes());
	        outputStream.close();
	    }
	}
  
  static class MyGetHandler implements HttpHandler {
	    public void handle(HttpExchange httpExchange) throws IOException {
	        String response = "Hello"; // Will send "Hello" has a response to the client.
	        httpExchange.sendResponseHeaders(200, response.length());
	        System.out.println(response);
	        OutputStream outputStream = httpExchange.getResponseBody();
	        outputStream.write(response.getBytes());
	        outputStream.close();
	    }
	}

}
