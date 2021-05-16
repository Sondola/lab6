package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;
import com.thebestlab6.server.utils.ResponseBuilder;

import javax.xml.stream.XMLStreamException;

public class Clear implements Executable{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            collectionManager.clearCollection();
            ResponseBuilder.append("Коллекция очищена");
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }
}
