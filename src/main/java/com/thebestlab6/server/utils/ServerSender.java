package com.thebestlab6.server.utils;

import com.thebestlab6.common.objects.Response;
import com.thebestlab6.server.ServerMain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender {
    private DatagramSocket serverSocket;
    private InetAddress inetAdd;
    private int port;
    private byte bytes[] = new byte[16000];

    public ServerSender(DatagramSocket ds, InetAddress ia, int port) {
        serverSocket = ds;
        inetAdd = ia;
        this.port = port;
    }

    public boolean send(Response serverResponse) {
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(serverResponse);
            objectOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAdd, port);
            serverSocket.send(packet);
            ServerMain.logger.info("Пакет отправлен");
            return true;
        } catch (IOException e) {
            ResponseBuilder.appendError("Ошибка ввода/выводы");
            return false;
        }
    }
}
