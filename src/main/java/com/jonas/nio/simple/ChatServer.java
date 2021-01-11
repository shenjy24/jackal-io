package com.jonas.nio.simple;

import lombok.SneakyThrows;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 聊天服务端
 *
 * @author shenjy 2019/01/03
 */
public class ChatServer {

    private final Charset charset = StandardCharsets.UTF_8;

    @SneakyThrows
    public void start(String host, int port) {
        //用于检测所有Channel状态的Selector
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(host, port);
        server.bind(address);

        //设置非阻塞方式工作
        server.configureBlocking(false);

        //将server注册到指定的Selector对象
        server.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            for (SelectionKey sk : selector.selectedKeys()) {
                //从selector上的已选择Key集合中删除正在处理的SelectionKey
                selector.selectedKeys().remove(sk);
                //如果sk对应的Channel包含客户端的请求
                if (sk.isAcceptable()) {
                    //调用accept方法接受连接，产生服务器端的SocketChannel
                    SocketChannel socketChannel = server.accept();
                    //设置采用非阻塞模式
                    socketChannel.configureBlocking(false);
                    //将该SocketChannel也注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    //将sk对应的Channel设置成准备接受其他请求
                    sk.interestOps(SelectionKey.OP_ACCEPT);
                }
                //如果sk对应的Channel有数据可以读取
                if (sk.isReadable()) {
                    //获取该SelectionKey对应的Channel，该Channel中有可读的数据
                    SocketChannel socketChannel = (SocketChannel) sk.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    StringBuilder content = new StringBuilder();
                    try {
                        while (socketChannel.read(buffer) > 0) {
                            buffer.flip();
                            content.append(charset.decode(buffer));
                        }
                        System.out.println("读取的数据:" + content);
                        sk.interestOps(SelectionKey.OP_READ);
                    }
                    /**
                     * 如果捕获到该sk对应的Channel出现了异常，即表明了该Channel
                     * 对应的Client出现了问题，所以从Selector中取消sk的注册
                     */ catch (IOException e) {
                        sk.cancel();
                        if (sk.channel() != null) {
                            sk.channel().close();
                        }
                    }

                    //聊天信息不为空时，将信息发给其他客户端
                    for (SelectionKey key : selector.keys()) {
                        Channel targetChannel = key.channel();
                        if (targetChannel instanceof SocketChannel) {
                            SocketChannel dest = (SocketChannel) targetChannel;
                            dest.write(charset.encode(content.toString()));
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().start("127.0.0.1", 30000);
        System.out.println("NIO Server Started");
    }
}
