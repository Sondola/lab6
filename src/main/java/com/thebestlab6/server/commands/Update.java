package com.thebestlab6.server.commands;

import com.thebestlab6.common.data.HumanBeing;
import com.thebestlab6.common.exceptions.*;
import com.thebestlab6.server.utils.CollectionManager;

public class Update implements Executable{
    private CollectionManager collectionManager;

    public Update(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object obj) {
        try {
            if (str.length() == 0) throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            int id = Integer.parseInt(str);
            HumanBeing human = collectionManager.getById(id);
            if (human != null) {
                human = (HumanBeing) obj;
                collectionManager.setNewHuman(id, human);
                System.out.println("Данные были успешно обновлены!");
            }
            else throw new WrongIdException("Не найден человек с таким id");
            return true;
        } catch (WrongAmountOfElementsException | WrongIdException e) {
            System.out.println(e.getMessage());
            return false;
        } /*catch (IncorrectScriptInputException e) {
            System.out.println("Не удалось выполнить скрипт! Введены некорректные данные!");
            return false;
        } */catch (NumberFormatException e) {
            System.out.println("Некорректно введен id");
            return false;
        }
    }
}
