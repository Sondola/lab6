package com.thebestlab6.server.commands;

import com.thebestlab6.common.data.*;
import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;
import com.thebestlab6.server.utils.ResponseBuilder;

public class Add implements Executable{
    private CollectionManager collectionManager;

    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object human) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            collectionManager.add((HumanBeing) human);
            System.out.println("Элемент успешно добавлен в коллекцию!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }
}
