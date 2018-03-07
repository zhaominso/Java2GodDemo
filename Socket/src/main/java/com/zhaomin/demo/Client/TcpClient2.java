package com.zhaomin.demo.Client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class TcpClient2 {

    private Socket socket;

    public TcpClient2 init(int port, int localPort) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), port, InetAddress.getLocalHost(), localPort);
        return this;
    }

    public void sendData() throws IOException {
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String name = reader.readLine();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(name);
            writer.newLine();
            writer.flush();
            System.out.println(getData());
        }
    }

    public String getData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return reader.readLine();
    }
}
