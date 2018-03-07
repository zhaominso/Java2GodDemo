package com.zhaomin.demo;

import com.zhaomin.demo.Client.TcpClient;
import com.zhaomin.demo.Client.TcpClient2;
import com.zhaomin.demo.Server.TcpServer;
import com.zhaomin.demo.Server.TcpServer2;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private void testTcp() throws IOException {
        final TcpServer tcpServer = new TcpServer().init(2333);
        TcpClient tcpClient = new TcpClient().init(2333, 56567);
        ExecutorService server = Executors.newSingleThreadExecutor();
        server.submit(() -> {
            try {
                tcpServer.serve();
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("server error");
            }
        });
        tcpClient.sendData();
        tcpClient.getData();
        tcpClient.close();
    }

    private void testTcp2() throws IOException {
        final TcpServer2 tcpServer = new TcpServer2().init(23334);
        TcpClient2 tcpClient = new TcpClient2().init(23334, 56568);
        ExecutorService server = Executors.newSingleThreadExecutor();
        ExecutorService client = Executors.newSingleThreadExecutor();
        server.submit(() -> {
            try {
                tcpServer.serve();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        client.submit(() -> {
            try {
                tcpClient.sendData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Test test = new Test();
        test.testTcp2();
    }
}
