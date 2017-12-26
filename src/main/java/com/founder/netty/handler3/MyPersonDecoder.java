package com.founder.netty.handler3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyPersonDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

        System.out.println("MyPersonDecoder decode invoked!");

        int length = in.readInt();
        byte[] bytes = new byte[length];

        in.readBytes(bytes);

        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length);
        personProtocol.setContent(bytes);

        out.add(personProtocol);
    }
}
