package com.founder.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by liuwei on 2017-5-31.
 */
public class MyClientHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0;i <10; ++i) {
            String messageToBeSend = "send from client  ";
            int length = messageToBeSend.getBytes("utf-8").length;
            byte[] content = messageToBeSend.getBytes("utf-8");

            PersonProtocol personProtocol = new PersonProtocol();
            personProtocol.setLength(length);
            personProtocol.setContent(content);

            ctx.writeAndFlush(personProtocol);
        }

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();

        System.out.println("客户端接收到的数据：");
        System.out.println("长度：" + length);
        System.out.println("内容："+ new String(content,Charset.forName("utf-8")));
        System.out.println("客户端接收到的数量：" + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
