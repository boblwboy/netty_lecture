package com.founder.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;

public class NioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isConnectable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if (client.isConnectionPending()) {
                        client.finishConnect();

                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                        writeBuffer.put((LocalDateTime.now() + " 连接成功！").getBytes());
                        writeBuffer.flip();

                        client.write(writeBuffer);


                    }

                }
            }





        }


    }
}
