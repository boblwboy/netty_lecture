package com.founder.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NewIOClient {
    public static void main(String[] args) throws Exception {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost",8899);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);
        socketChannel.configureBlocking(true);

        String fileName = "/Users/bob/Downloads/movie/电脑狂人.Halt.and.Catch.Fire.S04E10.End.中英字幕.WEB-HR.AAC.720P.x264-人人影视.mp4";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送总字节数： " + transferCount + ",耗时： " + (System.currentTimeMillis() - startTime));

        fileChannel.close();



    }
}
