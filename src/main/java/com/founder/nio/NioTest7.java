package com.founder.nio;

import java.nio.ByteBuffer;

/**
 * 只读Buffer，我们可以随时将一个普通的Buffer调用asReadOnlyBuffer方法返回一个只读Buffer
 * 但不能将一个只读Buffer转换为读写Buffer
 */
public class NioTest7 {
    public static void main(String[] args) throws Exception {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0;i<byteBuffer.capacity();i++) {
            byteBuffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();

        readOnlyBuffer.flip();

        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }


    }
}
