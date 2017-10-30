package com.founder.zerocopy;

import com.sun.tools.doclets.internal.toolkit.util.DocFinder;

import java.io.*;
import java.net.Socket;

public class OldIOClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost",8899);

        String fileName = "/Users/bob/Downloads/movie/电脑狂人.Halt.and.Catch.Fire.S04E10.End.中英字幕.WEB-HR.AAC.720P.x264-人人影视.mp4";

        InputStream inputStream = new FileInputStream(fileName);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];

        long readCount;

        long total = 0;

        long startTime = System.currentTimeMillis();

        while ((readCount = inputStream.read(buffer)) >= 0) {
            total += readCount;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送总字节数： " + total + ",耗时： " + (System.currentTimeMillis() - startTime));

        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
