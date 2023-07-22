package com.example.Net;

import java.net.*;
import java.io.*;

public class SocketClientDemo {

  int port = 2345;
  String host = "localhost";
  Socket socket;

  public SocketClientDemo() {
    try {
      socket = new Socket(InetAddress.getByName(host), port);

      DataInputStream in = new DataInputStream(socket.getInputStream());
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      byte[] buffer = new byte[256];
      in.read(buffer);
      System.out.println(new String(buffer));
      System.out.println("Connect Success!");
      socket.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new SocketClientDemo();
  }
}