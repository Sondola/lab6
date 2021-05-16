package com.thebestlab6.client.utils;

import com.thebestlab6.common.exceptions.IncorrectCommandException;
import com.thebestlab6.common.objects.Request;
import com.thebestlab6.common.objects.Response;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.DatagramChannel;

public class ClientHelper {
    private ClientReceiver receiver;
    private ClientSender sender;
    private StringWorking stringWorking;

    public ClientHelper(ClientReceiver receiver, ClientSender sender, StringWorking stringWorking){
        this.receiver = receiver;
        this.sender = sender;
        this.stringWorking = stringWorking;
    }

    public void handle(String command){
        try {
            Request request = stringWorking.makeRequest(command);
            if(request != null){
                if(sender.send(request)){
                    //System.out.println(request.toString());
                    Response response = receiver.getResponse();
                    if(response != null){
                        System.out.print(response.getResponseBody());
                    }
                }
            }

        } catch (IncorrectCommandException e) {
            System.out.println(e.getMessage());
        }
    }
}
