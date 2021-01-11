package com.jonas.aio.example;

/**
 * TimeServer
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-03-16
 */
public class TimeServer {

    public static void main(String[] args) {
        new TimeServer().startServer(8080);
    }

    public void startServer(int port) {
        AsyncTimeServerHandler timerServer = new AsyncTimeServerHandler(port);
        new Thread(timerServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
