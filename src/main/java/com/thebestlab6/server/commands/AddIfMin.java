package com.thebestlab6.server.commands;

import com.thebestlab6.common.data.HumanBeing;
import com.thebestlab6.common.exceptions.*;
import com.thebestlab6.server.utils.CollectionManager;
import com.thebestlab6.server.utils.ResponseBuilder;

import javax.xml.stream.XMLStreamException;

public class AddIfMin implements Executable{
    private CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public boolean execute(String str, Object humanObj) {
        try {
            if(str.length() != 0){
                throw new WrongAmountOfElementsException("Неправильное количество аргументов для команды");
            }
            HumanBeing human = (HumanBeing) humanObj;
            if (collectionManager.collectionSize() != 0) {
                if (human.getImpactSpeed() < collectionManager.getMinImpactSpeed()) {
                    collectionManager.add(human);
                    ResponseBuilder.append("Элемент успешно добавлен в коллекцию!");
                } else
                    ResponseBuilder.append("Элемент не был добавлен в коллекцию, так как его значение поля ImpactSpeed больше минимального");
            } else throw new NoElementsInCollectionException("В коллекции нет элементов для сравнения!");
            return true;
        } catch (WrongAmountOfElementsException e) {
            ResponseBuilder.appendError(e.getMessage());
            return false;
        } catch (NoElementsInCollectionException e) {
            ResponseBuilder.appendError(e.getMessage());
            return false;
        }
    }
}
