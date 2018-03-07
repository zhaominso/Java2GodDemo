package com.zhaomin.demo.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer2 {

    private final static int DataSize = 5 * 1024 * 1024;
    private ServerSocket serverSocket;

    public TcpServer2 init(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("recv buffer:" + serverSocket.getReceiveBufferSize());
        return this;
    }

    public void serve() throws IOException {
        int serveNum = 0;
        Socket socket = serverSocket.accept();
        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = reader.readLine();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(name + serveNum++);
            writer.newLine();
            writer.flush();
        }
    }
}
