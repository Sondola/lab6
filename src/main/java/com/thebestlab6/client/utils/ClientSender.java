package com.thebestlab6.client.utils;

import com.thebestlab6.common.objects.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSender {
    private DatagramChannel datagramChannel;
    private InetSocketAddress socketAdd;
    private ByteBuffer buffer = ByteBuffer.allocate(8192);
    //private Selector selector;

    public ClientSender(DatagramChannel datagramChannel, InetSocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAdd = socketAddress;
        //this.selector = selector;
    }

    public boolean send(Request request) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream= new ObjectOutputStream(byteArrayOutputStream)){
            //datagramChannel.socket().bind(socketAdd);
            objectOutputStream.writeObject(request);
            buffer.put(byteArrayOutputStream.toByteArray());
            buffer.clear();
            datagramChannel.send(buffer, socketAdd);
            buffer.flip();
            datagramChannel.disconnect();
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Данные не отправлены");
            return false;
        }
    }
}
