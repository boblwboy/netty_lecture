package com.founder.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("out.txt");

        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outFileChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {
            buffer.clear(); //如果注释掉这行代码会发生什么事情
            int read = inputFileChannel.read(buffer);
            System.out.println("read:"+read);
            if (-1 == read) {
                break;
            }

            buffer.flip();

            outFileChannel.write(buffer);

        }

        inputFileChannel.close();
        outFileChannel.close();

    }
}
