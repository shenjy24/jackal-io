package com.jonas.aio.example;

/**
 * TimeClient
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-03-16
 */
public class TimeClient {

    public static void main(String[] args) {
        TimeClient timeClient = new TimeClient();
        timeClient.startConnect("127.0.0.1", 8080);
    }

    public void startConnect(String host, int ip) {
        new Thread(new AsyncTimeClientHandler(host, ip), "AIO-TimeClient-001").start();
    }
}
