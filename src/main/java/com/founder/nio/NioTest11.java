package com.founder.nio;

import io.netty.buffer.ByteBuf;

import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * 关于Buffer的Scaterting与Gathering
 */
public class NioTest11 {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(address);

        int messageLength = 2 + 3 + 4;
        ByteBuffer[] buffers = new ByteBuffer[3];

        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = socketChannel.read(buffers);
                byteRead += r;

                System.out.println("byteRead:" + byteRead);

                Arrays.asList(buffers).stream().map(buffer->"position:"+buffer.position()+",limit: "+buffer.limit()).forEach(System.out::println);

            }

            Arrays.asList(buffers).forEach(buffer -> {
                buffer.flip();
            });

            long byteWritten = 0;

            while (byteWritten < messageLength) {
                long w = socketChannel.write(buffers);
                byteWritten += w;
            }

            Arrays.asList(buffers).forEach(buffer->{buffer.clear();});

            System.out.println("byteRead: " + byteRead + ",byteWritten: " + byteWritten + ",messageLength: " + messageLength);
        }

    }
}
