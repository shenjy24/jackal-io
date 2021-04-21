package com.jonas.netty.learn;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * MyInboundHandler
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-05-10
 */
public class MyOutboundHandler2 extends ChannelHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("MyOutbound2:" + Thread.currentThread().getName());
        ctx.write(msg);
    }
}
