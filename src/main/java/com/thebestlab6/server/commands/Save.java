package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Save implements Executable {
    private CollectionManager collectionManager;
    File xmlFile;

    public Save(CollectionManager collectionManager, File xmlFile) {
        this.collectionManager = collectionManager;
        this.xmlFile = xmlFile;
    }

    public boolean execute(String str, Object obj) {
        try {
            if (str.length() != 0) {
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            OutputStreamWriter fos = new OutputStreamWriter(new FileOutputStream(xmlFile));
            collectionManager.saveCollection(fos);
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (XMLStreamException e) {
            System.out.println("Error");
            return false;
        }
    }
}
