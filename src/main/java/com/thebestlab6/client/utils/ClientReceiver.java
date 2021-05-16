package com.thebestlab6.client.utils;

import com.thebestlab6.common.objects.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiver {
    private DatagramChannel datagramChannel;
    private ByteBuffer buffer = ByteBuffer.allocate(8192);

    public ClientReceiver(DatagramChannel datagramChannel){
        this.datagramChannel = datagramChannel;
    }

    public Response getResponse() {
        try {
            datagramChannel.receive(buffer);
            buffer.flip();

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Response response = (Response) objectInputStream.readObject();
            //System.out.println("Есть response ");
            objectInputStream.close();
            byteArrayInputStream.close();
            buffer.clear();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Answer isn't received");
            return null;
        }
    }
}
