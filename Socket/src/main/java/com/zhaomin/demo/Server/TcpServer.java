package com.zhaomin.demo.Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

    private final static int DataSize = 5 * 1024 * 1024;
    private ServerSocket serverSocket;

    public TcpServer init(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("recv buffer:" + serverSocket.getReceiveBufferSize());
        return this;
    }

    public void serve() throws IOException {
        int serveNum = 0;
        Socket socket = serverSocket.accept();
        System.out.println("serveNum=" + serveNum++);
        Reader reader = new InputStreamReader(socket.getInputStream());
        char[] buff = new char[512];
        reader.read(buff);
        System.out.println("Client msg:" + String.valueOf(buff));

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(initData());
        outputStream.flush();
        outputStream.close();
        System.out.println("server flush success");
    }

    private byte[] initData() throws IOException {
        byte[] data = new byte[DataSize];
        for (int i = 0; i < DataSize; i++) {
            data[i] = 99;
        }
        return data;
    }
}
