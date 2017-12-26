package com.founder.netty.handler3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by liuwei on 2017-5-31.
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyPersonDecoder());
        pipeline.addLast(new MyPersonEncoder());
        pipeline.addLast(new MyServerHandler());
    }
}
