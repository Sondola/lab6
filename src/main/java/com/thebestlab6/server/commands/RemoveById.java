package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.*;
import com.thebestlab6.server.utils.CollectionManager;

public class RemoveById implements Executable{
    private CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object obj) {
        try {
            if (str.length() == 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            if (collectionManager.collectionSize() != 0) {
                int id = Integer.parseInt(str);
                if (collectionManager.getById(id) != null)
                    collectionManager.removeById(id);
                else throw new WrongIdException("Не найден человек с таким id");
            }
            else throw new NoElementsInCollectionException("Коллекция пуста!");
            return true;
        } catch (WrongAmountOfElementsException | WrongIdException | NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Некорректно введен id");
            return false;
        }
    }
}
