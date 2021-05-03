package com.thebestlab6.server.commands;

import com.thebestlab6.common.exceptions.*;
import com.thebestlab6.server.utils.CollectionManager;

import java.time.format.DateTimeFormatter;

public class Info implements Executable{
    private CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try{
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            System.out.println("Тип коллекции: " + collectionManager.collectionType());
            System.out.println("Размер коллекции: " + collectionManager.collectionSize());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            try{
                if (collectionManager.getLastSave() == null){
                    throw new NullLastSaveException("Данная коллекция еще не сохранялась");
                }
                System.out.println("Дата последнего сохранения: " + collectionManager.getLastSave().format(formatter));
            }
            catch (NullLastSaveException e){
                System.out.println(e.getMessage());
            }
            System.out.println("Дата инициализации: " + collectionManager.getLastInit().format(formatter));
            return true;
        }
        catch (WrongAmountOfElementsException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
