package com.thebestlab6.server.utils;

import com.thebestlab6.common.objects.*;
import com.thebestlab6.server.utils.*;

import java.io.IOException;


public class ServerHelper {
    private Analyzer analyzer;
    private ServerReceiver serverReceiver;
    private ServerSender serverSender;

    public ServerHelper(Analyzer analyzer, ServerReceiver serverReceiver, ServerSender serverSender) {
        this.analyzer = analyzer;
        this.serverReceiver = serverReceiver;
        this.serverSender = serverSender;
    }

    public void run() throws IOException, ClassNotFoundException {
        while(true) {
            Request request = serverReceiver.getRequest();
            Response response = analyzer.handle(request);
            if (!serverSender.send(response))
                ResponseBuilder.appendError("Ошибка при отправке ответа");
        }
    }
}
