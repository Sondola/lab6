package com.thebestlab6.server.utils;

import com.thebestlab6.common.objects.Request;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class ServerReceiver {
    private DatagramSocket serverSocket;
    private DatagramPacket packet;
    private byte bytes[] = new byte[8192];

    public ServerReceiver(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Request getRequest() throws IOException, ClassNotFoundException {
        packet = new DatagramPacket(bytes, bytes.length);
        //try {
            serverSocket.receive(packet);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            System.out.println(Arrays.toString(bytes));
            Request request = (Request) objectInputStream.readObject();

            ResponseBuilder.append("Сообщение получено");
            System.out.println("Сообщение получено");
            return request;
        /*} catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "1");
            ResponseBuilder.appendError("Ошибка ввода/вывода");
            return null;
        } catch (IOException e) {
            System.out.println(e.getMessage() + "2");
            return null;
        }*/
    }

    public InetAddress getAddress() {
        return packet.getAddress();
    }

    public int getPort() {
        return packet.getPort();
    }
}
