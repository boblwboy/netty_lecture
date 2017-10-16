package com.founder.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer类型化的put与get方法
 */
public class NioTest5 {
    public static void main(String[] args) throws Exception {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(15);
        byteBuffer.putLong(5000000L);
        byteBuffer.putDouble(14.000001);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('我');

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());


    }
}
