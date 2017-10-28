package com.founder.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Set;

public class NioTest13 {

    public static void main(String[] args) throws Exception {

        String inputFile = "NioTest13_In.txt";
        String outputFile = "NioTest13_Out.txt";

        RandomAccessFile inputRandomAccessFile = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outputRandomAccessFile = new RandomAccessFile(outputFile, "rw");

        long inputFileLength = new File(inputFile).length();

        FileChannel inputFileChannel = inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel = outputRandomAccessFile.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputFileLength);

        System.out.println("=================================");
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k + "," + v);
        });


        System.out.println("=================================");

        Charset charset = Charset.forName("utf-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = charsetDecoder.decode(inputData);
        ByteBuffer outputData = charsetEncoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();

    }

}
