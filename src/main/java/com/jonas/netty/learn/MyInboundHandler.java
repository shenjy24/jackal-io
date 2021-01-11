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
public class MyInboundHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyInbound:Connected!");
        ctx.fireChannelActive();
    }
}
