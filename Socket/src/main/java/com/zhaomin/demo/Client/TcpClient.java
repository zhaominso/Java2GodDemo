package com.zhaomin.demo.Client;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;


public class TcpClient {

    private Socket socket;

    public TcpClient init(int port, int localPort) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), port, InetAddress.getLocalHost(), localPort);
        System.out.println(socket.getSendBufferSize());
//        for (int i = 0; i < DataSize; i++) {
//            data[i] = 1;
//        }
        return this;
    }

    public void sendData() throws IOException {
        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write("I am ready to get data");
        writer.flush();
        System.out.println("client send data success");
    }

    public void getData() throws IOException {
        System.out.println("client wait to get data");
        InputStream inputStream = socket.getInputStream();
        byte[] tmp = new byte[1024];
        int i = 0;
        int offset = 0;
        while ((offset = inputStream.read(tmp)) != -1) {
            i++;
            System.out.println(i);
        }
        System.out.println(i);
        print(tmp);
    }

    private void print(byte[] tmp) {
        System.out.print("[");
        for (byte b : tmp) {
            System.out.print(b);
        }
        System.out.println("]");
    }

    public void close() throws IOException {
        socket.shutdownOutput();
        socket.shutdownInput();
        socket.close();
    }
}
