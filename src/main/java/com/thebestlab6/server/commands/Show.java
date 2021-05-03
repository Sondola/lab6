package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;

public class Show implements Executable{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object obj) {
        try {
            if (str.length() != 0) {
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            collectionManager.showCollection();
            return true;
        } catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
