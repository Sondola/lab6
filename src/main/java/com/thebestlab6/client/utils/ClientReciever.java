package com.thebestlab6.client.utils;

import com.thebestlab6.common.objects.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReciever {
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private ByteBuffer buffer = ByteBuffer.allocate(16000);
    private int port;

    public ClientReciever(DatagramChannel datagramChannel, SocketAddress socketAddress, int port){
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.port = port;
    }

    public Response getResponse() {
        try {
            datagramChannel.socket().bind(new InetSocketAddress(port));
            datagramChannel.receive(buffer);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Response response = (Response) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            buffer.flip();
            datagramChannel.disconnect();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ответ не получен");
            return null;
        }
    }
}
