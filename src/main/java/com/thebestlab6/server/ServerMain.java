package com.thebestlab6.server;

import com.thebestlab6.server.commands.*;
import com.thebestlab6.server.utils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.logging.Logger;

public class ServerMain {
    public static final int PORT = 1488;
    public static final Logger logger = Logger.getLogger(ServerMain.class.getName());

    public static void main(String[] args) {
        //if (args.length == 0) {
          //  System.out.println("Недопустимое количество аргументов командной строки!");
            //System.exit(0);
        //}

        try{
            //FileInputStream is = new FileInputStream(args[0]);
            FileInputStream is = new FileInputStream("lab5.xml");
        }catch (FileNotFoundException e) {
            File file = new File(args[0]);
            if (!file.canRead() && !file.canWrite()) {
                try {
                    file.createNewFile();
                    System.out.println("Файла не было, но мы его создали!");
                } catch (IOException e2) {
                    System.out.println("error");
                    System.exit(0);
                }
            } else {
                System.out.println("Ошибка доступа");
                System.exit(0);
            }
        }
        //File fileName = new File(args[0]);
        File fileName = new File("lab5.xml");

        CollectionManager collectionManager = new CollectionManager(fileName);
        CommandManager commandManager = new CommandManager(
                new Info(collectionManager),
                new Help(),
                new Show(collectionManager),
                new Add(collectionManager),
                new Update(collectionManager),
                new RemoveById(collectionManager),
                new Clear(collectionManager),
                new Save(collectionManager, fileName),
                //new ExecuteScript(),
                //new Exit(),
                new AddIfMax(collectionManager),
                new AddIfMin(collectionManager),
                new RemoveGreater(collectionManager),
                new RemoveAllByCar(collectionManager),
                new MaxByCreationDate(collectionManager),
                new FilterGreaterThanSoundtrackName(collectionManager));

        Analyzer analyzer = new Analyzer(commandManager);
        /*try {
            DatagramSocket datagramSocket = new DatagramSocket(PORT);
            ServerReceiver receiver = new ServerReceiver(datagramSocket);
            ServerSender sender = new ServerSender(datagramSocket, receiver.getAddress(), receiver.getPort());
            ServerHelper server = new ServerHelper(analyzer, receiver, sender);
            server.run();
        } catch (SocketException e) {
            e.printStackTrace();
        }*/

        try {
            InetAddress ADDR = InetAddress.getByName("localhost");
            InetSocketAddress socketAddress = new InetSocketAddress(ADDR, PORT);
            DatagramSocket datagramSocket = new DatagramSocket(socketAddress);
            ServerReceiver receiver = new ServerReceiver(datagramSocket);
            ServerSender sender = new ServerSender(datagramSocket, ADDR, PORT);
            ServerHelper server = new ServerHelper(analyzer, receiver, sender);
            server.run();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
