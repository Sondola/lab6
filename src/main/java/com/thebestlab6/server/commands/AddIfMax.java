package com.thebestlab6.server.commands;

import com.thebestlab6.common.data.HumanBeing;
import com.thebestlab6.common.exceptions.WrongAmountOfElementsException;
import com.thebestlab6.server.utils.CollectionManager;

public class AddIfMax implements Executable{
    private CollectionManager collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            HumanBeing human = (HumanBeing) humanObj;
            if (Float.compare(human.getImpactSpeed(), collectionManager.getMaxImpactSpeed()) == 1) {
                collectionManager.add(human);
                System.out.println("Элемент успешно добавлен в коллекцию!");
            }
            else System.out.println("Элемент не был добавлен в коллекцию, так как его значение поля ImpactSpeed меньше максимального");
            return true;
        } /*catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        } */catch (WrongAmountOfElementsException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
