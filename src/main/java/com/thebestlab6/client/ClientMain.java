package com.thebestlab6.client;

import com.thebestlab6.client.utils.*;
import com.thebestlab6.client.utils.Console;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;

public class ClientMain {

    public static final int PORT = 1488;

    public static void main(String[] args) {
        BufferedInputStream bf = new BufferedInputStream(System.in);
        BufferedReader r = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
        AskManager askManager = new AskManager(r);
        StringWorking stringWorking = new StringWorking(askManager);

        try {
            InetAddress ADDR = InetAddress.getByName("localhost");
            InetSocketAddress socketAddress = new InetSocketAddress(ADDR, PORT);
            DatagramChannel datagramChannel = DatagramChannel.open();
            ClientSender sender = new ClientSender(datagramChannel, socketAddress);
            ClientReciever reciever = new ClientReciever(datagramChannel, socketAddress, PORT);
            ClientHelper client = new ClientHelper(ADDR, reciever, sender, stringWorking);
            Console console = new Console(r, askManager, client);
            stringWorking.addConsole(console);
            console.interactiveMode();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}

