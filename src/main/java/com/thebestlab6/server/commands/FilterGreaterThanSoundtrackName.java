package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.*;
import com.thebestlab6.server.utils.CollectionManager;

public class FilterGreaterThanSoundtrackName implements Executable{
    private CollectionManager collectionManager;

    public FilterGreaterThanSoundtrackName(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try {
            if(str.length() == 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            String soundtrackName = humanObj.toString();
            if (collectionManager.collectionSize() != 0)
                collectionManager.showCollection(collectionManager.filterGreaterThanSoundtrackName(soundtrackName));
            else throw new NoElementsInCollectionException("Коллекция пуста!");
            return true;
        } catch (WrongAmountOfElementsException | NoElementsInCollectionException e) {
            System.out.println(e.getMessage());
            return false;
        } /*catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        }*/
    }
}
