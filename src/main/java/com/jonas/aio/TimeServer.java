package com.jonas.aio;

/**
 * TimeServer
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-03-16
 */
public class TimeServer {

    public static void main(String[] args) {
        TimeServer timeServer = new TimeServer();
        timeServer.startServer(8080);
    }

    public void startServer(int port) {
        AsyncTimeServerHandler timerServer = new AsyncTimeServerHandler(port);
        new Thread(timerServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
