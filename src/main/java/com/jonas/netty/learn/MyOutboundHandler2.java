package com.jonas.netty.learn;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * MyInboundHandler
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-05-10
 */
public class MyOutboundHandler2 extends ChannelHandlerAdapter {
    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyOutbound2:" + Thread.currentThread().getName());
        ctx.read();
    }
}
