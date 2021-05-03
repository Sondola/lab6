package com.thebestlab6.client.utils;

import com.thebestlab6.common.exceptions.IncorrectCommandException;
import com.thebestlab6.common.objects.Request;
import com.thebestlab6.common.objects.Response;

import java.net.InetAddress;

public class ClientHelper {
    private final InetAddress host;
    private ClientReciever receiver;
    private ClientSender sender;
    private StringWorking stringWorking;

    public ClientHelper(InetAddress host, ClientReciever receiver, ClientSender sender, StringWorking stringWorking){
        this.host = host;
        this.receiver = receiver;
        this.sender = sender;
        this.stringWorking = stringWorking;
    }

    public void handle(String command){
        try {
            Request request = stringWorking.makeRequest(command);
            if(request != null){
                if(sender.send(request)){
                    System.out.println(request.toString());
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
