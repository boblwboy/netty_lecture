package com.founder.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.Buffer;

public class ByteBufTest0 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < byteBuf.capacity();i++) {
            byteBuf.writeByte(i);
        }

        for (int i = 0; i < byteBuf.capacity();i++) {
            System.out.println(byteBuf.getByte(i));
        }
    }
}
